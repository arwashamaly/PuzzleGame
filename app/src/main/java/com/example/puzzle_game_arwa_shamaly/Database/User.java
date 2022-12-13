package com.example.puzzle_game_arwa_shamaly.Database;

public class User {
     int id;
     String user_name;
     String email;
     String gender;
     String country;
    long birthdate;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public long getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }
}
