package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
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

import java.util.Objects;

public class SkillData extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_data);
        Intent i = getIntent();
        String message = i.getStringExtra("Pass");

        ((TextView) findViewById(R.id.SkillTextView)).setText(message);
    }
}
    /*
    public void datalist() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/DbId/skills");

        //String userId;
        mDatabase.child("skills").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(Objects.requireNonNull(task.getResult()).getValue()));
                }
            }
        });

    }*/
