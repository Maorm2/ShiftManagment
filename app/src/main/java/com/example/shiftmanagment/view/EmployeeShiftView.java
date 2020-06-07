package com.example.shiftmanagment.view;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioGroup;
import com.developer.kalert.KAlertDialog;
import com.example.shiftmanagment.R;
import com.example.shiftmanagment.viewmodel.EmployeeShiftViewModel;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;;
import android.content.Intent;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.TemporalAdjusters;

public class EmployeeShiftView extends AppCompatActivity implements View.OnClickListener {

    private String shiftTime;
    private LocalDate shiftDate;
    private EmployeeShiftViewModel viewModel;
    private RadioButton morningShift;
    private RadioButton eveningShift;
    private RadioButton nightShift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_shift_view);

        viewModel = new EmployeeShiftViewModel();

        morningShift = findViewById(R.id.img_btn_morning);
        eveningShift = findViewById(R.id.img_btn_evening);
        nightShift = findViewById(R.id.img_btn_night);

        Button bunAddShift = findViewById(R.id.btn_add_shift);
        Button bunPlaceShifts = findViewById(R.id.btn_place_shifts);


        morningShift.setOnClickListener(this);
        eveningShift.setOnClickListener(this);
        nightShift.setOnClickListener(this);
        bunAddShift.setOnClickListener(this);
        bunPlaceShifts.setOnClickListener(this);

     /*   viewModel.getPublishShifts(new ManageShiftsView.OnCallbackShifts() {
            @Override
            public void setPublishShifts(boolean isPublish) {
                if(!isPublish){
                    // cancel the request shifts button
                }

            }
        });*/

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
        switch (v.getId()) {
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
                Toast.makeText(this, "Shift added", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_place_shifts:
            viewModel.addWeekShiftToDb(new Callback() {
                @Override
                public void onSuccess() {
                    successDialog();
                }
            });
            break;
        }

    }

    private void successDialog() {
        new KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
                .setTitleText(getString(R.string.shifts_send_confirmation))
                .setContentText(getString(R.string.shifts_send_confirmation_info))
                .setConfirmText(getString(R.string.confirm_button))
                .confirmButtonColor(R.color.colorPrimaryDark)
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        finish();
                    }
                })
                .show();
    }


    public interface Callback {
        void onSuccess();
    }

}
