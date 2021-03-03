package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity implements View.OnClickListener {

    private EditText FullNameText, AddEmail, AddPassword;
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
        AddPassword = (EditText) findViewById(R.id.AddPassword);


    }
    public void MainActivity(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.EnterDetailsButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String fullName = FullNameText.getText().toString().trim();
        String email = AddEmail.getText().toString().trim();
        String password = AddPassword.getText().toString().trim();

        if(fullName.isEmpty()){
            FullNameText.setError("Full name is required.");
            FullNameText.requestFocus();
            return;
        }
        if(email.isEmpty()){
            AddEmail.setError("An email address is required.");
            AddEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            AddEmail.setError("The email address you entered is not valid. Please enter a valid email address.");
            AddEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            AddPassword.setError("A password must be set.");
            AddPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            AddPassword.setError("The minimum password length is six characters.");
            AddPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullName, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(sign_up.this, "User has been registered successfully.", Toast.LENGTH_LONG).show();
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