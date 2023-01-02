package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Database.User;
import com.example.puzzle_game_arwa_shamaly.R;
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

        binding.userDataLayout.setAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce));
        binding.puzzleDataLayout.setAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce));


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
               binding.tvCompletedLevelsData.setText(String.valueOf(user.getNumOfCompletedLevels()));
               binding.tvAnswerQData.setText(String.valueOf(user.getNumOfQuestionsAnswered()));
               binding.tvCorrectAnswerData.setText(String.valueOf(user.getNumOfCorrectAnswer()));
               binding.tvWrongAnswerData.setText(String.valueOf(user.getNumOfWrongAnswer()));
            }
        });

        binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EditProfileScreen.class);
                intent.putExtra("id",user.getId());
                intent.putExtra("username",user.getUser_name());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("gender",user.getGender());
                intent.putExtra("birthdate",user.getBirthdate());
                intent.putExtra("numOfCompletedLevels",user.getNumOfCompletedLevels());
                intent.putExtra("numOfQuestionsAnswered",user.getNumOfQuestionsAnswered());
                intent.putExtra("numOfCorrectAnswer",user.getNumOfCorrectAnswer());
                intent.putExtra("numOfWrongAnswer",user.getNumOfWrongAnswer());
                startActivity(intent);
            }
        });
    }
}