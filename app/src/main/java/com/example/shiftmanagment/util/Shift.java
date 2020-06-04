package com.example.shiftmanagment.util;

import android.os.Parcel;
import android.os.Parcelable;

import org.threeten.bp.LocalDate;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Shift  implements Parcelable {

    private String date;
    private String timeInDay;
    private Date timestamp;

    public Shift() {}


    public Shift(String date, String timeInDay) {
        this.date = date;
        this.timeInDay = timeInDay;
        timestamp = new Date(System.currentTimeMillis());
    }


    protected Shift(Parcel in) {
        timeInDay = in.readString();
        date = in.readString();
        timestamp = (Date)in.readValue(Date.class.getClassLoader());

    }

    public static final Creator<Shift> CREATOR = new Creator<Shift>() {
        @Override
        public Shift createFromParcel(Parcel in) {
            return new Shift(in);
        }

        @Override
        public Shift[] newArray(int size) {
            return new Shift[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(timeInDay);
        dest.writeString(date);
        dest.writeValue(timestamp);
    }
}