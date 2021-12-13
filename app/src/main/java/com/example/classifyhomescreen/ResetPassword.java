package com.example.classifyhomescreen;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {


    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    public void resetClickFunction(View view){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.emailConfirm);
        String emailAddress = email.getText().toString();


        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Email Sent.", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(getApplicationContext(), "Email is invalid.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}