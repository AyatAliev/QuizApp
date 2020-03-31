package com.lawlett.quizapp.presentation.quiz;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
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
    LinearLayout quizLinear;
    LottieAnimationView loading;
    private QuizViewModel quizViewModel;
    ProgressBar progress;
    RecyclerView recyclerView;
    QuizAdapter adapter;
    int qAmount;
    Integer category;
    String difficulty;
    TextView category_title;
    List<Question> listQuestion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loading = findViewById(R.id.loading_lottie);
        category_title = findViewById(R.id.category_title);
        quizLinear = findViewById(R.id.quiz_linear);
        recyclerView = findViewById(R.id.quiz_recycler);
        progress = findViewById(R.id.quiz_progressbar);
        recycler_builder();
        getQuestion();
        quizViewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.scrollToPosition(integer);
            }
        });
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
        qAmount = getIntent().getIntExtra(EXTRA_AMOUNT, 5);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);

        if (category == 8) category = null;

        quizViewModel = ViewModelProviders
                .of(this)
                .get(QuizViewModel.class);

        quizViewModel.queryOnData(qAmount, category, difficulty);

        quizViewModel.dataWithQuestion.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.updateQuestions(questions);
                listQuestion = questions;
                getPosition();

            }
        });
        quizViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    quizLinear.setVisibility(View.GONE);
                    loading.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.GONE);
                    quizLinear.setVisibility(View.VISIBLE);

                }

            }

        });

        quizViewModel.finishEvent.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                finish();
            }
        });
        quizViewModel.openResultEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ResultActivity.start(QuizActivity.this, integer);
            }
        });
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
        quizViewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.scrollToPosition(integer);
                progress.setProgress(integer + 1);
                progress.setMax(qAmount);
                category_title.setText(listQuestion.get(integer).getCategory());
            }
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