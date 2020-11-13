package com.dauut.EksiDebeFetcher.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "debe_entries")
@EntityListeners(AuditingEntityListener.class)
public class EntryAudit {
    @Id
    @Column(name = "entry_id")
    private int entryId;

    @Column(name = "entry_date")
    private LocalDate date;

    @Column(name = "entry_url")
    private String url;

    @Column(name = "entry_header")
    private String header;

    @Column(name = "author")
    private String author;

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryAudit that = (EntryAudit) o;
        return entryId == that.entryId &&
                Objects.equals(date, that.date) &&
                Objects.equals(url, that.url) &&
                Objects.equals(header, that.header) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, date, url, header, author);
    }
}
