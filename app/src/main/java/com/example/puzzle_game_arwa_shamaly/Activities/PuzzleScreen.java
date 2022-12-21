package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.puzzle_game_arwa_shamaly.Adapteres.FragmentVPAdapter;
import com.example.puzzle_game_arwa_shamaly.Database.Puzzle;
import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Fragments.ChooseFragment;
import com.example.puzzle_game_arwa_shamaly.Fragments.CompleteFragment;
import com.example.puzzle_game_arwa_shamaly.Fragments.TrueOrFalseFragment;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivityPuzzleScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class PuzzleScreen extends AppCompatActivity {
    ActivityPuzzleScreenBinding binding;
    int level_no;
    PuzzleViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);

        level_no = getIntent().getIntExtra("level_no", -1);

        ArrayList<Fragment> fragments = new ArrayList<>();
        int patt=0;

        model.getAllPuzzleByLevelID(level_no).observe(this, new Observer<List<Puzzle>>() {
            @Override
            public void onChanged(List<Puzzle> puzzles) {
                for (int i = 0; i < puzzles.size(); i++) {
                    Puzzle puzzle = puzzles.get(i);
                    //المفروض جايه مع كل سؤال patt و بنقارنها بقيمة
                    if (patt==1){
                        fragments.add(new TrueOrFalseFragment());
                    }else if (patt==2){
                        fragments.add(new ChooseFragment());

                    }else {
                        fragments.add(new CompleteFragment());
                    }
                }

            }
        });


        FragmentVPAdapter adapter = new FragmentVPAdapter(this, fragments);
        binding.viewPager2.setAdapter(adapter);
    }
}