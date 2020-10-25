package com.niteshb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.niteshb.activities.R;
import com.niteshb.models.JobModel;
import com.niteshb.models.LoginResponseModel;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.MyViewHolder> {
    private ArrayList<JobModel> resultModelArrayList;
    private Context mContext;

    public JobsAdapter(ArrayList<JobModel> resultModelArrayList, Context mContext) {
        this.resultModelArrayList = resultModelArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.job_recycler_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mJobNumber.setText(String.format("Job no: %s", resultModelArrayList.get(position).getJobNo()));
        holder.mCompanyName.setText(resultModelArrayList.get(position).getCustomerCompanyName());
    }

    @Override
    public int getItemCount() {
        return resultModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mJobNumber, mCompanyName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mJobNumber = itemView.findViewById(R.id.job_recycler_number);
            mCompanyName = itemView.findViewById(R.id.job_recycler_company);


        }
    }
}
