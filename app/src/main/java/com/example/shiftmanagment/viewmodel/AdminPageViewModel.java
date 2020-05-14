package com.example.shiftmanagment.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.shiftmanagment.database.Database;

public class AdminPageViewModel extends ViewModel {

    private Database database = Database.getInstance();

    //Methods:
    public void signOut(){
        database.signOutUser();
    }
}
