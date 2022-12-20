package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.puzzle_game_arwa_shamaly.Adapteres.LevelAdapter;
import com.example.puzzle_game_arwa_shamaly.Database.Level;
import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Listeners.OnActionLevel;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivityLevelsScreenBinding;

import java.util.List;

public class LevelsScreen extends AppCompatActivity {
    ActivityLevelsScreenBinding binding;
    PuzzleViewModel model;
    LevelAdapter levelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);
        model.getAllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                levelAdapter = new LevelAdapter(levels, getBaseContext(), new OnActionLevel() {
                    @Override
                    public void onClickLevel(int level_no) {
                        Intent intent = new Intent(getBaseContext(),PuzzleScreen.class);
                        intent.putExtra("level_no",level_no);
                        startActivity(intent);
                    }
                });
                binding.rcLevel.setAdapter(levelAdapter);
                binding.rcLevel.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                        RecyclerView.VERTICAL,false));
            }
        });

    }
}