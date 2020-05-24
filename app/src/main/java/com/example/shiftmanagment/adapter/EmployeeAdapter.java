package com.example.shiftmanagment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;


    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{

        TextView nameTv;
        TextView salaryTv;
        ImageView employeeImage;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.employee_fullName);
            salaryTv = itemView.findViewById(R.id.employee_salary);
            employeeImage = itemView.findViewById(R.id.employee_image);

            circleImage(itemView,employeeImage);
        }
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_cell,parent,false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(view);

        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.nameTv.setText(employee.getFirstName()+ " " + employee.getLastName());
        holder.salaryTv.setText(employee.getSalary()+"$");

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    // Making the employee image as a circle image

    public void circleImage(View v,ImageView image){
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .centerInside()
                .transform(new CircleCrop());


        Glide.with(v)
                .load(v.getResources()
                        .getIdentifier("employee_default", "drawable", v.getContext().getPackageName()))
                .thumbnail(0.9f)
                .apply(options)
                .into(image);
    }
}
