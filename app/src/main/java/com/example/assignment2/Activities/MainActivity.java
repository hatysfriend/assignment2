package com.example.assignment2.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GestureDetectorCompat;

import com.example.assignment2.Database.PhonebookDb;
import com.example.assignment2.Database.RetrofitServices;
import com.example.assignment2.Model.Contact;
import com.example.assignment2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RetrofitServices.ResultsHandler{

    public static final String MYPREFRENCES = "nightModePrefs";
    public static final String KEY_ISNIGHTMODE = "isNightMode";
    SharedPreferences sharedPreferences;

    private GestureDetectorCompat mDetector;
    private Contact contact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Welcome Page");

        //For Swipe Gesture
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        //Shared Theme - get sharedPrefences Boolean value from saved SharedPrefrences
        sharedPreferences = getSharedPreferences(MYPREFRENCES, Context.MODE_PRIVATE);

        //CHECK SAVED THEME
        checkNightModeActivated();//makes onCreate run twice?
        setDark();
        setLight();
        CameraButton();

        UpdateServerSideDBButton();


    }

    private void UpdateServerSideDBButton(){
        findViewById(R.id.btn_updateServerSide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLocalDBtoServerSide();
            }
        });
    }
    //Send Local DB to Server-side DB
    private void sendLocalDBtoServerSide() {
        try {
                ArrayList<Contact> allContacts = (ArrayList<Contact>) PhonebookDb.getInstance(this).contactDao().getAllContacts();
                for (Contact contact : allContacts) {
                    RetrofitServices.getInstance().AddAContact(contact);
                }
                Toast.makeText(MainActivity.this, "Server-Side DB Updated", Toast.LENGTH_SHORT).show();
        }catch(Exception ex)
        {
            Toast.makeText(MainActivity.this, "ERROR!! with Updating DB", Toast.LENGTH_SHORT).show();
        }
    }


    private void CameraButton(){
        findViewById(R.id.btnCamera_mainActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, camera_page.class);
                startActivity(intent);
            }
        });
    }

    private void saveNightModeState(boolean nightmode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISNIGHTMODE, nightmode);
        editor.apply();
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(KEY_ISNIGHTMODE, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void setDark(){
        findViewById(R.id.btn_dark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveNightModeState(true);
            }
        });
    }

    private void setLight(){
        findViewById(R.id.btn_light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveNightModeState(false);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void CreateOnResponseHandler(Contact contact) {

    }

    @Override
    public void ReadOneOnResponseHandler(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void ReadAllOnResponseHandler(List<Contact> contactList) {

    }

    @Override
    public void UpdateOnResponseHandler() {

    }

    @Override
    public void DeleteOnResponseHandler(Contact contact) {

    }

    @Override
    public void OnFailureHandler() {

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());

            Intent intent = new Intent(MainActivity.this, list_page.class);
            startActivity(intent);
            return true;
        }
    }

}



