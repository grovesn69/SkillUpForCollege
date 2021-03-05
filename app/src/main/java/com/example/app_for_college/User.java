package com.example.app_for_college;

//This is a class with the user's data. I think skills could be added here? Not sure.

public class User {

//Ciara: added string to user class. This isnt really necessary but could help us down the line
    public String userName;
    public String userEmail;
    public static String userSkill; //user added
    public User(String userName, String userEmail, String userSkill){
        this.userName = userName;
        this.userEmail = userEmail;
        User.userSkill = userSkill;

    }
    public User(){}
}
