package com.example.app_for_college;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

//USE THIS ACTIVITY INSTEAD OF GOTOHOME

//I think onCreate can be changed added to where I left comments, to display skills the user put in previously.
//That might not work for dynamic buttons though.
//User class probably needs to have something added to handle skills? Atm just holds name and email.
//Separate method might be needed for displaying skills right after there added, rather when activity is started.
//Gwen

public class UsersSkills extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    Intent in;
    ArrayList<String> skills;

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
                    String email = userProfile.userEmail;

                    usersName.setText(fullName);        //This can be replicated to put skill names on buttons.
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UsersSkills.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });
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

}