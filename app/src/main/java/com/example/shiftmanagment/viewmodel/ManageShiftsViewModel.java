package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;

public class ManageShiftsViewModel extends ViewModel {

    private Database database = Database.getInstance();

}
