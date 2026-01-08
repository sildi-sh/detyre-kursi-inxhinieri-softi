package com.example.Inxhinieri.Softi.report.repository;

import com.example.Inxhinieri.Softi.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {
}