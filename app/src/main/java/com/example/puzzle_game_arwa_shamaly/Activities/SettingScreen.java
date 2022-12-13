package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.puzzle_game_arwa_shamaly.R;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivitySettingScreenBinding;

public class SettingScreen extends AppCompatActivity {
ActivitySettingScreenBinding binding;
boolean musicIsOn=true;
boolean notificationsIsOn=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySettingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),ProfileScreen.class));
            }
        });
        binding.imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicIsOn){
                    binding.imgMusic.setImageResource(R.drawable.ic_music_off);
                    musicIsOn=false;
                }else {
                    binding.imgMusic.setImageResource(R.drawable.ic_music_on);
                    musicIsOn=true;
                }

            }
        });
        binding.imgNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notificationsIsOn){
                    binding.imgNotifications.setImageResource(R.drawable.ic_notifications_off);
                    notificationsIsOn=false;
                }else {
                    binding.imgNotifications.setImageResource(R.drawable.ic_notifications_active);
                    notificationsIsOn=true;
                }

            }
        });

    }
}