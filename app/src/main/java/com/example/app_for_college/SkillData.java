package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SkillData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_data);
        Intent i = getIntent();
        String message = i.getStringExtra("Pass");
        ((TextView)findViewById(R.id.SkillTextView)).setText(message);
    }

}