package com.example.app_for_college;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class UsersSkills extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    //Retrieves user's name and displays it in the top right corner. Retrieval of skills can probably use this method.
    @Override
    protected void onCreate(Bundle savedInstanceState) {            //Gwen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_skills);
/*        setTitle("Home");
        Intent i = getIntent();
        String message = i.getStringExtra("SkillKey");
        ((TextView)findViewById(R.id.SkillTextOne)).setText(message);*/

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView usersName = (TextView) findViewById(R.id.ProfileNameText);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){

                    String fullName = userProfile.userName;
                    String email = userProfile.userEmail;

                    usersName.setText(fullName);
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
    public void SkillData(View v) {
        Intent in = new Intent(this, SkillData.class);
        String message = ((EditText) findViewById(R.id.SkillTextOne)).getText().toString();
        in.putExtra("Pass", message);
        startActivity(in);
    }
}