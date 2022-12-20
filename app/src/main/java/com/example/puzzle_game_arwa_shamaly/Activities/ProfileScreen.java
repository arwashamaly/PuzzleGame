package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Database.User;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivityProfileScreenBinding;

import java.util.List;

public class ProfileScreen extends AppCompatActivity {
    ActivityProfileScreenBinding binding;
    PuzzleViewModel model;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);
        model.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                user=users.get(0);
               binding.tvUserNameData.setText(user.getUser_name());
               binding.tvEmailData.setText(user.getEmail());
               binding.tvGenderData.setText(user.getGender());
               binding.tvBirthdateData.setText(user.getBirthdate());
               binding.tvCountryData.setText(user.getCountry());
            }
        });

        binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EditProfileScreen.class);
                intent.putExtra("id",user.getId());
                intent.putExtra("username",binding.tvUserNameData.getText().toString());
                intent.putExtra("email",binding.tvEmailData.getText().toString());
                intent.putExtra("gender",binding.tvGenderData.getText().toString());
                intent.putExtra("birthdate",binding.tvBirthdateData.getText().toString());
                startActivity(intent);
            }
        });
    }
}