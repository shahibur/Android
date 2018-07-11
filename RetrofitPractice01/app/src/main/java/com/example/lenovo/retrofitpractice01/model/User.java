package com.example.lenovo.retrofitpractice01.model;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class User {

    private String name;
    private String hobby;
    public User() {
    }

    /**
     *
     * @param name
     * @param hobby
     */
    public User(String name, String hobby) {
        super();
        this.name = name;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
