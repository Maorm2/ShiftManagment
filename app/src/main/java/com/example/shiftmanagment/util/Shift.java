package com.example.shiftmanagment.util;

import android.os.Parcel;
import android.os.Parcelable;

import org.threeten.bp.LocalDate;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Shift{

    private String date;
    private String timeInDay;

    public Shift() {}


    public Shift(String date, String timeInDay) {
        this.date = date;
        this.timeInDay = timeInDay;
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeInDay() {
        return timeInDay;
    }

    public void setTimeInDay(String timeInDay) {
        this.timeInDay = timeInDay;
    }

}