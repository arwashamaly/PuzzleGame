package com.example.puzzle_game_arwa_shamaly.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PuzzleRepository {
    UserDAO userDAO;
    PuzzleDAO puzzleDAO;
    LevelDAO levelDAO;

    public PuzzleRepository(Application application) {
        PuzzleDatabase database = PuzzleDatabase.getDatabase(application);
        userDAO = database.userDAO();
        puzzleDAO = database.puzzleDAO();
        levelDAO = database.levelDAO();
    }

    //user
    void insertUser(User user) {
        PuzzleDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.insertUser(user);
            }
        });
    }

    void updateUser(User user) {
        PuzzleDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.updateUser(user);
            }
        });
    }

    LiveData<List<User>> getAllUser() {
        return userDAO.getAllUser();
    }

    //level
    void insertLevel(Level level) {
        PuzzleDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDAO.insertLevel(level);
            }
        });
    }

    LiveData<List<Level>> getAllLevel() {
        return levelDAO.getAllLevel();
    }

    //puzzle
    void insertPuzzle(Puzzle puzzle) {
        PuzzleDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                puzzleDAO.insertPuzzle(puzzle);
            }
        });
    }

    //جلب مجموعة الاسئلة الخاصة بالمرحلة
    LiveData<List<Puzzle>> getAllPuzzleByLevelID(int levelID) {
        return puzzleDAO.getAllPuzzleByLevelID(levelID);
    }

}
