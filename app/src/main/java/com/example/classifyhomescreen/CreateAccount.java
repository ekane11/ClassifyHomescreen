package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void clickFunction(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}