package com.example.shiftmanagment.view;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class ManageShiftsView extends AppCompatActivity {

    private ManageShiftsViewModel viewModel = new ManageShiftsViewModel();
    private  RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emloyee_list_requests);

        recyclerView = findViewById(R.id.recyclerview_employee_requests);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        getRequestList();

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
                setContentView(R.layout.employee_requests_fragment);
                ManageShiftsFragment fragment = new ManageShiftsFragment(user);
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.fragmentContainer,fragment);
                transaction.commit();
            }
        });
    }


    public interface onCallback{
        void setRequestList(List<PoolUser> employees);
    }
}
