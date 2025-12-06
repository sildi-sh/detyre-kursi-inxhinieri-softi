package com.example.Inxhinieri.Softi.track.repository;

import com.example.Inxhinieri.Softi.track.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, String> {

    // 1. findById(UUID id) është trashëguar automatikisht nga JpaRepository.

    List<Track> findByUserId(String userId);
//    List<Track> findByIs_publicTrue();
}