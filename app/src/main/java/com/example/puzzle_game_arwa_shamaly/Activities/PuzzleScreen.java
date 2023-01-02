package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;


import com.example.puzzle_game_arwa_shamaly.Adapteres.FragmentVPAdapter;
import com.example.puzzle_game_arwa_shamaly.Database.Level;
import com.example.puzzle_game_arwa_shamaly.Database.Puzzle;
import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Database.User;
import com.example.puzzle_game_arwa_shamaly.Fragments.ChooseFragment;
import com.example.puzzle_game_arwa_shamaly.Fragments.CompleteFragment;
import com.example.puzzle_game_arwa_shamaly.Fragments.CorrectAnswerDialogFragment;
import com.example.puzzle_game_arwa_shamaly.Fragments.TrueOrFalseFragment;
import com.example.puzzle_game_arwa_shamaly.Fragments.WrongAnswerDialogFragment;
import com.example.puzzle_game_arwa_shamaly.Listeners.InFragment;
import com.example.puzzle_game_arwa_shamaly.R;
import com.example.puzzle_game_arwa_shamaly.Services.NotificationService;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivityPuzzleScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class PuzzleScreen extends AppCompatActivity implements InFragment {
    ActivityPuzzleScreenBinding binding;
    int level_no;
    PuzzleViewModel model;
    int puzzleNum;
    JobInfo info;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String settingFileName = "settingShared";
    String notificationKey = "notificationState";
    String qNumKey = "qNum";

    private CountDownTimer timer;
    int oldScore;
    int unlock_points;
    int oldOverallAssessment;
    int qNum;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(settingFileName, MODE_PRIVATE);
        editor = sp.edit();

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);

        binding.viewPager2.setUserInputEnabled(false);

        level_no = getIntent().getIntExtra("level_no", -1);
        binding.tvLevelNumber.append(" " + level_no);

        //
        qNum = sp.getInt(qNumKey, 0);
        if (qNum != 0) {
            binding.viewPager2.setCurrentItem(qNum);
        }
        Toast.makeText(this, "puzzle " + sp.getInt("puzzle", 0), Toast.LENGTH_SHORT).show();

        //////

        model.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                user = users.get(0);
            }
        });

        model.getAllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                for (int i = 0; i < levels.size(); i++) {
                    Level level = levels.get(i);
                    if (level.getLevel_no() == level_no) {
                        oldScore = level.getScore();
                        unlock_points = level.getUnlock_points();
                        oldOverallAssessment = level.getOverallAssessment();
                        binding.tvStar.setText(String.valueOf(level.getScore()));
                    }
                }
            }
        });

        model.getAllPuzzleByLevelID(level_no).observe(this, new Observer<List<Puzzle>>() {
            @Override
            public void onChanged(List<Puzzle> puzzles) {
                ArrayList<Fragment> fragments = new ArrayList<>();

                for (int i = 0; i < puzzles.size(); i++) {
                    Puzzle puzzle = puzzles.get(i);
                    puzzleNum = puzzles.size();
                    if (puzzle.getPattern_id_fk() == 1) {
                        fragments.add(TrueOrFalseFragment.newInstance(puzzle.getId(), puzzle.getTitle(),
                                puzzle.getTrue_answer(), puzzle.getPoints(), puzzle.getDuration(), puzzle.getHint()));
                    } else if (puzzle.getPattern_id_fk() == 2) {
                        fragments.add(ChooseFragment.newInstance(puzzle.getId(), puzzle.getTitle(), puzzle.getAnswer_1(),
                                puzzle.getAnswer_2(), puzzle.getAnswer_3(), puzzle.getAnswer_4(),
                                puzzle.getTrue_answer(), puzzle.getPoints(), puzzle.getDuration(),
                                puzzle.getHint()));
                    } else if (puzzle.getPattern_id_fk() == 3) {
                        fragments.add(CompleteFragment.newInstance(puzzle.getId(), puzzle.getTitle(), puzzle.getTrue_answer(),
                                puzzle.getPoints(), puzzle.getDuration(), puzzle.getHint()));
                    }
                }

                FragmentVPAdapter adapter = new FragmentVPAdapter(PuzzleScreen.this,
                        fragments);
                binding.viewPager2.setAdapter(adapter);

            }
        });

        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PuzzleScreen.this, "bb " + binding.viewPager2.getCurrentItem(), Toast.LENGTH_SHORT).show();
                Toast.makeText(PuzzleScreen.this, "sss " + puzzleNum, Toast.LENGTH_SHORT).show();
                timer.cancel();
                new AlertDialog.Builder(PuzzleScreen.this)
                        .setTitle(R.string.skipTitleDialog)
                        .setMessage(R.string.skipMessageDialog)
                        .setPositiveButton(R.string.positiveButtonDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (puzzleNum == binding.viewPager2.getCurrentItem() + 1) {
                                    finish();
                                } else {
                                    binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);
                                }
                                model.updateLevel(new Level(level_no,
                                        unlock_points,
                                        oldScore - 3,
                                        oldOverallAssessment));
                            }
                        })
                        .setNegativeButton(R.string.negativeButtonDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                timer.start();
                            }
                        }).create().show();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sp.getBoolean(notificationKey, true)) {
            ComponentName component = new ComponentName(getBaseContext(), NotificationService.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                info = new JobInfo.Builder(1, component)
                        .setPeriodic(24 * 60 * 60 * 1000, 5 * 60 * 1000)
                        .setPersisted(true)
                        .build();
            }
            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.schedule(info);
        }
        editor.putInt(qNumKey, binding.viewPager2.getCurrentItem());
        editor.commit();
    }


    @Override
    public void onAnswer(String dialogName, String hint, int points) {
        timer.cancel();

        Toast.makeText(this, "onAnswer", Toast.LENGTH_SHORT).show();
        if (dialogName.equals("correctAnswerDialog")) {

            CorrectAnswerDialogFragment fragment = CorrectAnswerDialogFragment.newInstance();
            fragment.show(getSupportFragmentManager(), "");

            MediaPlayer.create(getBaseContext(), R.raw.true_answer).start();

            User user1 = new User(
                    user.getUser_name(),
                    user.getEmail(),
                    user.getGender(),
                    user.getCountry(),
                    user.getBirthdate(),
                    user.getNumOfCompletedLevels(),
                    user.getNumOfQuestionsAnswered() + 1,
                    user.getNumOfCorrectAnswer() + 1,
                    user.getNumOfWrongAnswer());
            user1.setId(user.getId());
            model.updateUser(user1);

            int overallAssessment = (oldScore * puzzleNum) * 100 / 100;
            model.updateLevel(new Level(level_no,
                    unlock_points,
                    oldScore + points,
                    overallAssessment));
        } else {
            WrongAnswerDialogFragment fragment = WrongAnswerDialogFragment.newInstance(hint);
            fragment.show(getSupportFragmentManager(), "");

            MediaPlayer.create(getBaseContext(), R.raw.false_answer).start();

            User user1 = new User(
                    user.getUser_name(),
                    user.getEmail(),
                    user.getGender(),
                    user.getCountry(),
                    user.getBirthdate(),
                    user.getNumOfCompletedLevels(),
                    user.getNumOfQuestionsAnswered() + 1,
                    user.getNumOfCorrectAnswer(),
                    user.getNumOfWrongAnswer() + 1);
            user1.setId(user.getId());
            model.updateUser(user1);
        }
    }


    @Override
    public void puzzleInfo(int duration, String hint) {
        Toast.makeText(this, "puzzleInfo", Toast.LENGTH_SHORT).show();

        int pageNum = binding.viewPager2.getCurrentItem();
        binding.tvPuzzleNum.setText(pageNum + 1 + "/" + puzzleNum);

        if (pageNum == 0) {

            //كل متى ينفذ الكود الي في ال interval : onTick
            timer = new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long l) {
                    //الوقت المتبقي للانتهاء : l
                    binding.tvTimer.setText(String.valueOf(l / 1000));
                }

                @Override
                public void onFinish() {
                    binding.tvTimer.setText("time over");
                    WrongAnswerDialogFragment fragment = WrongAnswerDialogFragment.newInstance(hint);
                    fragment.show(getSupportFragmentManager(), "");

                    MediaPlayer.create(getBaseContext(), R.raw.false_answer).start();

                    timer.cancel();
                }
            }.start();

        }else {
            timer.start();
        }
    }


    @Override
    public void nextPuzzle() {
        Toast.makeText(this, "nextPuzzle", Toast.LENGTH_SHORT).show();
        if (puzzleNum == binding.viewPager2.getCurrentItem() + 1) {
            User user1 = new User(
                    user.getUser_name(),
                    user.getEmail(),
                    user.getGender(),
                    user.getCountry(),
                    user.getBirthdate(),
                    user.getNumOfCompletedLevels() + 1,
                    user.getNumOfQuestionsAnswered(),
                    user.getNumOfCorrectAnswer(),
                    user.getNumOfWrongAnswer());
            user1.setId(user.getId());
            model.updateUser(user1);

            finish();
        }
        binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);
    }
}