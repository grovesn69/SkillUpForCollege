package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class GoToHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_home);
        setTitle("Home");
        Intent i = getIntent();
        String message = i.getStringExtra("SkillKey");
        ((TextView)findViewById(R.id.SkillTextOne)).setText(message);
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
