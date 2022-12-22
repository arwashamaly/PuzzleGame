package com.example.puzzle_game_arwa_shamaly.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Level.class, Puzzle.class, User.class, Pattern.class},
        version = 1, exportSchema = false)
public abstract class PuzzleDatabase extends RoomDatabase {

    public abstract UserDAO userDAO();

    public abstract LevelDAO levelDAO();

    public abstract PuzzleDAO puzzleDAO();

    public abstract PatternDAO patternDAO();

    private static volatile PuzzleDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PuzzleDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PuzzleDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PuzzleDatabase.class, "puzzle_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}