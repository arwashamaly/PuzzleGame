package com.example.puzzle_game_arwa_shamaly.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puzzle_game_arwa_shamaly.databinding.FragmentChooseBinding;

public class ChooseFragment extends Fragment {

    private static final String ARG_PARAM_TITLE = "title";
    private static final String ARG_PARAM_ANSWER_1 = "answer_1";
    private static final String ARG_PARAM_ANSWER_2 = "answer_2";
    private static final String ARG_PARAM_ANSWER_3 = "answer_3";
    private static final String ARG_PARAM_ANSWER_4 = "answer_4";
    private static final String ARG_PARAM_TRUE_ANSWER = "true_answer";
    private static final String ARG_PARAM_POINTS = "points";
    private static final String ARG_PARAM_DURATION = "duration";
    private static final String ARG_PARAM_HINT = "hint";

    private String title;
    private String true_answer;
    private String hint;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private int points;
    private int duration;

    public ChooseFragment() {
        // Required empty public constructor
    }

    public static ChooseFragment
    newInstance(String title, String answer_1, String answer_2, String answer_3, String answer_4,
                String true_answer, int points, int duration, String hint) {
        ChooseFragment fragment = new ChooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_TITLE, title);
        args.putString(ARG_PARAM_ANSWER_1, answer_1);
        args.putString(ARG_PARAM_ANSWER_2, answer_2);
        args.putString(ARG_PARAM_ANSWER_3, answer_3);
        args.putString(ARG_PARAM_ANSWER_4, answer_4);
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
            answer_1 = getArguments().getString(ARG_PARAM_ANSWER_1);
            answer_2 = getArguments().getString(ARG_PARAM_ANSWER_2);
            answer_3 = getArguments().getString(ARG_PARAM_ANSWER_3);
            answer_4 = getArguments().getString(ARG_PARAM_ANSWER_4);
            true_answer = getArguments().getString(ARG_PARAM_TRUE_ANSWER);
            hint = getArguments().getString(ARG_PARAM_HINT);
            points = getArguments().getInt(ARG_PARAM_POINTS);
            duration = getArguments().getInt(ARG_PARAM_DURATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChooseBinding binding = FragmentChooseBinding.inflate(
                inflater, container, false);
        binding.tvChooseQ.setText(title);
        binding.btnAns1.setText(answer_1);
        binding.btnAns2.setText(answer_2);
        binding.btnAns3.setText(answer_3);
        binding.btnAns4.setText(answer_4);

        return binding.getRoot();
    }
}