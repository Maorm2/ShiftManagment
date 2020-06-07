package com.example.shiftmanagment.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.kalert.KAlertDialog;
import com.example.shiftmanagment.R;
import com.example.shiftmanagment.adapter.EmployeeRequestsAdapter;
import com.example.shiftmanagment.fragment.ManageEmployeeFragment;
import com.example.shiftmanagment.fragment.ManageShiftsFragment;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.util.PoolUser;
import com.example.shiftmanagment.util.WeekShift;
import com.example.shiftmanagment.viewmodel.ManageShiftsViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ManageShiftsView<isShiftsPublished> extends AppCompatActivity {

    private ManageShiftsViewModel viewModel = new ManageShiftsViewModel();
    private  RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private boolean isShiftsPublished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emloyee_list_requests);

        viewModel.getPublishShifts(new OnCallbackShifts() {
            @Override
            public void setPublishShifts(boolean isPublish) {
                isShiftsPublished = isPublish;
            }
        });

        final Button publishShiftsBtn = findViewById(R.id.publish_shifts_btn);
        publishShiftsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isShiftsPublished) {
                    publishShiftsBtn.setText("Unlock requests shifts");
                    isShiftsPublished = true;
                    successDialog();
                }
                else {
                    publishShiftsBtn.setText("Publish shifts and Lock requests");
                    isShiftsPublished = false;
                    viewModel.clearShifts();
                    infoDialog();
                }
                viewModel.publishShifts(isShiftsPublished);
            }
        });

        recyclerView = findViewById(R.id.recyclerview_employee_requests);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        getRequestList();

    }

    private void infoDialog() {
        new KAlertDialog(this, KAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.requests_shifts_allowed))
                .setContentText(getString(R.string.request_shifts_info))
                .setConfirmText(getString(R.string.confirm_button))
                .confirmButtonColor(R.color.colorPrimaryDark)
                .show();
    }

    private void successDialog() {
        new KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
                .setTitleText(getString(R.string.shifts_published_success))
                .setContentText(getString(R.string.shift_publish_succsses_info))
                .setConfirmText(getString(R.string.confirm_button))
                .confirmButtonColor(R.color.colorPrimaryDark)
                .show();
    }

    public void getRequestList(){
        viewModel.getList(new onCallback() {
            @Override
            public void setRequestList(List<PoolUser> employees) {
                initRecyclerView(employees);
            }
        });

    }

    public void initRecyclerView(final List<PoolUser> employeeRequest){
        EmployeeRequestsAdapter adapter = new EmployeeRequestsAdapter(employeeRequest);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new EmployeeRequestsAdapter.RequestListener() {
            @Override
            public void onClick(PoolUser user) {
                ManageShiftsFragment fragment = new ManageShiftsFragment(user);
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.add(R.id.request_list_fragment,fragment);
                transaction.commit();
            }
        });
    }


    public interface onCallback{
        void setRequestList(List<PoolUser> employees);
    }
    public interface OnCallbackShifts{
        void setPublishShifts(boolean isPublish);
    }
}
