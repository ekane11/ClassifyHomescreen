package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    // this takes to the create account page
    public void clickFunction(View view){
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    // this takes to the home page
    public void clickFunction2(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}