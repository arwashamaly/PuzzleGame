package com.example.puzzle_game_arwa_shamaly.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class PuzzleViewModel extends AndroidViewModel {
    PuzzleRepository repository;

    public PuzzleViewModel(@NonNull Application application) {
        super(application);
        repository = new PuzzleRepository(application);
    }

    //user
    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public void updateUser(User user) {
        repository.updateUser(user);
    }

    public LiveData<List<User>> getAllUser() {
        return repository.getAllUser();
    }

    //level
    public void insertLevel(Level level) {
        repository.insertLevel(level);
    }

    public LiveData<List<Level>> getAllLevel() {
        return repository.getAllLevel();
    }

    //puzzle
    public void insertPuzzle(Puzzle puzzle) {
        repository.insertPuzzle(puzzle);
    }

    //جلب مجموعة الاسئلة الخاصة بالمرحلة
    public LiveData<List<Puzzle>> getAllPuzzleByLevelID(int levelID) {
        return repository.getAllPuzzleByLevelID(levelID);
    }
    //pattern
    public void insertPattern(Pattern pattern){
        repository.insertPattern(pattern);
    }
    public LiveData<List<Pattern>> getAllPattern(){
        return repository.getAllPattern();
    }


}
