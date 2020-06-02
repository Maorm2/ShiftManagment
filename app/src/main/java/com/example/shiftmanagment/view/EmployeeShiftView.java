package com.example.shiftmanagment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.example.shiftmanagment.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAdjusters;

import java.util.Calendar;
import java.util.Date;

public class EmployeeShiftView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_shift_view);

        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.state().edit().setMinimumDate(nextSunday)
                .setMaximumDate(CalendarDay.from(nextSunday.plusDays(7))).commit();
    }

}
