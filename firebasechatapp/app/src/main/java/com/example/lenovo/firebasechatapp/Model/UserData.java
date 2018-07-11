package com.example.lenovo.firebasechatapp.Model;

/**
 * Created by Lenovo on 3/24/2018.
 */

public class UserData {

    String name,username,email,gender,password;

    public UserData() {

    }

    @Override
    public String toString() {


        return "UserData{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';

    }

    public UserData(String name, String username, String email, String gender, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
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

    public String getPassword() {
        return password;
    }
}
