package com.dauut.EksiDebeFetcher.dao.datasource;

import com.dauut.EksiDebeFetcher.model.EntryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<EntryAudit, Integer> {

}
