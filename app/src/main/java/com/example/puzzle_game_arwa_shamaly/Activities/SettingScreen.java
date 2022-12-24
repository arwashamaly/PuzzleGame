package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.puzzle_game_arwa_shamaly.R;
import com.example.puzzle_game_arwa_shamaly.Services.MusicService;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivitySettingScreenBinding;

public class SettingScreen extends AppCompatActivity {
    ActivitySettingScreenBinding binding;
    boolean musicIsOn = true;
    boolean notificationsIsOn = true;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String musicFileName ="musicShared";
    String musicKey="musicState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(musicFileName, MODE_PRIVATE);
        editor = sp.edit();
        musicIsOn=sp.getBoolean(musicKey,true);

        if (musicIsOn) {
            binding.imgMusic.setImageResource(R.drawable.ic_music_on);
        } else {
            binding.imgMusic.setImageResource(R.drawable.ic_music_off);
        }

        binding.tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProfileScreen.class));
            }
        });
        binding.imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicIsOn) {
                    binding.imgMusic.setImageResource(R.drawable.ic_music_off);
                    editor.putBoolean(musicKey, false);
                    editor.commit();
                    stopService(new Intent(getBaseContext(), MusicService.class));
                } else {
                    binding.imgMusic.setImageResource(R.drawable.ic_music_on);
                    editor.putBoolean(musicKey, true);
                    editor.commit();
                    startService(new Intent(getBaseContext(), MusicService.class));
                }

            }
        });
        binding.imgNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationsIsOn) {
                    binding.imgNotifications.setImageResource(R.drawable.ic_notifications_off);
                    notificationsIsOn = false;
                } else {
                    binding.imgNotifications.setImageResource(R.drawable.ic_notifications_active);
                    notificationsIsOn = true;
                }

            }
        });

    }
}