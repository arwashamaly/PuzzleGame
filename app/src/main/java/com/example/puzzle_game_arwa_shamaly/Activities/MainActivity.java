package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.puzzle_game_arwa_shamaly.Database.Level;
import com.example.puzzle_game_arwa_shamaly.Database.Pattern;
import com.example.puzzle_game_arwa_shamaly.Database.Puzzle;
import com.example.puzzle_game_arwa_shamaly.Database.PuzzleViewModel;
import com.example.puzzle_game_arwa_shamaly.Database.User;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    PuzzleViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(PuzzleViewModel.class);
        model.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size() == 0) {
                    User user = new User("player1",
                            "yourEmail@gmail.com",
                            "Male",
                            "Add it from the edit icon",
                            "0/0/0000");
                    model.insertUser(user);
                }
            }
        });
        model.getAllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                if (levels.size() == 0) {
                    getData();
                    addThePuzzles();
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), HomeScreen.class));
                finish();
            }
        }, 3000);
    }

    private void getData() {
        String json = readFromAssets();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int level_no = jsonObject.getInt("level_no");
                int unlock_points = jsonObject.getInt("unlock_points");

                Level level = new Level(level_no, unlock_points);
                model.insertLevel(level);

                JSONArray questionsJsonArray = jsonObject.getJSONArray("questions");
                for (int j = 0; j < questionsJsonArray.length(); j++) {
                    JSONObject questionsJsonObject =
                            questionsJsonArray.getJSONObject(j);

                    JSONObject patternJsonObject = questionsJsonObject.getJSONObject("pattern");
                    int pattern_id = patternJsonObject.getInt("pattern_id");
                    String pattern_name = patternJsonObject.getString("pattern_name");

                    Pattern pattern = new Pattern(pattern_id, pattern_name);
                    model.insertPattern(pattern);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addThePuzzles() {
        String json = readFromAssets();
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int level_no = jsonObject.getInt("level_no");

                JSONArray questionsJsonArray = jsonObject.getJSONArray("questions");
                for (int j = 0; j < questionsJsonArray.length(); j++) {
                    JSONObject questionsJsonObject =
                            questionsJsonArray.getJSONObject(j);

                    int id = questionsJsonObject.getInt("id");
                    String title = questionsJsonObject.getString("title");
                    String answer_1 = questionsJsonObject.getString("answer_1");
                    String answer_2 = questionsJsonObject.getString("answer_2");
                    String answer_3 = questionsJsonObject.getString("answer_3");
                    String answer_4 = questionsJsonObject.getString("answer_4");
                    String true_answer = questionsJsonObject.getString("true_answer");
                    int points = questionsJsonObject.getInt("points");
                    int duration = questionsJsonObject.getInt("duration");
                    String hint = questionsJsonObject.getString("hint");

                    JSONObject patternJsonObject = questionsJsonObject.getJSONObject("pattern");
                    int pattern_id = patternJsonObject.getInt("pattern_id");

                    Puzzle puzzle = new Puzzle(id, title, answer_1, answer_2, answer_3, answer_4,
                            true_answer, points, duration, hint, level_no, pattern_id);

                    model.insertPuzzle(puzzle);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readFromAssets() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("puzzleGameData.json");
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            json = new String(bytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

}