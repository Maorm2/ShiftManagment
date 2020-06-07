package com.example.shiftmanagment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.kalert.KAlertDialog;
import com.example.shiftmanagment.R;
import com.example.shiftmanagment.adapter.EmployeeShiftsAdapter;
import com.example.shiftmanagment.util.Employee;
import com.example.shiftmanagment.util.PoolUser;
import com.example.shiftmanagment.view.AdminPageView;
import com.example.shiftmanagment.view.ManageShiftsView;
import com.example.shiftmanagment.viewmodel.ManageShiftsViewModel;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public class ManageShiftsFragment extends Fragment {

    private RecyclerView recyclerView;
    private PoolUser user;
    private EmployeeShiftsAdapter adapter;
    private HashMap<String,String> shifts = new HashMap<>();
    private ManageShiftsViewModel viewModel = new ManageShiftsViewModel();

    public ManageShiftsFragment(PoolUser user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.employee_shifts_list_requests,container,false);

        Button applyBtn = view.findViewById(R.id.manager_requests_apply_btn);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    viewModel.shiftsApproved(shifts,user);
                    successDialog();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview_employee_shifts_requests);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EmployeeShiftsAdapter(user.getWeekShift());


        recyclerView.setAdapter(adapter);

        return view;
    }


    private void successDialog() {
        new KAlertDialog(getContext(), KAlertDialog.SUCCESS_TYPE)
                .setConfirmText(getString(R.string.confirm_button))
                .confirmButtonColor(R.color.colorPrimaryDark)
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        Intent intent = new Intent(getContext(), ManageShiftsView.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter.setListener(new EmployeeShiftsAdapter.ApproveShiftListener() {
            @Override
            public void onApprove(Boolean isChecked,String date, String shiftTime) {
                if(isChecked){
                    shifts.put(date,shiftTime);
                }
                else{
                    shifts.remove(date);
                }
            }
        });

    }
}
