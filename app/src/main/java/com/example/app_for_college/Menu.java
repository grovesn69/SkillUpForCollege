package com.example.app_for_college;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

//Gwen.
//Delete account functionality needs to be added.
//Integrations with other apps and notifications could be handled here.

public class Menu extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logout = (Button) findViewById(R.id.LogOutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Menu.this, "You have been logged out.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
    }
}