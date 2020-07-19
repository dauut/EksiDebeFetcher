package com.dauut.EksiDebeFetcher.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class Debe {

    @NotNull
    private List<Entry> debeEntries;

    @NotNull
    private LocalDate date;
    private int entryCount;

    public Debe(@NotNull List<Entry> debeEntries, @NotNull LocalDate date, int entryCount) {
        this.debeEntries = debeEntries;
        this.date = date;
        this.entryCount = entryCount;
    }

    public @NotNull List<Entry> getDebeEntries() {
        return debeEntries;
    }

    public void setDebeEntries(@NotNull List<Entry> debeEntries) {
        this.debeEntries = debeEntries;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }
}
