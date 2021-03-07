package com.dauut.EksiDebeFetcher.dao.datasource;

import com.dauut.EksiDebeFetcher.model.DebeAudit;
import com.dauut.EksiDebeFetcher.model.EntryAudit;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DebeRepository extends JpaRepository<DebeAudit, LocalDate> {
    List<DebeAudit> findByListDate(LocalDate date);
}
