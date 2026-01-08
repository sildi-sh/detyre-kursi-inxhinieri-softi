package com.example.Inxhinieri.Softi.booking.repository;

import com.example.Inxhinieri.Softi.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    // Gjen të gjitha rezervimet e bëra nga një përdorues specifik
    List<Booking> findByUserId(String userId);

    // Gjen të gjitha rezervimet që janë bërë për një guidë specifike
    List<Booking> findByGuideId(String guideId);

    // Mund të shtosh edhe kërkim sipas statusit (p.sh. gjej të gjitha 'Pending')
    List<Booking> findByStatus(String status);
}