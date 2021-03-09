package com.example.app_for_college;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseCallsClass {
    public int count;       //skill count

    public int SkillCount(String userID){
        DatabaseReference countRef = FirebaseDatabase.getInstance().getReference("/Users" + "/" + userID + "/skillCount") ;
        count = countRef.
        return count;
    }

/*    public String GetID(){

    }

    public String[] SkillList(){
        String userId = GetID();
        //call skills and store them in an array
    }

    public void AddSkill(){

    }

    public void AddMetrics(String skill, String metric1, String metric2){

    }

    public void AddTrainingSession(){

    }*/
}
