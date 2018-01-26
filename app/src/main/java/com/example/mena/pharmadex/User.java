package com.example.mena.pharmadex;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Mena Shafik
 */

@Entity(tableName = "User")
public class User {
    @PrimaryKey
    @ColumnInfo(name = "PBN")
    public int PBN;
    @ColumnInfo(name = "username")
    public String userName;
    @ColumnInfo(name = "password")
    public String password;
    @ColumnInfo(name = "full_name")
    public String name;
    @ColumnInfo(name = "EDT")
    public String EDT;
    @ColumnInfo(name = "location_Code")
    public String locationCode;
    @ColumnInfo(name = "locationName")
    public String locationName;
    @ColumnInfo(name = "locationAddress")
    public String address;
    @ColumnInfo(name = "dcode")
    public String dCode;


    public User(int PBN, String userName, String password, String name, String EDT, String locationCode, String locationName, String address) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.PBN = PBN;
        this.EDT = EDT;
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.address = address;
    }
    @Ignore
    public User(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }
    @Ignore
    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public int getPBN() {
        return PBN;
    }

    public void setPBN(int PBN) {
        this.PBN = PBN;
    }

    public String getEDT() {
        return EDT;
    }

    public void setEDT(String EDT) {
        this.EDT = EDT;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return String.format("PBN: %s, userName: %s,  Password: %s, Name: %s, EDT: %s, Location Code: %s, Location: %s, Address %s \n", PBN, userName, password, name,  EDT, locationCode, locationName, address);
    }
}
