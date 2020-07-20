package com.dauut.EksiDebeFetcher.dao.datasource;

import com.dauut.EksiDebeFetcher.model.DebeAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DebeRepository extends JpaRepository<DebeAudit, LocalDate> {
}
