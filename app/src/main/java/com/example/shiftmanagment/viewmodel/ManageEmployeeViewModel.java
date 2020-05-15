package com.example.shiftmanagment.viewmodel;


import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;

public class ManageEmployeeViewModel extends ViewModel {

    private Database mDatabase = Database.getInstance();

    public void signUpUser(String email, String password, View v){
        mDatabase.createUser(email,password);
    }

}
