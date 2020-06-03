package com.example.shiftmanagment.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.viewmodel.EmployeeShiftViewModel;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAdjusters;

import java.util.Calendar;
import java.util.Date;

public class EmployeeShiftView extends AppCompatActivity implements View.OnClickListener {

    private String shiftTime;
    private LocalDate shiftDate;
    private EmployeeShiftViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_shift_view);

        viewModel = new EmployeeShiftViewModel();

        ImageButton morningShift = findViewById(R.id.img_btn_morning);
        ImageButton eveningShift = findViewById(R.id.img_btn_evening);
        ImageButton nightShift = findViewById(R.id.img_btn_night);

        Button bunAddShift = findViewById(R.id.btn_add_shift);
        Button bunPlaceShifts = findViewById(R.id.btn_place_shifts);


        morningShift.setOnClickListener(this);
        eveningShift.setOnClickListener(this);
        nightShift.setOnClickListener(this);
        bunAddShift.setOnClickListener(this);
        bunPlaceShifts.setOnClickListener(this);



        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.state().edit().setMinimumDate(nextSunday)
                .setMaximumDate(CalendarDay.from(nextSunday.plusDays(7))).commit();


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                shiftDate = date.getDate();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_morning:
                shiftTime = "Morning";
                break;
            case R.id.img_btn_evening:
                shiftTime = "Evening";
                break;
            case R.id.img_btn_night:
                shiftTime = "Night";
                break;
            case R.id.btn_add_shift:
                viewModel.addShiftToWeek(shiftDate.toString(), shiftTime);
                break;
            case R.id.btn_place_shifts:
            viewModel.addWeekShiftToDb(new Callback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "Shifts have been sent to manager", Toast.LENGTH_LONG).show();
                }
            });
            break;
        }

    }

    public interface Callback{
        void onSuccess();
    }
}
