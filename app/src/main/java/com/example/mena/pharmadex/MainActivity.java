package com.example.mena.pharmadex;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Ross
 */
public class MainActivity extends AppCompatActivity {


    private UserDatabase userDatabase;
    List<User> users;
    //ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDatabase = Room.databaseBuilder(getApplicationContext(),UserDatabase.class, "user-db").fallbackToDestructiveMigration().build();

        new UserDatabaseAsync().execute();
        //Log.i("users", users.toString());



        Button loginBtn = (Button) findViewById(R.id.LoginButton);
        Button registerBtn = (Button) findViewById(R.id.RegisterButton);
        final EditText userName = (EditText) findViewById(R.id.uName);
        final EditText password = (EditText) findViewById(R.id.password);

        String json = null;


        Gson gson = new Gson();

        Type listType = new TypeToken<List<User>>() {}.getType();
        //users = (List<User>) gson.fromJson(json, listType);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                int PBN;
                String EDT;
                String locationCode;
                String location;
                String address;
                for (int i = 0; i < users.size(); i++) {
                    if (userName.getText().toString().equals(users.get(i).getUserName())) {
                        //Log.i("password", password.getText().toString());
                        if (password.getText().toString().equals(users.get(i).getPassword())) {
                            name = users.get(i).getName();
                            PBN = users.get(i).getPBN();
                            EDT = users.get(i).getEDT();
                            locationCode = users.get(i).getLocationCode();
                            location = users.get(i).getLocationName();
                            address = users.get(i).getAddress();
                            Bundle bundle = new Bundle();

                            bundle.putString("name", name);
                            bundle.putInt("PBN", PBN);
                            bundle.putString("EDT", EDT);
                            bundle.putString("location", locationCode);
                            bundle.putString("locationCode", locationCode);
                            bundle.putString("location", location);
                            bundle.putString("address",address);
                            Log.i("users", users.toString());
                            Log.i("Login Info","");
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                        else
                            Toast.makeText(getApplicationContext(),"Username or Password Incorrect",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }


    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private class UserDatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Perform pre-adding operation here.
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Let's add some dummy data to the database.
            //User user = new User();
            //user.setName("Magdin");


            User user1 = new User(10000,"mena","1234","Mena Shafik",  "450089", "B208", "St.Mary & St. Mark Pharmacy","800 Covert Drive");
            User user2 = new User(10001,"marco","1234","Marco Ross",  "450088", "B209", "Oakville Hospital","325 Webb Drive");

            //Now access all the methods defined in DaoAccess with sampleDatabase object

            // in case I added something i didn't want in the DB. For test purposes ONLY.
            userDatabase.userDaoAccess().deleteAllUsers();

            //adding test users to DB
            userDatabase.userDaoAccess().insertOnlySingleRecord(user1);
            userDatabase.userDaoAccess().insertOnlySingleRecord(user2);

            users = userDatabase.userDaoAccess().getAll();
            Log.i("users", users.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //To after addition operation here.
        }
    }
}






