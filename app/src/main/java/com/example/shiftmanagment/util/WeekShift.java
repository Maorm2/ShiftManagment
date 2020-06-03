package com.example.shiftmanagment.util;

import java.util.HashMap;

public class WeekShift  {

    private HashMap<String, String> shifts = new HashMap<>();

    public WeekShift(){}

    public HashMap<String, String> getShifts() {
        return shifts;
    }

    public void setShifts(HashMap<String, String> shifts) {
        this.shifts = shifts;
    }


    public void addToShifts(String date, String shiftTime){
        shifts.put(date, shiftTime);
    }
}
