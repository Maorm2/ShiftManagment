package com.example.shiftmanagment.util;

import org.threeten.bp.LocalDate;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Shift {

    private LocalDate date;
    private String timeInDay;

    public Shift() {}

    public Shift(LocalDate date, String timeInDay) {
        this.date = date;
        this.timeInDay = timeInDay;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeInDay() {
        return timeInDay;
    }

    public void setTimeInDay(String timeInDay) {
        this.timeInDay = timeInDay;
    }
}