package com.example.UserApp.entity;

public class User {
    private long id;
    private String name;
    private String surname;
    private long age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public User() {
    }

    public User(String name, String surname, long age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(long id, String name, String surname, long age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

}
