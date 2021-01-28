package com.example.stuntingapps;

public class User {

    private int user_id;
    private String email, user_name;

    public User(int user_id, String email, String username) {
        this.user_id = user_id;
        this.email = email;
        this.user_name = user_name;
    }

    public int getUserId() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return user_name;
    }
}
