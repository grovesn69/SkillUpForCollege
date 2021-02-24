package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Log In");
    }
    public void GoToHome(View v){
        Intent i = new Intent(this, GoToHome.class);
        startActivity(i);
    }

    public void Sign_up(View v){
        Intent i = new Intent(this, sign_up.class);
        startActivity(i);
    }
}