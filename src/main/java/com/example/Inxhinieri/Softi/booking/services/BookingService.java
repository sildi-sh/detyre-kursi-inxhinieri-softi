package com.example.Inxhinieri.Softi.booking.services;

import com.example.Inxhinieri.Softi.booking.dto.CreateBookingDto;
import com.example.Inxhinieri.Softi.booking.enums.BookingStatus;
import com.example.Inxhinieri.Softi.booking.model.Booking;
import com.example.Inxhinieri.Softi.booking.repository.BookingRepository;
import com.example.Inxhinieri.Softi.guide.services.GuideService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final GuideService guideService;

    public BookingService(BookingRepository bookingRepository, GuideService guideService) {
        this.bookingRepository = bookingRepository;
        this.guideService = guideService;
    }

    /* --- KS6: Krijimi i rezervimit --- */
    public Booking createBooking(CreateBookingDto dto) {
        var guide = guideService.findById(dto.getGuideId());

        Booking booking = new Booking();
        booking.setUserId(dto.getUserId());
        booking.setGuideId(dto.getGuideId());
        booking.setNumParticipants(dto.getNumParticipants());
        booking.setBookingDate(dto.getBookingDate());
        booking.setStatus(BookingStatus.Pending); // Statusi fillestar

        booking.setTotalPrice(guide.getBasePrice() * dto.getNumParticipants());

        return bookingRepository.save(booking);
    }

    /* --- KS6 & KS11: Ndryshimi i statusit dhe Njoftimi --- */
    public Booking updateStatus(String id, BookingStatus newStatus) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezervimi nuk u gjet"));

        booking.setStatus(newStatus);

        // Këtu në të ardhmen do të shtohet njoftimi (KS11)
        System.out.println("Njoftim: Rezervimi " + id + " ndryshoi në " + newStatus);

        return bookingRepository.save(booking);
    }

    /* --- KS6: Historiku i rezervimeve për përdoruesin --- */
    public List<Booking> getHistoryByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    /* --- KS13: Raportimi dhe Analitika --- */
    // Kjo metodë ndihmon adminin të shohë sa rezervime janë sipas statusit
    public Map<BookingStatus, Long> getBookingStats() {
        return bookingRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Booking::getStatus, Collectors.counting()));
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public void delete(String id) {
        bookingRepository.deleteById(id);
    }
}