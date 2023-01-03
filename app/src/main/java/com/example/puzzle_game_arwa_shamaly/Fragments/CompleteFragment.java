package com.example.puzzle_game_arwa_shamaly.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puzzle_game_arwa_shamaly.Listeners.InFragment;
import com.example.puzzle_game_arwa_shamaly.databinding.FragmentCompleteBinding;


public class CompleteFragment extends Fragment {

    private static final String ARG_PARAM_ID = "id";
    private static final String ARG_PARAM_TITLE = "title";
    private static final String ARG_PARAM_TRUE_ANSWER = "true_answer";
    private static final String ARG_PARAM_POINTS = "points";
    private static final String ARG_PARAM_DURATION = "duration";
    private static final String ARG_PARAM_HINT = "hint";

    private int id;
    private String title;
    private String true_answer;
    private String hint;
    private int points;
    private int duration;

    InFragment inFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        inFragment = (InFragment) context;
    }

    public CompleteFragment() {
        // Required empty public constructor
    }


    public static CompleteFragment
    newInstance(int id, String title, String true_answer, int points, int duration, String hint) {
        CompleteFragment fragment = new CompleteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_ID, id);
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
        Log.d("arwaFragmentTest", "onCreateView: im here 1 ");
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_PARAM_ID);
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
        Log.d("arwaFragmentTest", "onCreateView: im here 2 ");
        inFragment.puzzleInfo(duration, hint);
        Log.d("arwaFragmentTest", "onCreateView: im here 3 ");

        binding.btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAnswer = binding.etCompleteAnswer.getText().toString().trim();
                if (userAnswer.equals(true_answer)) {
                    inFragment.onAnswer("correctAnswerDialog", hint, points);
                } else {
                    inFragment.onAnswer("WrongAnswerDialog", hint, points);
                }
            }
        });

        return binding.getRoot();
    }
}