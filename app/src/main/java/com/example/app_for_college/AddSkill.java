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
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//NEEDS TO STORE SKILLS WITHIN EACH USER BRANCH.

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
        String message2 = ((EditText)findViewById(R.id.SkillNameText)).getText().toString();
        i.putExtra("SkillKey", message2);
        startActivity(i);

        String MetricForY = ((EditText)findViewById(R.id.MetricForY)).getText().toString();
        String MetricForX = ((EditText)findViewById(R.id.MetricForX)).getText().toString();
        //add a skill to the data base
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("/skills") ;

        DatabaseReference newIssueRef = rootRef.push();
       //newIssueRef.setValue(message2);
        DatabaseReference metric2Ref = FirebaseDatabase.getInstance().getReference("/skills" + "/" + message2);
        metric2Ref.child("metrics").push().setValue(MetricForY);
        metric2Ref.child("metrics").push().setValue(MetricForX);


    }
}