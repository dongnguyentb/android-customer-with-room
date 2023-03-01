package com.example.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.customer.adapter.CustomerAdapter;
import com.example.customer.database.AppDatabase;
import com.example.customer.database.Customer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rvAddCustomer;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        db = AppDatabase.getAppDatabase(this);
        List<Customer> customerList = db.customerDao().getAllCustomer();

        CustomerAdapter customerAdapter = new CustomerAdapter(this,customerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvAddCustomer = findViewById(R.id.rv_addCustomer);
        rvAddCustomer.setLayoutManager(layoutManager);
        rvAddCustomer.setAdapter(customerAdapter);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this,AddCustomerActivity.class);
            startActivity(intent);
        });
    }
}