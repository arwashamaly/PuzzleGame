package com.example.puzzle_game_arwa_shamaly.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puzzle_game_arwa_shamaly.R;
import com.example.puzzle_game_arwa_shamaly.databinding.FragmentTrueOrFalseBinding;

public class TrueOrFalseFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TrueOrFalseFragment() {
        // Required empty public constructor
    }

    public static TrueOrFalseFragment
    newInstance(String title, String true_answer, int points,
                String answer_1, String answer_2, String answer_3,
                String answer_4, int duration, String hint) {
        TrueOrFalseFragment fragment = new TrueOrFalseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, true_answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTrueOrFalseBinding binding =
                FragmentTrueOrFalseBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}