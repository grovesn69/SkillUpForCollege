package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent i = getIntent();
    }
    public void MainActivity(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}