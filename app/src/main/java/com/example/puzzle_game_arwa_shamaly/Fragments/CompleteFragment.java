package com.example.puzzle_game_arwa_shamaly.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puzzle_game_arwa_shamaly.R;
import com.example.puzzle_game_arwa_shamaly.databinding.FragmentCompleteBinding;

public class CompleteFragment extends Fragment {

    private static final String ARG_PARAM_TITLE = "title";
    private static final String ARG_PARAM_TRUE_ANSWER = "true_answer";
    private static final String ARG_PARAM_POINTS = "points";
    private static final String ARG_PARAM_DURATION = "duration";
    private static final String ARG_PARAM_HINT = "hint";

    private String title;
    private String true_answer;
    private String hint;
    private int points;
    private int duration;

    public CompleteFragment() {
        // Required empty public constructor
    }


    public static CompleteFragment
    newInstance(String title, String true_answer, int points, int duration, String hint) {
        CompleteFragment fragment = new CompleteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_TITLE, title);
        args.putString(ARG_PARAM_TRUE_ANSWER, true_answer);
        args.putInt(ARG_PARAM_POINTS, points);
        args.putInt(ARG_PARAM_DURATION, duration);
        args.putString(ARG_PARAM_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM_TITLE);
            true_answer = getArguments().getString(ARG_PARAM_TRUE_ANSWER);
            hint = getArguments().getString(ARG_PARAM_HINT);
            points = getArguments().getInt(ARG_PARAM_POINTS);
            duration = getArguments().getInt(ARG_PARAM_DURATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCompleteBinding binding =
                FragmentCompleteBinding.inflate(inflater, container, false);
        binding.tvCompleteQ.setText(title);
        binding.btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAnswer=binding.etCompleteAnswer.getText().toString().trim();
                if (userAnswer.equals(true_answer)){

                }else{

                }
            }
        });
        return binding.getRoot();
    }
}