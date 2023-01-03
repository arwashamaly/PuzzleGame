package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.puzzle_game_arwa_shamaly.Database.Level;
import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Database.User;
import com.example.puzzle_game_arwa_shamaly.R;
import com.example.puzzle_game_arwa_shamaly.Services.MusicService;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivitySettingScreenBinding;

import java.util.List;

public class SettingScreen extends AppCompatActivity {
    ActivitySettingScreenBinding binding;

    boolean musicIsOn;
    boolean notificationsIsOn;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String settingFileName = "settingShared";
    String musicKey = "musicState";
    String qNumKey = "qNum";

    String notificationKey = "notificationState";

    PuzzleViewModel model;
    User user;
    List<Level> levelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.linear.setAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.bounce));

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);
        model.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                user = users.get(0);
            }
        });
        model.getAllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                levelList = levels;
            }
        });

        sp = getSharedPreferences(settingFileName, MODE_PRIVATE);
        editor = sp.edit();


        if (sp.getBoolean(musicKey, true)) {
            binding.imgMusic.setImageResource(R.drawable.ic_music_on);
        } else {
            binding.imgMusic.setImageResource(R.drawable.ic_music_off);
        }
        if (sp.getBoolean(notificationKey, true)) {
            binding.imgNotifications.setImageResource(R.drawable.ic_notifications_active);
        } else {
            binding.imgNotifications.setImageResource(R.drawable.ic_notifications_off);
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
                musicIsOn = sp.getBoolean(musicKey, true);
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
                notificationsIsOn = sp.getBoolean(notificationKey, true);
                if (notificationsIsOn) {
                    binding.imgNotifications.setImageResource(R.drawable.ic_notifications_off);
                    editor.putBoolean(notificationKey, false);
                    editor.commit();
                } else {
                    binding.imgNotifications.setImageResource(R.drawable.ic_notifications_active);
                    editor.putBoolean(notificationKey, true);
                    editor.commit();
                }
            }
        });

        binding.tvReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SettingScreen.this)
                        .setTitle(R.string.replayTitleDialog)
                        .setMessage(R.string.replayMessageDialog)
                        .setPositiveButton(R.string.positiveButtonDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editor.putInt(qNumKey, 0);
                                editor.commit();
                                User user1 =
                                        new User(user.getUser_name(),
                                                user.getEmail(),
                                                user.getGender(),
                                                user.getCountry(),
                                                user.getBirthdate(),
                                                0,
                                                0,
                                                0,
                                                0);
                                user1.setId(user.getId());
                                model.updateUser(user1);
                                for (int j = 0; j < levelList.size(); j++) {
                                    Level level = levelList.get(j);
                                    Level level1 = new Level(
                                            level.getLevel_no(),
                                            level.getUnlock_points(),
                                            0,
                                            0);
                                    model.updateLevel(level1);
                                }
                            }
                        })
                        .setNegativeButton(R.string.negativeButtonDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).create().show();
            }
        });
    }
}