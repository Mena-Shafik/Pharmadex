package com.example.mena.pharmadex;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
/**
 * Created by Marco Ross
 */
public class RegisterActivity extends AppCompatActivity {
    Gson gson = new Gson();

    private UserDatabase userDatabase;

    int  phyBillingNum ;
    String userName;
    String password;
    String  fullName;
    String  elecDataTrans;
    String  locationCode;
    String locationName;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDatabase = Room.databaseBuilder(getApplicationContext(),UserDatabase.class, "user-db").build();

        //new AddToUserDatabaseAsync().execute();


        Button regBtn = (Button) findViewById(R.id.RegisterButton);
        final EditText editText1 = (EditText) findViewById(R.id.UserName);
        final EditText editText2 = (EditText) findViewById(R.id.Password);
        final EditText editText3 = (EditText) findViewById(R.id.PhyBillingNum);
        final EditText editText4 = (EditText) findViewById(R.id.FullName);
        final EditText editText5 = (EditText) findViewById(R.id.ElecDataTrans);
        final EditText editText6 = (EditText) findViewById(R.id.LocationCode);
        final EditText editText7 = (EditText) findViewById(R.id.LocationName);
        final EditText editText8 = (EditText) findViewById(R.id.LocationName);


        final List<User> theUsers;

        //json file to string
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.i("json", json.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        // json string to user array object
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        theUsers = (List<User>) gson.fromJson(json, listType);


        regBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             phyBillingNum = Integer.parseInt(editText3.getText().toString());
             userName = editText1.getText().toString();
             password = editText2.getText().toString();
             fullName = editText4.getText().toString();
             elecDataTrans = editText5.getText().toString();
             locationCode = editText6.getText().toString();
             locationName = editText7.getText().toString();
             address = editText8.getText().toString();

             User registeringUser = new User(phyBillingNum, userName, password, fullName,  elecDataTrans, locationCode, locationName, address);
             // add new user to array
             theUsers.add(registeringUser);

             //convert back to string
             String newJson = gson.toJson(theUsers);
             new AddToUserDatabaseAsync().execute();
             Toast.makeText(getApplicationContext(),"Registration Complete",Toast.LENGTH_SHORT).show();
             Log.i("newData", registeringUser.toString());

             //for (int i = 0; i < theUsers.size(); i++) {
             //    Log.i("users", theUsers.toString());
             //}

             /*FileOutputStream outputStream;;

             try {
                 outputStream = openFileOutput("data.json", Context.MODE_PRIVATE);
                 outputStream.write(newJson.getBytes());
                 outputStream.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }*/

            }
        });


    }

    private class AddToUserDatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setTitle("Register");
            //Perform pre-adding operation here.
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Let's add some dummy data to the database.
            //User user = new User();
            //user.setName("Magdin");

            User user1 = new User(phyBillingNum, userName ,password, fullName,  elecDataTrans, locationCode, locationName, address);
            //User user2 = new User(10001,"marco","1234","Marco Ross",  "450088", "B209");

            //Now access all the methods defined in DaoAccess with sampleDatabase object
            userDatabase.userDaoAccess().insertOnlySingleRecord(user1);
            //userDatabase.userDaoAccess().insertOnlySingleRecord(user2);

            //users = userDatabase.userDaoAccess().getAll();
            Log.i("users", user1.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //To after addition operation here.
        }
    }
}
