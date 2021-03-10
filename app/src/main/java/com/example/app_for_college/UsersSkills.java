package com.example.app_for_college;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class UsersSkills extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private int skillCount;
    Intent in;
    ArrayList<String> skills;


    public void PrintList(ArrayList<String> skillList){
        Log.d("list", skillList.get(0));
        Log.d("list", skillList.get(1));
        Log.d("list", skillList.get(2));
        Log.d("list", skillList.get(3));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_skills);
        setTitle("Home");
        skills = new ArrayList<>();
        in = getIntent();

        //String message = in.getStringExtra("SkillKey");
        //skills.add(message);
        skills.add("Jumping"); //hard coded skills
        skills.add("Drumming");
        skills.add("Passing");
        skills.add("Shooting");
        for(int x =0; x<skills.size(); x++){     //Loop to create dynamic buttons
            Button myButton = new Button(this);
            String skill = skills.get(x);
            myButton.setText(skill);
            myButton.setBackgroundColor(Color.GRAY);
            myButton.setTextColor(Color.YELLOW);
            LinearLayout ll = (LinearLayout) findViewById(R.id.buttonlayout);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 10);
            myButton.setLayoutParams(params);

            ll.addView(myButton,params);

            myButton.setOnClickListener(handleOnClick(myButton));
        }

        String message = in.getStringExtra("SkillKey");
        Button myButton = new Button(this);
        myButton.setText(message);
        //myButton.setGravity(Gravity.CENTER);
        LinearLayout ll = (LinearLayout) findViewById(R.id.buttonlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton,lp);


        //Retrieves user's name and displays it in the top right corner. Retrieval of skills can probably use this method.
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView usersName = (TextView) findViewById(R.id.ProfileNameText);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){           //This chunk can pull anything stored in a User class from the database.

                    String fullName = userProfile.userName;
                    //String email = userProfile.userEmail;
                    //skillCount = userProfile.skillCount;

                    usersName.setText(fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UsersSkills.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });

/*        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.UsersSkills, );
        DatabaseReference skillsRef = FirebaseDatabase.getInstance().getReference().child("skills");
        skillsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot skillSnapshot : snapshot.getChildren()){
                    list.add(skillSnapshot.child("skills").getValue().toString());
                    Toast.makeText(UsersSkills.this, "list: " + list., Toast.LENGTH_LONG).show();
                }
                Toast.makeText(UsersSkills.this, "array list size is " + list.size(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

/*        skillsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot skillsnap : snapshot.getChildren()){
                    String skill = (String)skillsnap.getValue();
                    Toast.makeText(UsersSkills.this, "node " + skill, Toast.LENGTH_LONG).show();

                    if(skill != null){
                        skillList.add(skillsnap.getKey());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        //Toast.makeText(UsersSkills.this, "array list size is " + skillList.size(), Toast.LENGTH_LONG).show();

/*        final ArrayList<String> skillList = new ArrayList<>();
        DatabaseReference skillsRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills");
        skillsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot skillSnapshot : snapshot.getChildren()){
                    Skill skill = snapshot.getValue(Skill.class);
                    String skillName = skill.skillName;     //i dont think a skillName string is being saved, not sure why not
                    Log.d("list", skillName);           //this line makes the app crash.
                    skillList.add(skillName);               //the list size is zero, so this isn't working
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("list", "failed");
            }
        });

        PrintList(skillList);*/
    }

    View.OnClickListener handleOnClick(final Button myButton) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                String message = myButton.getText().toString();
                Intent in = new Intent(UsersSkills.this, SkillData.class);
                in.putExtra("Pass", message);
                startActivity(in);
            }
        };
    }

    public void Menu(View v){           //Gwen
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
    public void AddSkill(View v){
        Intent i = new Intent(this, AddSkill.class);
        startActivity(i);
    }

    public void SkillData(View v) {                         //Gwen: deleted button that leads here. When Niall dynamically creates buttons might go here?
        Intent in = new Intent(this, SkillData.class);
        String message = ((Button) findViewById(R.id.button2)).getText().toString();
        in.putExtra("Pass", message);
        startActivity(in);
    }
}