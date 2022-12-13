package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.puzzle_game_arwa_shamaly.databinding.ActivityLevelsScreenBinding;

public class LevelsScreen extends AppCompatActivity {
ActivityLevelsScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLevelsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}