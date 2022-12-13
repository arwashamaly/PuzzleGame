package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.puzzle_game_arwa_shamaly.databinding.ActivityPuzzleScreenBinding;

public class PuzzleScreen extends AppCompatActivity {
ActivityPuzzleScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPuzzleScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}