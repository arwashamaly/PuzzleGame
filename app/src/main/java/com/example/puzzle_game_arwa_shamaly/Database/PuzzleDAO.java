package com.example.puzzle_game_arwa_shamaly.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PuzzleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPuzzle(Puzzle puzzle);

    //جلب مجموعة الاسئلة الخاصة بالمرحلة
    @Query("select * from puzzle where level_id =:levelID order by id")
    LiveData<List<Puzzle>> getAllPuzzleByLevelID(int levelID);
}
