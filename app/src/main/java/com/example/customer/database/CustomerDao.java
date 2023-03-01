package com.example.customer.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM customer")
    List<Customer> getAllCustomer();

    @Insert
    void insertAllCustomer(Customer... customers);
    @Insert
    long insertCustomer(Customer customer);

    @Query("UPDATE customer SET name = :name, gender = :gender, description= :description WHERE id = :id")
    int updateCustomer(int id,String name,String gender,String description);


    @Delete
    void deleteCustomer(Customer customer);

}
