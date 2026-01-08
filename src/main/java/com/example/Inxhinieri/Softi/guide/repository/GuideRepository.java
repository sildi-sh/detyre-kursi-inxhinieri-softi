package com.example.Inxhinieri.Softi.guide.repository;

import com.example.Inxhinieri.Softi.guide.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideRepository extends JpaRepository<Guide, String> {
}