package com.example.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customer.database.AppDatabase;
import com.example.customer.database.Customer;

import java.util.Arrays;

public class UpdateCustomerActivity extends AppCompatActivity {
    EditText et_name,et_description;
    Spinner spinner_gender;

    String gender;

    AppDatabase db;

    Customer customer;

    Button bt_udpate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);
        db = AppDatabase.getAppDatabase(this);

        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        spinner_gender = findViewById(R.id.spiner_gender);
        bt_udpate = findViewById(R.id.bt_update);

        String[] listGender = {"Male","Female","Unknown"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listGender);
        spinner_gender.setAdapter(adapter);

        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = listGender[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Bundle bundle = getIntent().getExtras();
        customer = (Customer) bundle.get("customer");

        et_name.setText(customer.getName());
        et_description.setText(customer.getDescription());
        spinner_gender.setSelection(Arrays.asList(listGender).indexOf(customer.getGender()));

        bt_udpate.setOnClickListener(view -> {
            onUpdate();
        });
    }

    private void onUpdate() {
        if(!validate()) return;

        int id = db.customerDao().updateCustomer(customer.getId(),et_name.getText().toString(),gender,et_description.getText().toString());

        if(id > 0) {
            Log.d("TAG", "onUpdate: " + id);
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        }

        goHome();
    }

    private void goHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private boolean validate() {
        String mess = null;
        if(et_name.getText().toString().trim().isEmpty()) {
            mess = "name required";
        }
        else if(et_description.getText().toString().trim().isEmpty()) {
            mess = "description required";
        }

        if(mess != null) {
            Toast.makeText(this,mess, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}