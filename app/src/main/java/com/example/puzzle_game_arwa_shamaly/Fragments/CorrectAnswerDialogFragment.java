package com.example.puzzle_game_arwa_shamaly.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puzzle_game_arwa_shamaly.Listeners.InFragment;
import com.example.puzzle_game_arwa_shamaly.databinding.FragmentCorrectAnswerDialogBinding;


public class CorrectAnswerDialogFragment extends DialogFragment {

    InFragment inFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        inFragment= (InFragment) context;
    }

    public CorrectAnswerDialogFragment() {
        // Required empty public constructor
    }


    public static CorrectAnswerDialogFragment newInstance() {
        CorrectAnswerDialogFragment fragment = new CorrectAnswerDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentCorrectAnswerDialogBinding binding=
                FragmentCorrectAnswerDialogBinding.inflate(inflater,container,false);

        binding.tvNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inFragment.nextPuzzle();
                dismiss();
            }
        });

        return binding.getRoot();
    }
}