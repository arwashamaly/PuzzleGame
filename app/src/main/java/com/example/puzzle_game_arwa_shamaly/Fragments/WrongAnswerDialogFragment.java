package com.example.puzzle_game_arwa_shamaly.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puzzle_game_arwa_shamaly.Listeners.InFragment;
import com.example.puzzle_game_arwa_shamaly.databinding.FragmentWrongAnswerDialogBinding;


public class WrongAnswerDialogFragment extends DialogFragment {

    private static final String ARG_PARAM_HINT = "hint";

    private String hint;

    InFragment inFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        inFragment = (InFragment) context;
    }

    public WrongAnswerDialogFragment() {
        // Required empty public constructor
    }

    public static WrongAnswerDialogFragment newInstance(String hint) {
        WrongAnswerDialogFragment fragment = new WrongAnswerDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hint = getArguments().getString(ARG_PARAM_HINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentWrongAnswerDialogBinding binding =
                FragmentWrongAnswerDialogBinding.inflate(inflater, container, false);

        if (hint != null) {
            binding.tvHint.setText(hint);
        }

        binding.tvNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inFragment.nextPuzzle();
                dismiss();
            }
        });

        return binding.getRoot();
    }

//   @Override
//    public void onResume() {
//        ViewGroup.LayoutParams params=getDialog().getWindow().getAttributes();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
//        super.onResume();
//    }

}