package com.dauut.EksiDebeFetcher.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "debe_list")
@EntityListeners(AuditingEntityListener.class)
public class DebeAudit {
    @Id
    @NotNull
    @Column(name = "list_date")
    private LocalDate listDate;

    @Column(name = "entry_count")
    private int entryCount;

    @NotNull
    @Column(name = "list_creation_time")
    private LocalDateTime listCreationTime;

    public @NotNull LocalDate getListDate() {
        return listDate;
    }

    public void setListDate(@NotNull LocalDate listDate) {
        this.listDate = listDate;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    public @NotNull LocalDateTime getListCreationTime() {
        return listCreationTime;
    }

    public void setListCreationTime(@NotNull LocalDateTime listCreationTime) {
        this.listCreationTime = listCreationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebeAudit debeAudit = (DebeAudit) o;
        return entryCount == debeAudit.entryCount &&
                listDate.equals(debeAudit.listDate) &&
                Objects.equals(listCreationTime, debeAudit.listCreationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listDate, entryCount, listCreationTime);
    }
}
