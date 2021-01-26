package com.example.ceep.classes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.ceep.R;

import static com.example.ceep.classes.constants.general.GeneralConstants.FIRST;
import static com.example.ceep.classes.constants.sharedPreference.LayoutPreference.USER_PREFERENCES;

@SuppressWarnings("ALL")
public class SplashScreen extends AppCompatActivity {
    final Handler handle = new Handler();
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences =getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        check(handle, preferences);


    }

    private void check(Handler handle, SharedPreferences preferences) {

        if(!preferences.contains(FIRST)){
            createPreferences(preferences);
            handle.postDelayed(this::startListNotes, 2000);

        }else{
            handle.postDelayed(this::startListNotes, 500);
        }
    }

    public void startListNotes() {
        Intent intent = new Intent(this, NotesList.class);
        startActivity(intent);
        finish();
    }

    private void createPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FIRST,true);
        editor.apply();
    }



}