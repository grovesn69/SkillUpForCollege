package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class GoToHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_home);
        setTitle("Home");

        Intent i = getIntent();
        String message = i.getStringExtra("SkillKey");
        Button myButton = new Button(this);
        myButton.setText(message);
        LinearLayout ll = (LinearLayout) findViewById(R.id.buttonlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton,lp);
    }

    public void AddSkill(View v){
        Intent i = new Intent(this, AddSkill.class);
        startActivity(i);
    }
    public void SkillData(View v) {
        Intent in = new Intent(this, SkillData.class);
        String message = ((EditText) findViewById(R.id.SkillTextOne)).getText().toString();
        in.putExtra("Pass", message);
        startActivity(in);
    }

}