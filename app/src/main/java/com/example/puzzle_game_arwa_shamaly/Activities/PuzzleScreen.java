package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.puzzle_game_arwa_shamaly.Adapteres.FragmentVPAdapter;
import com.example.puzzle_game_arwa_shamaly.Database.Pattern;
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

        //  binding.viewPager2.setUserInputEnabled(false);

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);

        level_no = getIntent().getIntExtra("level_no", -1);

        model.getAllPattern().observe(this, new Observer<List<Pattern>>() {
            @Override
            public void onChanged(List<Pattern> patterns) {
                for (int i = 0; i < patterns.size(); i++) {
                    Pattern p = patterns.get(i);
                }
            }
        });


        model.getAllPuzzleByLevelID(level_no).observe(this, new Observer<List<Puzzle>>() {
            @Override
            public void onChanged(List<Puzzle> puzzles) {
                ArrayList<Fragment> fragments = new ArrayList<>();

                for (int i = 0; i < puzzles.size(); i++) {
                    Puzzle puzzle = puzzles.get(i);
                    //المفروض جايه مع كل سؤال patt و بنقارنها بقيمة
                    if (puzzle.getPattern_id_fk() == 1) {
                        fragments.add(TrueOrFalseFragment.newInstance(puzzle.getTitle(),
                                puzzle.getTrue_answer(), puzzle.getPoints(), puzzle.getDuration(), puzzle.getHint()));
                    } else if (puzzle.getPattern_id_fk() == 2) {
                        fragments.add(ChooseFragment.newInstance(puzzle.getTitle(), puzzle.getAnswer_1(),
                                puzzle.getAnswer_2(), puzzle.getAnswer_3(), puzzle.getAnswer_4(),
                                puzzle.getTrue_answer(), puzzle.getPoints(), puzzle.getDuration(),
                                puzzle.getHint()));
                    } else {
                        fragments.add(CompleteFragment.newInstance(puzzle.getTitle(),puzzle.getTrue_answer(),
                                puzzle.getPoints(),puzzle.getDuration(),puzzle.getHint()));
                    }
                }

                FragmentVPAdapter adapter = new FragmentVPAdapter(PuzzleScreen.this,
                        fragments);
                binding.viewPager2.setAdapter(adapter);
            }
        });


    }
}