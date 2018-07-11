package com.example.lenovo.simplifiedcoding;
//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User {

    private String username, email, gender;
    int id;

    public User(int id, String username, String email, String gender) {
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.id=id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }
}