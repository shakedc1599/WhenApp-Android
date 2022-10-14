package com.example.whenappandroid.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whenappandroid.Data.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAll();

    @Query("SELECT * FROM contact WHERE id =:id")
    LiveData<Contact> get(String id);

    @Query("SELECT * FROM contact WHERE id =:id")
    Contact getContact(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact... contacts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Contact> order);

    @Update
    void update(Contact... contacts);

    @Query("DELETE FROM contact")
    void deleteAll();

    @Delete
    void delete(Contact... contacts);

}
