package com.example.shiftmanagment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.shiftmanagment.R;
import com.example.shiftmanagment.util.PoolUser;

import java.util.List;

public class EmployeeRequestsAdapter extends
        RecyclerView.Adapter<EmployeeRequestsAdapter.EmployeeRequestsViewHolder> {


    private List<PoolUser> requestList;
    private RequestListener listener;

    public EmployeeRequestsAdapter(List<PoolUser> requestList){
        this.requestList = requestList;
    }

    public interface RequestListener{
        void onClick(PoolUser user);
    }

    public void setListener(RequestListener listener){
        this.listener = listener;
    }


    public class EmployeeRequestsViewHolder extends RecyclerView.ViewHolder{

        TextView nameTv;
        ImageView employeeIv;
        LinearLayout linearLayoutCell;

        public EmployeeRequestsViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayoutCell = itemView.findViewById(R.id.linearlayout_cell_requests);
            nameTv = itemView.findViewById(R.id.name_request_list_et);
            employeeIv = itemView.findViewById(R.id.employee_cell_image);


            circleImage(itemView,employeeIv);

            linearLayoutCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(requestList.get(getAdapterPosition()));
                }
            });

        }
    }

    @NonNull
    @Override
    public EmployeeRequestsAdapter.EmployeeRequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cell_requests,parent,false);
        EmployeeRequestsViewHolder viewHolder = new EmployeeRequestsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeRequestsViewHolder holder, int position) {
        PoolUser employee = requestList.get(position);
        holder.nameTv.setText(employee.getFirstName()
                + " " + employee.getLastName());


    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public void circleImage(View v, ImageView image){
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
