package com.example.app_for_college;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private EditText EmailText, PasswordText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Log In");
    }

    public void LogInMain(View v) {
/*        //add a name to the data base
        String message = ((EditText)findViewById(R.id.EmailText)).getText().toString();
        DatabaseReference DbId = FirebaseDatabase.getInstance().getReference("/names" + "/ " +message);
        DatabaseReference newIssueRef = DbId.push();
        newIssueRef.setValue(message);//NameText
        Intent i = new Intent(this, GoToHome.class);

        startActivity(i);*/

        mAuth = FirebaseAuth.getInstance();
        EmailText = (EditText) findViewById(R.id.EmailText);
        PasswordText = (EditText) findViewById(R.id.PasswordText);
        mAuth = FirebaseAuth.getInstance();
        LogInChecks();
    }

    private void LogInChecks() {
        String email = EmailText.getText().toString().trim();
        String password = PasswordText.getText().toString().trim();

        if(email.isEmpty()){
            EmailText.setError("Your email address is required.");
            EmailText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmailText.setError("Please enter a valid email.");
            EmailText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            PasswordText.setError("Please enter your password.");
            PasswordText.requestFocus();
        }
        if(password.length() < 6){
            PasswordText.setError("Minimum password length is six characters.");
            PasswordText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        startActivity(new Intent(MainActivity.this, UsersSkills.class));
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check your email to verify you account.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Log in failed. Please check your credentials and try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Sign_up(View v) {
        Intent i = new Intent(this, sign_up.class);
        startActivity(i);
    }

    //Haven't added forgot password functionality yet.
    public void ForgotPassword(View v) {
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
    }

}

