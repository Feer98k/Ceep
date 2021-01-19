package com.example.ceep.classes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.ceep.R;

import static com.example.ceep.classes.constantes.general.ConstantesGerais.JA_ENTROU;
import static com.example.ceep.classes.constantes.sharedPreference.layoutPreference.USER_PREFERENCES;

@SuppressWarnings("ALL")
public class splashScreen extends AppCompatActivity {
    final Handler handle = new Handler();
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences =getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        verificaEntrada(handle, preferences);


    }

    private void verificaEntrada(Handler handle, SharedPreferences preferences) {

        if(!preferences.contains(JA_ENTROU)){
            criarPreference(preferences);
            handle.postDelayed(this::iniciarPacote, 2000);

        }else{
            handle.postDelayed(this::iniciarPacote, 500);
        }
    }

    public void iniciarPacote() {
        Intent intent = new Intent(this, ListaNotas.class);
        startActivity(intent);
        finish();
    }

    private void criarPreference(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(JA_ENTROU,true);
        editor.apply();
    }



}