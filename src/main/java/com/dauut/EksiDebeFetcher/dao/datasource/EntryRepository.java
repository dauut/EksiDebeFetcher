package com.dauut.EksiDebeFetcher.dao.datasource;

import com.dauut.EksiDebeFetcher.model.EntryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<EntryAudit, Integer> {
    List<EntryAudit> findByDate(LocalDate date);
}
