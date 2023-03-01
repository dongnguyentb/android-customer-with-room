package com.example.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.customer.database.AppDatabase;
import com.example.customer.database.Customer;

public class AddCustomerActivity extends AppCompatActivity {
    EditText et_name,et_description;
    Spinner spinner_gender;
    CheckBox check_agree;
    Button bt_register;
    String gender = "Male";
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        db = AppDatabase.getAppDatabase(this);

        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        spinner_gender = findViewById(R.id.spiner_gender);
        bt_register = findViewById(R.id.bt_register);
        check_agree = findViewById(R.id.checkbox_agree);
        spinner_gender = findViewById(R.id.spiner_gender);

        String[] listGender = {"Male","Female","Unknown"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listGender);
        spinner_gender.setAdapter(adapter);

        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemSelected: " + listGender[i]);
                gender = listGender[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt_register.setOnClickListener(view -> {
            onRegister();
        });
    }

    private void onRegister() {
        if(!validate()) return;

        Customer customer = new Customer();
        customer.setName(et_name.getText().toString());
        customer.setGender(gender);
        customer.setDescription(et_description.getText().toString());

        long id = db.customerDao().insertCustomer(customer);
        if(id > 0) {
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
        else if(!check_agree.isChecked()) {
            mess = "you must agree to the terms of use";
        }

        if(mess != null) {
            Toast.makeText(this,mess, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}