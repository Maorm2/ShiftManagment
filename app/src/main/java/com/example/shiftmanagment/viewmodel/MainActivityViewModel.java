package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;

public class MainActivityViewModel extends ViewModel {

    private  Database database = Database.getInstance();

}
