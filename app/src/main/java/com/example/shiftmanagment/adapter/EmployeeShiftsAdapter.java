package com.example.shiftmanagment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.WeekShift;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EmployeeShiftsAdapter extends RecyclerView.Adapter<EmployeeShiftsAdapter.EmployeeShiftsViewHolder> {

    private WeekShift weekShifts;
    private List<String> dates;
    private ApproveShiftListener listener;

    public EmployeeShiftsAdapter(WeekShift weekShifts) {
        this.weekShifts = weekShifts;
        this.dates = new ArrayList(weekShifts.getShifts().keySet());
    }

    public interface ApproveShiftListener{
        void onApprove(Boolean isChecked ,String date,String shift);
    }

    public void setListener(ApproveShiftListener listener) {
        this.listener = listener;
    }

    public class EmployeeShiftsViewHolder extends RecyclerView.ViewHolder {

        TextView shiftRequestTv;
        CheckBox approveShiftCb;
        ImageView shiftIv;


        public EmployeeShiftsViewHolder(@NonNull View itemView) {
            super(itemView);

            shiftRequestTv = itemView.findViewById(R.id.shift_request_date);
            approveShiftCb = itemView.findViewById(R.id.shift_request_cb);
            shiftIv = itemView.findViewById(R.id.shift_image);

            approveShiftCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        String date = dates.get(getAdapterPosition());
                        listener.onApprove(approveShiftCb.isChecked(),date, weekShifts.getShifts().get(date));
                }
            });


        }
    }

    @NonNull
    @Override
    public EmployeeShiftsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_shifts_cell,parent,false);
        EmployeeShiftsViewHolder viewHolder = new EmployeeShiftsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeShiftsViewHolder holder, int position) {

        String date = dates.get(position);
        String shift = weekShifts.getShifts().get(date);
        holder.shiftRequestTv.setText(date + ": "+shift);
        holder.shiftIv.setImageResource(setImageView(shift));
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
    
    private int setImageView(String shift){
        int shiftRequest = 0;
        switch(shift){
            case "Evening":
                shiftRequest = R.drawable.evening_icon;
                break;
            case "Night":
                shiftRequest = R.drawable.night_icon;
                break;
            case "Morning":
                shiftRequest = R.drawable.morning_icon;
                break;
        }
        
        return shiftRequest;
    }



}
