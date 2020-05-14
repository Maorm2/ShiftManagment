package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.viewmodel.AdminPageViewModel;

public class AdminPageView extends AppCompatActivity {

    private AdminPageViewModel viewModel = new AdminPageViewModel();
    private Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page_view);

        btnSignOut = findViewById(R.id.logOutAdminBtn);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.signOut();
                Intent intent = new Intent(AdminPageView.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
