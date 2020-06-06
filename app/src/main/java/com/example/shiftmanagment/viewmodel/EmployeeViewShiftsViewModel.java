package com.example.shiftmanagment.viewmodel;

import com.example.shiftmanagment.database.Database;
import com.example.shiftmanagment.view.EmployeeSalaryView;
import com.example.shiftmanagment.view.EmployeeViewShiftsView;

public class EmployeeViewShiftsViewModel {

    Database db = Database.getInstance();

    public void getShiftsForCurrentWeek(String from, String to, EmployeeViewShiftsView.Callback callback){
            db.getHashMapShiftByDate(from, to, callback);
    }
}
