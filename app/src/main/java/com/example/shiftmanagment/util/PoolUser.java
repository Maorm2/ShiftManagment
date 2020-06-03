package com.example.shiftmanagment.util;

public class PoolUser {

    private String firstName;
    private String lastName;
    private String Uid;
    private WeekShift weekShift;

    public PoolUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public WeekShift getWeekShift() {
        return weekShift;
    }

    public void setWeekShift(WeekShift weekShift) {
        this.weekShift = weekShift;
    }
}
