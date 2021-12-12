package com.example.classifyhomescreen;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    EditText email;
    EditText password;
    EditText password2;
    String email1;
    String password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        onStart();

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    public void clickFunction(View view){
//        if (email1 == null || password1 == null){
//            Toast.makeText(getApplicationContext(), "Email or password is invalid.", Toast.LENGTH_SHORT).show();
//            return;
//        }
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.username);

        if (password.length() < 6){
            Toast.makeText(getApplicationContext(), "Password needs to be at least 6 characters.", Toast.LENGTH_SHORT).show();
            return;
        }

        password2 = (EditText) findViewById(R.id.passwordCorrect);
        if (!password.equals(password2)){
            Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }
        email1 = email.getText().toString();
        password1 = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        if (user != null){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Not logged in properly.", Toast.LENGTH_SHORT).show();

        }
    }
}
