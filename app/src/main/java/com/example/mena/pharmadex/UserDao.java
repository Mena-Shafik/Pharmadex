package com.example.mena.pharmadex;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Mena Shafik
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE PBN = :PBN")
    List<User> getSingleRecord(int PBN);

    @Update
    void updateRecord(User user);

    @Delete
    void delete(User user);


    @Insert
    void insertAll(List<User> user);

    @Query("DELETE FROM User")
    void deleteAllUsers();

    @Insert
    void insertMultipleRecord(User... user);

    @Insert
    void insertMultipleListRecord(List<User> user);

    @Insert
    void insertOnlySingleRecord(User user);
}
