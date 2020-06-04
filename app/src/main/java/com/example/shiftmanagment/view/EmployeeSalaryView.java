package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Shift;

import java.util.Calendar;
import java.util.List;

public class EmployeeSalaryView extends AppCompatActivity {

    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_salary_view);

        final TextView dateFrom = findViewById(R.id.text_date_from);
        final TextView toDate = findViewById(R.id.text_to_date);
        Button btnGetShifts = findViewById(R.id.btn_salary_get);


        dateFrom.setInputType(InputType.TYPE_NULL);
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(EmployeeSalaryView.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateFrom.setText( year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        toDate.setInputType(InputType.TYPE_NULL);
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(EmployeeSalaryView.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                toDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btnGetShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = dateFrom.getText().toString();
                String to = toDate.getText().toString();



            }
        });



    }

    public interface Callback{
        void onGetShitCallback(List<Shift> shifts);
    }
}
