package com.example.puzzle_game_arwa_shamaly.Adapteres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzle_game_arwa_shamaly.Database.Level;
import com.example.puzzle_game_arwa_shamaly.Listeners.OnActionLevel;
import com.example.puzzle_game_arwa_shamaly.databinding.LevelItemBinding;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelHolder> {
    List<Level> levelList;
    Context context;
    OnActionLevel onActionLevel;

    public LevelAdapter(List<Level> levelList, Context context, OnActionLevel onActionLevel) {
        this.levelList = levelList;
        this.context = context;
        this.onActionLevel = onActionLevel;
    }

    @NonNull
    @Override
    public LevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LevelItemBinding binding =
                LevelItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new LevelHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelHolder holder, int position) {
        int pos = position;
        Level level = levelList.get(pos);
        if (pos!=0){
            Level level2=levelList.get(pos-1);
            if (level.getLevel_no() <= level.getScore()
                    ||level.getUnlock_points()<=level2.getScore()) {
                holder.tvStar.setText(level.getUnlock_points() + "");
                holder.tvLevelNumber.append(" ");
                holder.tvLevelNumber.append(String.valueOf(level.getLevel_no()));
                holder.tvOverallAssessment.setText(String.valueOf(level.getOverallAssessment())+"%");
                holder.imgLock.setVisibility(View.GONE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onActionLevel.onClickLevel(level.getLevel_no());
                    }
                });
            } else {
                holder.tvStar.setText(level.getUnlock_points() + "");
                holder.tvLevelNumber.append(" ");
                holder.tvLevelNumber.append(String.valueOf(level.getLevel_no()));
                holder.tvOverallAssessment.setText(String.valueOf(level.getOverallAssessment())+"%");
                holder.imgLock.setVisibility(View.VISIBLE);
            }
        }else {
            holder.tvStar.setText(level.getScore() + "");
            holder.tvLevelNumber.append("  "+String.valueOf(level.getLevel_no()));
            holder.imgLock.setVisibility(View.GONE);
            holder.tvOverallAssessment.setText(String.valueOf(level.getOverallAssessment())+"%");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onActionLevel.onClickLevel(level.getLevel_no());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }


    class LevelHolder extends RecyclerView.ViewHolder {
        TextView tvLevelNumber, tvStar,tvOverallAssessment;
        ImageView imgLock;

        public LevelHolder(@NonNull LevelItemBinding binding) {
            super(binding.getRoot());
            tvLevelNumber = binding.tvLevelNumber;
            tvStar = binding.tvStar;
            imgLock = binding.imgLock;
            tvOverallAssessment=binding.tvOverallAssessment;
        }
    }
}
