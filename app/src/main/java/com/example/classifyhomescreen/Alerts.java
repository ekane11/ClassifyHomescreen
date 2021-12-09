package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Alerts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
    }

    public void clickFunction(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}