package com.example.puzzle_game_arwa_shamaly.Adapteres;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzle_game_arwa_shamaly.Database.Level;

import java.util.ArrayList;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelHolder> {
ArrayList<Level> levelArrayList;
Context context;

    public LevelAdapter(ArrayList<Level> levelArrayList, Context context) {
        this.levelArrayList = levelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public LevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LevelHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return levelArrayList.size();
    }

    class LevelHolder extends RecyclerView.ViewHolder{

        public LevelHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
