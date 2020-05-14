package com.example.shiftmanagment.util;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Shift {

    private Time startTime;
    private Time endTime;
    private float duration;

    public Shift(Time startTime, Time endTime ){
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime.compareTo(startTime);

    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
