package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.puzzle_game_arwa_shamaly.R;
import com.example.puzzle_game_arwa_shamaly.Services.MusicService;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivityHomeScreenBinding;

public class HomeScreen extends AppCompatActivity {
    ActivityHomeScreenBinding binding;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String settingFileName = "settingShared";
    String musicKey = "musicState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(settingFileName, MODE_PRIVATE);
        editor = sp.edit();

        if (sp.getBoolean(musicKey, true)) {
            startService(new Intent(this, MusicService.class));
        }
        binding.btnSetting.setAnimation(
                AnimationUtils.loadAnimation(
                        getBaseContext(), R.anim.lefttoright));
        binding.btnStartPlay.setAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.lefttoright));
        binding.btnLogOut.setAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.lefttoright));
        binding.img.setAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadein));


        binding.btnStartPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), LevelsScreen.class));
            }
        });
        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SettingScreen.class));
            }
        });
        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this, MusicService.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (sp.getBoolean(musicKey, true))
            startService(new Intent(this, MusicService.class));
    }

}