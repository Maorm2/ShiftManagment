package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;

public class EmployeeShiftViewModel extends ViewModel {

    private Database database = Database.getInstance();

}
