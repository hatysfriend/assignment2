package com.example.assignment2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.example.assignment2.Model.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    //create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact... contacts);

    //update
    @Update
    void update(Contact... contacts);

    //delete
    @Delete
    void delete(Contact... contacts);

    //delete all
    @Query("DELETE FROM contact")
    void clearTable();

    //read all
    @Query("SELECT * FROM contact")
    List<Contact> getAllContacts();

    //read by id
    @Query("SELECT * FROM contact WHERE id = :contactID")
    Contact getContactById(int contactID);


}
