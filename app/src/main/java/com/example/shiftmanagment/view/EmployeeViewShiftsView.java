package com.example.shiftmanagment.view;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Shift;
import com.example.shiftmanagment.viewmodel.EmployeeViewShiftsViewModel;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.WeekFields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class EmployeeViewShiftsView extends AppCompatActivity {

    private LocalDate shiftDate;
    private EmployeeViewShiftsViewModel viewModel;
    private HashMap<String, String> shiftsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_shifts_view);

        viewModel = new EmployeeViewShiftsViewModel();

        final TextView textDate = findViewById(R.id.shift_view_text_date);
        final TextView textTime = findViewById(R.id.shift_view_text_time);

        final LinearLayout shiftContent = findViewById(R.id.view_shifts_shift_content);

        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        final MaterialCalendarView materialCalendarView = findViewById(R.id.emp_view_shifts_calendarView);
        materialCalendarView.state().edit().setMinimumDate(nextSunday)
                .setMaximumDate(CalendarDay.from(nextSunday.plusDays(7))).commit();

        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.US).dayOfWeek();



        LocalDate sunday = now.with(fieldISO, 1).plusDays(7);
        LocalDate next = sunday.plusDays(14);

        String fromDate = sunday.toString();

        String[] fromDatesplit = fromDate.split("-");
        String newFromDate = fromDatesplit[2]+ "-" + fromDatesplit[1]+ "-" +fromDatesplit[0];

        String toDate = next.toString();

        String[] toDateSplit = toDate.split("-");
        String newToDate = toDateSplit[2]+ "-" + toDateSplit[1]+ "-" +toDateSplit[0];

        Log.d("TAG", "from date " + newFromDate + "to date: "+ newToDate);

        viewModel.getShiftsForCurrentWeek(newFromDate, newToDate, new Callback() {
            @Override
            public void onCallback(HashMap<String, String> shifts) {
                shiftsList = shifts;
                Collection<CalendarDay> dates = new ArrayList<>();

                for(Map.Entry entry : shiftsList.entrySet()){
                     CalendarDay day = CalendarDay.from(LocalDate.parse(entry.getKey().toString()));
                      dates.add(day);
                }
                materialCalendarView.addDecorator(new EventDecorator(R.color.colorPrimary, dates));
            }
        });



        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                shiftContent.setVisibility(View.VISIBLE);
                String dateToSet = date.getDate().toString();
                if(shiftsList.containsKey(dateToSet)) {
                    textDate.setText(dateToSet);
                    textTime.setText(shiftsList.get(dateToSet));
                }else{
                    shiftContent.setVisibility(View.GONE);
                }
            }
        });

        Button btnAddToCalendar = findViewById(R.id.btn_add_my_calendar);
        btnAddToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(Map.Entry entry : shiftsList.entrySet()) {


                    String[] parsedDate = entry.getKey().toString().split("-");
                    int startTime = 0;
                    int timeEnd = 8;
                    switch (entry.getValue().toString()){
                        case"Morning":
                            startTime = 8;
                            timeEnd = 16;
                            break;

                        case"Evening":
                            startTime = 16;
                            timeEnd = 24;
                            break;

                        case"Night":
                            startTime = 0;
                            timeEnd = 8;
                            break;

                    }


                    long calID = 1; // Make sure to which calender you want to add event
                    long startMillis = 0;
                    long endMillis = 0;
                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(Integer.parseInt(parsedDate[0]), Integer.parseInt(parsedDate[1])-1, Integer.parseInt(parsedDate[2]), startTime, 0);
                    startMillis = beginTime.getTimeInMillis();
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(Integer.parseInt(parsedDate[0]), Integer.parseInt(parsedDate[1])-1, Integer.parseInt(parsedDate[2]), timeEnd, 0);
                    endMillis = endTime.getTimeInMillis();


                    ContentResolver cr = getContentResolver();
                    ContentValues values = new ContentValues();
                    values.put(CalendarContract.Events.DTSTART, startMillis);
                    values.put(CalendarContract.Events.DTEND, endMillis);
                    values.put(CalendarContract.Events.TITLE,  entry.getValue() +" Shift");
                    values.put(CalendarContract.Events.CALENDAR_ID, calID);
                    values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
                    Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

                    // get the event ID that is the last element in the Uri
                    long eventID = Long.parseLong(uri.getLastPathSegment());
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

    public interface Callback{
        void onCallback(HashMap<String, String> shifts);
    }

}
