package com.example.app_for_college;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Intent i = getIntent();


    }

    public void UsersSkills(View v) {
        Intent i = new Intent(this, UsersSkills.class);
      //  final TextView usersSkill = (TextView) findViewById(R.id.SkillNameText);
        String message2 = ((EditText) findViewById(R.id.SkillNameText)).getText().toString();
        i.putExtra("SkillKey", message2);
        startActivity(i);

        user = FirebaseAuth.getInstance().getCurrentUser(); //get current user
        reference = FirebaseDatabase.getInstance().getReference("/Users");
        userID = user.getUid(); //get current user ID

        String MetricForY = ((EditText)findViewById(R.id.MetricForY)).getText().toString();
        String MetricForX = ((EditText)findViewById(R.id.MetricForX)).getText().toString();

        //Ciara: add skill to the class
                User.userSkill =  message2 ;


        //Ciara: add a skill to the data base
        DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills") ;
        DatabaseReference newIssueRef1 = rootRef1.push();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills") ;
        DatabaseReference newIssueRef = rootRef.push();
        newIssueRef.setValue(message2);
        //Ciara: add metrics
        DatabaseReference metric2Ref = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills" + "/" + message2);
        metric2Ref.child("metrics").push().setValue(MetricForY);
        metric2Ref.child("metrics").push().setValue(MetricForX);

    }

}

//Ciara: ignore all this commented code! ( i want to keep it in case its helpful in the future)

    /*
        //reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
        //    @Override
        //    public void onDataChange(@NonNull DataSnapshot snapshot) {
        //        User userProfile = snapshot.getValue(User.class);

            //    if (userProfile != null){           //This chunk can pull anything stored in a User class from the database.

        DatabaseReference fullName1 = reference.child(userID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // here you can get your data from this snapshot object
               String data = dataSnapshot.getValue(fullName1).toString();

                // String email1 = userProfile.userEmail;


                String MetricForY = ((EditText) findViewById(R.id.MetricForY)).getText().toString();
                String MetricForX = ((EditText) findViewById(R.id.MetricForX)).getText().toString();
                //add a skill to the data base
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Users" + "/" + userID + "/" + fullName1 + "/" + email1 + "/skills");

                DatabaseReference newIssueRef = rootRef.push();
                //newIssueRef.setValue(message2);
                DatabaseReference metric2Ref = FirebaseDatabase.getInstance().getReference("Users" + "/" + userID + "/" + fullName1 + "/" + email1 + "/skills" + "/" + message2);
                metric2Ref.child("metrics").push().setValue(MetricForY);
                metric2Ref.child("metrics").push().setValue(MetricForX);

                //      }
            }
          //  }

            //@Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddSkill.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
       // });


        /*
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
        metric2Ref.child("metrics").push().setValue(MetricForX);*/
/*

    }
}
*/