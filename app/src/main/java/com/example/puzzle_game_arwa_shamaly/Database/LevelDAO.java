package com.example.puzzle_game_arwa_shamaly.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LevelDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLevel(Level level);

    @Query("select * from level")
    LiveData<List<Level>> getAllLevel();
}
