package com.example.shiftmanagment.viewmodel;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;

public class MainActivityViewModel extends ViewModel {

    private  Database mDatabase = Database.getInstance();

    public void signInUser(String username, String password, View v) {
        mDatabase.signInUser(username, password, v);
    }

}
