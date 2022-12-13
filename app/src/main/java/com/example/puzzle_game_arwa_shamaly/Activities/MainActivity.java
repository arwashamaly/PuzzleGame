package com.example.puzzle_game_arwa_shamaly.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.puzzle_game_arwa_shamaly.Database.Level;
import com.example.puzzle_game_arwa_shamaly.Database.Pattern;
import com.example.puzzle_game_arwa_shamaly.Database.Puzzle;
import com.example.puzzle_game_arwa_shamaly.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();

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
        System.out.println(json);
        Toast.makeText(this, ""+ json, Toast.LENGTH_SHORT).show();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                String item = jsonArray.get(i).toString();
                JSONObject jsonObject = new JSONObject(item);

                int level_no = jsonObject.getInt("level_no");
                int unlock_points = jsonObject.getInt("unlock_points");

                Level level = new Level(level_no,unlock_points);

                JSONArray questionsJsonArray = jsonObject.getJSONArray("questions");

                for (int j = 0; j < questionsJsonArray.length(); j++) {
                    JSONObject questionsJsonObject =
                            new JSONObject(questionsJsonArray.get(i).toString());

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

                    Puzzle puzzle = new Puzzle(id,title,answer_1,answer_2,answer_3,answer_4,
                            true_answer,points,duration,hint);

                    JSONObject pattern = questionsJsonObject.getJSONObject("pattern");
                    int pattern_id = pattern.getInt("pattern_id");
                    String pattern_name = pattern.getString("pattern_name");

                    Pattern pattern1 = new Pattern(pattern_id,pattern_name);

                    Toast.makeText(this, pattern_name + " " + title + " " + true_answer, Toast.LENGTH_SHORT).show();

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readFromAssets() {
        Log.d("jsonTest", "im here: ");
        String json = null;
        try {
            InputStream inputStream = getAssets().open("puzzleGameData.json");
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read();
            Log.d("jsonnnnn", ""+inputStream.read());
            Log.d("jsonnnnn", ""+json);
            inputStream.close();
            json = new String(bytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Log.d("json", json);
        return json;
       /* try {

            JSONArray levels  = new JSONArray("../../../../../assets/puzzleGameData.json");
            Log.d("jsonTest", ""+levels.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";*/
    }
}