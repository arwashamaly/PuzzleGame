package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.puzzle_game_arwa_shamaly.databinding.ActivityProfileScreenBinding;

public class ProfileScreen extends AppCompatActivity {
ActivityProfileScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),EditProfileScreen.class));
            }
        });
    }
}