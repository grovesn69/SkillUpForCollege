package com.example.app_for_college;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.google.android.gms.tasks.Task;
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

    public void PrintList(ArrayList<String> skillList){
        Log.d("list", skillList.get(1));
        Log.d("list", skillList.get(1));
        Log.d("list", skillList.get(2));
        Log.d("list", skillList.get(3));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_skills);
        setTitle("Home");
        in = getIntent();
        String message = in.getStringExtra("SkillKey");
        Button myButton = new Button(this);
        myButton.setText(message);
        //myButton.setGravity(Gravity.CENTER);
        LinearLayout ll = (LinearLayout) findViewById(R.id.buttonlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton,lp);


        //Retrieves user's name and displays it in the top right corner. Retrieval of skills can probably use this method.
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("/Users");
        userID = user.getUid();

        final TextView usersName = (TextView) findViewById(R.id.ProfileNameText);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){           //This chunk can pull anything stored in a User class from the database.
                    String fullName = userProfile.userName;
                    usersName.setText(fullName);            //Sets the TextView to the user's name.
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

        //Gwen: This is the latest attempt. I don't understand why it's not working.
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