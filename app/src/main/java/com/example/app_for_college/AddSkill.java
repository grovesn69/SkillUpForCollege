package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class AddSkill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);
        setTitle("Add Skill");
        Intent i = getIntent();
    }

    public void GoToHome(View v){
        Intent i = new Intent(this, GoToHome.class);
        String message = ((EditText)findViewById(R.id.SkillNameText)).getText().toString();
        i.putExtra("SkillKey", message);
        startActivity(i);
    }
}