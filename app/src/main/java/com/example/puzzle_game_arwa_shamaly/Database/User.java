package com.example.puzzle_game_arwa_shamaly.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    String user_name;
    String email;
    String gender;
    String country;
    String birthdate;
    int numOfCompletedLevels;
    int numOfQuestionsAnswered;
    int numOfCorrectAnswer;
    int numOfWrongAnswer;

    public User(String user_name, String email, String gender, String country, String birthdate,
                int numOfCompletedLevels, int numOfQuestionsAnswered, int numOfCorrectAnswer,
                int numOfWrongAnswer) {
        this.user_name = user_name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.birthdate = birthdate;
        this.numOfCompletedLevels = numOfCompletedLevels;
        this.numOfQuestionsAnswered = numOfQuestionsAnswered;
        this.numOfCorrectAnswer = numOfCorrectAnswer;
        this.numOfWrongAnswer = numOfWrongAnswer;
    }

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getNumOfCompletedLevels() {
        return numOfCompletedLevels;
    }

    public void setNumOfCompletedLevels(int numOfCompletedLevels) {
        this.numOfCompletedLevels = numOfCompletedLevels;
    }

    public int getNumOfQuestionsAnswered() {
        return numOfQuestionsAnswered;
    }

    public void setNumOfQuestionsAnswered(int numOfQuestionsAnswered) {
        this.numOfQuestionsAnswered = numOfQuestionsAnswered;
    }

    public int getNumOfCorrectAnswer() {
        return numOfCorrectAnswer;
    }

    public void setNumOfCorrectAnswer(int numOfCorrectAnswer) {
        this.numOfCorrectAnswer = numOfCorrectAnswer;
    }

    public int getNumOfWrongAnswer() {
        return numOfWrongAnswer;
    }

    public void setNumOfWrongAnswer(int numOfWrongAnswer) {
        this.numOfWrongAnswer = numOfWrongAnswer;
    }

}
