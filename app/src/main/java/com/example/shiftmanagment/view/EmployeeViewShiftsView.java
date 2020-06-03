package com.example.shiftmanagment.view;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Shift;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.TemporalAdjusters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EmployeeViewShiftsView extends AppCompatActivity {

    private LocalDate shiftDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_shifts_view);

        final TextView textDate = findViewById(R.id.shift_view_text_date);
        final TextView textTime = findViewById(R.id.shift_view_text_time);

        final LinearLayout shiftContent = findViewById(R.id.view_shifts_shift_content);

        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        final MaterialCalendarView materialCalendarView = findViewById(R.id.emp_view_shifts_calendarView);
        materialCalendarView.state().edit().setMinimumDate(nextSunday)
                .setMaximumDate(CalendarDay.from(nextSunday.plusDays(7))).commit();


        final HashMap<String, Shift> shifts = new HashMap<>();
        shifts.put("2020-06-07" , new Shift(LocalDate.parse("2020-06-07"), "Morning"));
        shifts.put("2020-06-08" , new Shift(LocalDate.parse("2020-06-08"), "Evening"));
        shifts.put("2020-06-12" , new Shift(LocalDate.parse("2020-06-12"), "Morning"));
        shifts.put("2020-06-14" , new Shift(LocalDate.parse("2020-06-14"), "Night"));

        Collection<CalendarDay> dates = new ArrayList<>();

        for(Map.Entry entry : shifts.entrySet()){
            Shift shift = (Shift) entry.getValue();
            CalendarDay day = CalendarDay.from(shift.getDate());
            dates.add(day);
        }


        materialCalendarView.addDecorator(new EventDecorator(R.color.colorPrimary, dates));


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                shiftContent.setVisibility(View.VISIBLE);
                String dateToSet = date.getDate().toString();
                if(shifts.containsKey(dateToSet)) {
                    textDate.setText(dateToSet);
                    textTime.setText(shifts.get(dateToSet).getTimeInDay());
                }else{
                    shiftContent.setVisibility(View.GONE);
                }


            }
        });
    }

    class EventDecorator implements DayViewDecorator{

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(10, color));
        }
    }
}
