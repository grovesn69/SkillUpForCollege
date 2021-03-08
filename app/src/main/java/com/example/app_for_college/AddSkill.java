package com.example.app_for_college;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

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
        user = FirebaseAuth.getInstance().getCurrentUser(); //get current user
        reference = FirebaseDatabase.getInstance().getReference("/Users");
        userID = user.getUid(); //get current user ID

        Spinner MetricForY = ((Spinner)findViewById(R.id.MetricForY));
        String text = MetricForY.getSelectedItem().toString();
        Spinner MetricForX = ((Spinner)findViewById(R.id.MetricForX));
        String text2 = MetricForY.getSelectedItem().toString();
       // String MetricForX = ((EditText)findViewById(R.id.MetricForX)).getText().toString();


        //String MetricForY = ((EditText)findViewById(R.id.MetricForY)).getText().toString();
       // String MetricForX = ((EditText)findViewById(R.id.MetricForX)).getText().toString();

        //Ciara: add skill to the class
       // User.userSkill =  message2 ;


        //Ciara: add a skill to the data base
        DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills") ;
        DatabaseReference newIssueRef1 = rootRef1.push();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills") ;
        DatabaseReference newIssueRef = rootRef.push();
        newIssueRef.setValue(message);
        //Ciara: add metrics
        DatabaseReference metric2Ref = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills" + "/" + message);
        metric2Ref.child("metrics").push().setValue(text);
        metric2Ref.child("metrics").push().setValue(text2);

    }
}