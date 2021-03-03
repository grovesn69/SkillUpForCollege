package com.example.app_for_college;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Log In");
    }

    public void GoToHome(View v) {
        //add a name to the data base
        String message = ((EditText)findViewById(R.id.NameText)).getText().toString();
        DatabaseReference DbId = FirebaseDatabase.getInstance().getReference("/names" + "/ " +message);
        DatabaseReference newIssueRef = DbId.push();
        newIssueRef.setValue(message);//NameText
        Intent i = new Intent(this, GoToHome.class);

        startActivity(i);
    }

    public void Sign_up(View v) {
        Intent i = new Intent(this, sign_up.class);
        startActivity(i);
    }

    public void ForgotPassword(View v){
        Intent i = new Intent(this, ForgotPassword.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forgotPassword:
                startActivity(new Intent(this, sign_up.class));
                break;
        }
    }
}

