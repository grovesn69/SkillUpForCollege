package com.example.app_for_college;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class DatabaseCallsClass {

    /*public int count;       //skill count
    public int SkillCount(String userID){
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("/Users") ;
        userRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    count = userProfile.skillCount;
                }
                else{
                    count = 100;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "onCancelled: skill count failed");
            }
        });
        return count;
    }*/

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
