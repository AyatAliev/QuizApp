package com.lawlett.quizapp.presentation.quiz;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.lawlett.quizapp.R;
import com.lawlett.quizapp.data.model.Question;
import com.lawlett.quizapp.presentation.quiz.recycler.Listener;
import com.lawlett.quizapp.presentation.quiz.recycler.QuizAdapter;
import com.lawlett.quizapp.presentation.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements Listener {
    public static final String EXTRA_AMOUNT = "amount";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_DIFFICULTY = "difficulty";
    private LottieAnimationView loading_animation;
    private QuizViewModel quizViewModel;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    int qAmount;
    private Integer category;
    private String difficulty;
    private Button skipButton;
    private TextView tv_question_amount;
    private TextView categoryTitle;
    private List<Question> listQuestion = new ArrayList<>();
    private ImageView back_ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        recycler_builder();
        getQuestion();
    }


    public void initViews(){
        recyclerView = findViewById(R.id.quiz_recyclerView);
        tv_question_amount = findViewById(R.id.currentProgress);
        progressBar = findViewById(R.id.quiz_progressBar);
        categoryTitle = findViewById(R.id.categoryTitle);
        loading_animation = findViewById(R.id.loading_animation);
        skipButton = findViewById(R.id.skip_btn);
        back_ic = findViewById(R.id.back_ic);
    }
    @SuppressLint("ClickableViewAccessibility")
    public void recycler_builder() {
        adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void getQuestion() {
        qAmount = getIntent().getIntExtra(EXTRA_AMOUNT, 25);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        if (category == 8)
            category = null;
        quizViewModel.queryOnData(qAmount, category, difficulty);
        quizViewModel.dataWithQuestion.observe(this, questions -> {
            if (questions.size() > 0) {
                listQuestion = questions;
                recyclerView.setVisibility(View.VISIBLE);
                adapter.updateQuestions(questions);
                getPosition();
                loading_animation.setVisibility(View.INVISIBLE);
                skipButton.setVisibility(View.VISIBLE);
            }
        });
        quizViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                loading_animation.setVisibility(View.VISIBLE);
            } else {
                loading_animation.setVisibility(View.GONE);

            }

        });

        quizViewModel.finishEvent.observe(this, aVoid -> finish());
        quizViewModel.openResultEvent.observe(this, integer -> ResultActivity.start(QuizActivity.this, integer));
        quizViewModel.init(10, 1, "easy");
    }

    public static void start(Context context, int amount, Integer category, String difficulty) {
        context.startActivity(new Intent(context, QuizActivity.class)
                .putExtra(EXTRA_AMOUNT, amount)
                .putExtra(EXTRA_CATEGORY, category)
                .putExtra(EXTRA_DIFFICULTY, difficulty));
    }

    public void backImageClick(View view) {
        quizViewModel.onBackPress();
    }

    @Override
    public void onBackPressed() {
        quizViewModel.onBackPress();
    }

    public void getPosition() {
        quizViewModel.currentQuestionPosition.observe(this, integer -> {
            recyclerView.scrollToPosition(integer);
            tv_question_amount.setText(integer + 1 + "/" + qAmount);
            progressBar.setVisibility(View.VISIBLE);
            tv_question_amount.setVisibility(View.VISIBLE);
            progressBar.setProgress(integer + 1);
            progressBar.setMax(qAmount);
            categoryTitle.setVisibility(View.VISIBLE);
            categoryTitle.setText(listQuestion.get(integer).getCategory());
            back_ic.setVisibility(View.VISIBLE);
        });
    }

    public void skipClick(View view) {
        quizViewModel.onSkip();
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        quizViewModel.onAnswerClick(position, selectedAnswerPosition);
    }
}