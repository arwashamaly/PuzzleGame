package com.example.puzzle_game_arwa_shamaly.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Level {
    @PrimaryKey
     int level_no;
     int unlock_points;
     int score;
     int overallAssessment;

    public Level(int level_no, int unlock_points, int score, int overallAssessment) {
        this.level_no = level_no;
        this.unlock_points = unlock_points;
        this.score = score;
        this.overallAssessment = overallAssessment;
    }

    public int getLevel_no() {
        return level_no;
    }

    public void setLevel_no(int level_no) {
        this.level_no = level_no;
    }

    public int getUnlock_points() {
        return unlock_points;
    }

    public void setUnlock_points(int unlock_points) {
        this.unlock_points = unlock_points;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getOverallAssessment() {
        return overallAssessment;
    }

    public void setOverallAssessment(int overallAssessment) {
        this.overallAssessment = overallAssessment;
    }

}
