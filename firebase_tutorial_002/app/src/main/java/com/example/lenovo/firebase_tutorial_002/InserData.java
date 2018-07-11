package com.example.lenovo.firebase_tutorial_002;

/**
 * Created by Lenovo on 2/10/2018.
 */

public class InserData {
    String name;
    int age;
    double salary;

    public InserData(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public InserData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
