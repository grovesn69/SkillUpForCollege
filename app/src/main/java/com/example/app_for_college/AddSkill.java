package com.example.app_for_college;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//NEEDS TO STORE SKILLS WITHIN EACH USER BRANCH.

public class AddSkill extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private String skillCountString;            //Gwen
    private int skillCount;                     //Gwen

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
    public void UsersSkills(){
        Intent i = new Intent(this, UsersSkills.class);
        startActivity(i);
    }

    public int SkillCounter(String userID){
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    int skillCount = userProfile.skillCount;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddSkill.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void IncrementSkillCount(DatabaseReference reference, String userID){
/*        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    int skillCount = userProfile.skillCount;
                    skillCount++;
                    DatabaseReference countRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skillCount") ;
                    countRef.setValue(skillCount);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddSkill.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });*/
        int count = SkillCounter(userID);
        count++;
        DatabaseReference countRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skillCount") ;
        countRef.setValue(count);
    }

    public void UserSkills(View v){
        Intent i = new Intent(this, UsersSkills.class);
        String message = ((EditText)findViewById(R.id.SkillNameText)).getText().toString();
        i.putExtra("SkillKey", message);
        startActivity(i);
        user = FirebaseAuth.getInstance().getCurrentUser(); //get current user
        reference = FirebaseDatabase.getInstance().getReference("/Users");
        userID = user.getUid(); //get current user ID


        Spinner MetricForX = ((Spinner)findViewById(R.id.MetricForY));
        String text = MetricForX.getSelectedItem().toString();
        Spinner MetricForY = ((Spinner)findViewById(R.id.MetricForX));
        String text2 = MetricForY.getSelectedItem().toString();
       // String MetricForX = ((EditText)findViewById(R.id.MetricForX)).getText().toString();

        //String MetricForY = ((EditText)findViewById(R.id.MetricForY)).getText().toString();
       // String MetricForX = ((EditText)findViewById(R.id.MetricForX)).getText().toString();

        //Ciara: add skill to the class
       // User.userSkill =  message2 ;

        //Ciara: add a skill to the data base
//        DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills") ;
//        DatabaseReference newIssueRef1 = rootRef1.push();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills") ;
        DatabaseReference newIssueRef = rootRef.push();
//        newIssueRef.setValue(message);

        //Ciara: add metrics
        DatabaseReference metric2Ref = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills" + "/" + message);
        metric2Ref.child("metric1").setValue(text);
        metric2Ref.child("metric2").setValue(text2);

        //Gwen: add to skill count
        IncrementSkillCount(reference, userID);
/*        DatabaseReference skillCountRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skillCount");
        skillCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                skillCountString = snapshot.getKey();
                skillCount = Integer.parseInt(skillCountString);
                skillCount++;
                skillCountRef.setValue(skillCount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        //WHEN THE SKILL ADDING IS HANDLED IN DATABASE CALLS - an onCompleteListener can be added similar to below,
            //when the function is called in this class

/*        //Gwen's revision of adding a skill to the database
        Skill skill = new Skill(message, text, text2);
        FirebaseDatabase.getInstance().getReference("Users" + "/" + userID + "/skills")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(skill).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                int success;
                if(task.isSuccessful()){
                    Toast.makeText(AddSkill.this, "Skill has been successfully added.", Toast.LENGTH_LONG).show();
                    UsersSkills();
                }
                else{
                    Toast.makeText(AddSkill.this, "Failed to add skill. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }
}