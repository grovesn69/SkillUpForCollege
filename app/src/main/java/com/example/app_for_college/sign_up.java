package com.example.app_for_college;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class sign_up extends AppCompatActivity implements View.OnClickListener {

    private EditText FullNameText, AddEmail, AddEmailConfirm, AddPassword, AddPasswordConfirm;
    private Button EnterDetailsButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent i = getIntent();

        mAuth = FirebaseAuth.getInstance();

        EnterDetailsButton = (Button) findViewById(R.id.EnterDetailsButton);
        EnterDetailsButton.setOnClickListener(this);

        FullNameText = (EditText) findViewById(R.id.FullNameText);
        AddEmail = (EditText) findViewById(R.id.AddEmail);
        AddEmailConfirm = (EditText) findViewById(R.id.AddEmailConfirm);
        AddPassword = (EditText) findViewById(R.id.AddPassword);
        AddPasswordConfirm = (EditText) findViewById(R.id.AddPasswordConfirm);

    }
    public void MainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.EnterDetailsButton:
                registerUser();
                break;
            case R.id.backButton:
                MainActivity();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void registerUser() {
        String fullName = FullNameText.getText().toString().trim();
        String email1 = AddEmail.getText().toString().trim();
        String email2 = AddEmailConfirm.getText().toString().trim();
        String password1 = AddPassword.getText().toString().trim();
        String password2 = AddPasswordConfirm.getText().toString().trim();

        if(fullName.isEmpty()){
            FullNameText.setError("Full name is required.");
            FullNameText.requestFocus();
            return;
        }
        if(email1.isEmpty()){
            AddEmail.setError("An email address is required.");
            AddEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            AddEmail.setError("The email address you entered is not valid. Please enter a valid email address.");
            AddEmail.requestFocus();
            return;
        }
        if(email2.isEmpty()){
            AddEmail.setError("Please confirm your email address.");
            AddEmail.requestFocus();
            return;
        }
        if(!Objects.equals(email1, email2)) {
            AddEmailConfirm.setError("Your email address entries don't match.");
            AddEmailConfirm.requestFocus();
            return;
        }
        if(password1.isEmpty()){
            AddPassword.setError("A password must be set.");
            AddPassword.requestFocus();
            return;
        }
        if(password2.isEmpty()){
            AddPasswordConfirm.setError("Please confirm your password.");
            AddPasswordConfirm.requestFocus();
            return;
        }
        if(password1.length() < 6){
            AddPassword.setError("The minimum password length is six characters.");
            AddPassword.requestFocus();
            return;
        }
        if(!Objects.equals(password1, password2)) {
            AddPasswordConfirm.setError("Your password entries don't match.");
            AddPasswordConfirm.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullName, email1, 0);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    int success;
                                    if(task.isSuccessful()){
                                        Toast.makeText(sign_up.this, "User has been registered successfully.", Toast.LENGTH_LONG).show();
                                        MainActivity();
                                    }
                                    else{
                                        Toast.makeText(sign_up.this, "Failed to register. Please try again.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(sign_up.this, "Failed to register. Please try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}