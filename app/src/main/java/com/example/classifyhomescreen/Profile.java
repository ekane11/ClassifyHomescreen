package com.example.classifyhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    TextView emailAddress;
    private FirebaseAuth mAuth;
//    EditText ageInput;
//    EditText schoolInput;
//    EditText emailInput;
//    String editTextString1;
//    String editTextString2;
//    String editTextString3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        emailAddress =  (TextView) findViewById(R.id.email5);
        emailAddress.setText(mAuth.getCurrentUser().getEmail().toString());
//        ageInput = (EditText) findViewById(R.id.ageInput);
//        schoolInput = (EditText) findViewById(R.id.schoolInput);
//        emailInput = (EditText) findViewById(R.id.emailInput);
//        ageInput.setText(editTextString1);
//        schoolInput.setText(editTextString2);
//        emailInput.setText(editTextString3);


//        if(savedInstanceState != null) {
//            String editTextString1 = savedInstanceState.getString("EditText1");
//            ageInput.setText(editTextString1);
//        }
//        if(savedInstanceState != null) {
//            String editTextString2 = savedInstanceState.getString("EditText2");
//            schoolInput.setText(editTextString2);
//        }
//        if(savedInstanceState != null) {
//            String editTextString3 = savedInstanceState.getString("EditText3");
//            emailInput.setText(editTextString3);
//        }
    }

//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//        String editTextString1 = savedInstanceState.getString("EditText1");
//        String editTextString2 = savedInstanceState.getString("EditText2");
//        String editTextString3 = savedInstanceState.getString("EditText3");
//
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("EditText1", ageInput.getText().toString());
//        outState.putString("EditText2", emailInput.getText().toString());
//        outState.putString("EditText3", schoolInput.getText().toString());
//    }

    public void clickFunction(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}