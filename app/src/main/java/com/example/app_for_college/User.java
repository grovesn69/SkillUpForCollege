package com.example.app_for_college;

//This is a class with the user's data. I think skills could be added here? Not sure.

public class User {

    public String userName, userEmail;
    public int skillCount;
    public User(String userName, String userEmail, int skillCount){
        this.userName = userName;
        this.userEmail = userEmail;
        this.skillCount = skillCount;
    }
    public User(){}
}
