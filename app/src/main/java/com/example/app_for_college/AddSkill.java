package com.example.app_for_college;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

//NEEDS TO STORE SKILLS WITHIN EACH USER BRANCH.

public class AddSkill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);
        setTitle("Add Skill");

        Spinner dropdown = findViewById(R.id.MetricForX);
        String[] items = new String[]{"kg", "reps", "km"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

       dropdown = findViewById(R.id.MetricForY);
        items = new String[]{"kg", "reps", "km"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Intent i = getIntent();
    }

    public void UserSkills(View v){
        Intent i = new Intent(this, UsersSkills.class);
        String message = ((EditText)findViewById(R.id.SkillNameText)).getText().toString();
        i.putExtra("SkillKey", message);
        startActivity(i);

        String MetricForY = ((Spinner)findViewById(R.id.MetricForY)).toString();
        String MetricForX = ((Spinner)findViewById(R.id.MetricForX)).toString();
        //add a skill to the data base
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("/skills") ;

        DatabaseReference newIssueRef = rootRef.push();
       //newIssueRef.setValue(message2);
        DatabaseReference metric2Ref = FirebaseDatabase.getInstance().getReference("/skills" + "/" + message);
        metric2Ref.child("metrics").push().setValue(MetricForY);
        metric2Ref.child("metrics").push().setValue(MetricForX);


    }
}