package com.example.app_for_college;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import com.jjoe64.graphview.GraphView;              // Graphing library {DC}
import com.jjoe64.graphview.series.DataPoint;       // Supporting libraries for graphing {DC}
import com.jjoe64.graphview.series.LineGraphSeries;

public class SkillData extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private DatabaseReference metric1;
    private DatabaseReference metric2;
    private String userID;


    //I think this will work when we figure out how to pass skillID here,
    // or how to label the skill with it's name and then pass the name here.
    public void MetricHandling(){
/*        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        //need to get skillID somehow

        final TextView metric1View = (TextView) findViewById(R.id.metric1display);
        final TextView metric2View = (TextView) findViewById(R.id.metric2display);

        DatabaseReference skillRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills" + skillID);
        skillRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Skill skill = snapshot.getValue(Skill.class);
                String metric1 = skill.metric1;
                String metric2 = skill.metric2;
                metric1View.setText(metric1);
                metric2View.setText(metric2);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("metric", "failed");
            }
        });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_data);
        Intent i = getIntent();
        String message = i.getStringExtra("Pass");
        ((TextView)findViewById(R.id.SkillTextView)).setText(message);

        GraphView SkillGraph = (GraphView)findViewById(R.id.SkillGraph);  // Placeholder graph with arbitrary values {DC}
        LineGraphSeries<DataPoint> sampleSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0,2),
                new DataPoint(1,6),
                new DataPoint(2,9),
                new DataPoint(3,11),
                new DataPoint(4,12),
                new DataPoint(5,12),
        });                                         // Creates series to graph {DC}
        SkillGraph.addSeries(sampleSeries);         // Adds series to graph {DC}
        SkillGraph.getViewport().setMinX(0);
        SkillGraph.getViewport().setMaxX(5);
        SkillGraph.getViewport().setXAxisBoundsManual(true);  // Sets bounds to match placeholder series
                                                              //   -> change or remove for actual implementation {DC}
//        MetricHandling();
        UserMetricData();
    }
//}


    public void UserMetricData() {

        String met1 = ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
        String met2 = ((EditText)findViewById(R.id.editTextTextPersonName2)).getText().toString();

        String text3 = ((TextView)findViewById(R.id.SkillTextView)).getText().toString(); //this should be whatever skill is chosen
        user = FirebaseAuth.getInstance().getCurrentUser(); //get current user
        reference = FirebaseDatabase.getInstance().getReference("/Users");
        userID = user.getUid(); //get current user ID

        //Ciara: add metrics
        DatabaseReference metRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skills/new");
       // DatabaseReference newMetRef = metRef.push();
        metRef.child("metric1").setValue(met1);
        metRef.child("metric2").setValue(met2);
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
