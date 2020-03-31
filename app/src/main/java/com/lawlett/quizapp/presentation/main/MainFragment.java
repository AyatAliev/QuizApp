package com.lawlett.quizapp.presentation.main;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.lawlett.quizapp.core.CoreFragment;
import com.lawlett.quizapp.presentation.quiz.QuizActivity;
import com.lawlett.quizapp.R;
import com.lawlett.quizapp.utils.SimpleOnSeekBarChangeListener;

public class MainFragment extends CoreFragment {
    private TextView seekBar_amount;
    private MainViewModel mViewModel;
    SeekBar seekBar;
    Spinner spinnercat, spinnerdif;
    String difficult;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders
                .of(getActivity()).get(MainViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnercat = view.findViewById(R.id.spinner_category);
        spinnerdif = view.findViewById(R.id.spinner_difficulty);
        seekBar = view.findViewById(R.id.main_seekBar);
        seekBar_amount = view.findViewById(R.id.q_amount_number);

//Button start
        view.findViewById(R.id.main_start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer spinnerCategory = null;
                if (spinnercat.getSelectedItemPosition() != 0) {
                    spinnerCategory = spinnercat.getSelectedItemPosition() + 8;
                }
                QuizActivity.start(getActivity(), seekBar.getProgress(),
                        spinnerCategory,
                        getDifficulty());
            }
        });
        seekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                seekBar_amount.setText(String.valueOf(progress));
            }
        });
    }
    public String getDifficulty() {
        switch (spinnerdif.getSelectedItemPosition()) {
            case 0:
                difficult = null;
                break;
            case 1:
                difficult = "easy";
                break;
            case 2:
                difficult = "medium";
                break;
            case 3:
                difficult = "hard";
                break;
        }
        return difficult;
    }
}