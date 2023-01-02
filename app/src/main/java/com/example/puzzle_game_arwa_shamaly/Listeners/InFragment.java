package com.example.puzzle_game_arwa_shamaly.Listeners;

public interface InFragment {
    void onAnswer(String dialogName,String hint,int points);
    void puzzleInfo(int duration,String hint);
    void nextPuzzle();
}
