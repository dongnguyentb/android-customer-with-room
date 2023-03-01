package com.example.customer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer.AddCustomerActivity;
import com.example.customer.R;
import com.example.customer.UpdateCustomerActivity;
import com.example.customer.database.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Customer> customers;


    public CustomerAdapter(Activity activity,List<Customer> customers) {
        this.customers = customers;
        this.activity = activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.recyclerview_customer_item,parent,false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomerViewHolder vh = (CustomerViewHolder) holder;
        Customer model = customers.get(position);

        vh.tv_id.setText(String.valueOf(model.getId()));
        vh.tv_name.setText(model.getName());
        vh.tv_gender.setText(model.getGender());

        vh.customer_item.setOnClickListener(view -> {
            onClickToUpdateActivity(model);
        });
    }

    private void onClickToUpdateActivity(Customer customer) {
        Intent intent = new Intent(activity,UpdateCustomerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("customer",customer);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id,tv_name,tv_gender;
        LinearLayout customer_item;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_gender = itemView.findViewById(R.id.tv_gender);
            customer_item = itemView.findViewById(R.id.customer_item);
        }
    }
}
