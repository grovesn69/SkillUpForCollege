package com.example.app_for_college;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    //Retrieves user's name and displays it in the top right corner. Retrieval of skills can probably use this method.
    @Override
    protected void onCreate(Bundle savedInstanceState) {            //Gwen
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

    public void Menu(View v){           //Gwen
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
    public void AddSkill(View v){
        Intent i = new Intent(this, AddSkill.class);
        startActivity(i);
    }
    public void SkillData(View v) {                         //Gwen deleted button that leads here. When Niall dynamically creates buttons might go here?
        Intent in = new Intent(this, SkillData.class);
        String message = ((Button) findViewById(R.id.button2)).getText().toString();
        in.putExtra("Pass", message);
        startActivity(in);
    }
}