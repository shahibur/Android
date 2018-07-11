package com.example.lenovo.firebasetutorial_01;

/**
 * Created by Lenovo on 2/9/2018.
 */

public class User {

    String email;
    String address;
    String phone;

    public User(String email, String address, String phone) {
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
