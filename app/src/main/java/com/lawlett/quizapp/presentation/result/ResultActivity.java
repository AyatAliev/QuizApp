package com.lawlett.quizapp.presentation.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lawlett.quizapp.R;
import com.lawlett.quizapp.data.model.QuizResult;

public class ResultActivity extends AppCompatActivity {
    public static final String EXTRA_RESULT_ID = "RESULT_ID";
    TextView resultPercent, difficultValue, correctAnswersAmount, resultCategory;
    ResultViewModel viewModel;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        id = getIntent().getIntExtra(EXTRA_RESULT_ID, 0);
        initViews();
        viewModel.getResult(id);
        Log.e("resultActivity", "resultActivity" + id );
        showResult();
    }
    public static void start(Context context, Integer resultId) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_RESULT_ID, resultId));
    }

    public void showResult() {
        viewModel.quizResult.observe(this, new Observer<QuizResult>() {
            @Override
            public void onChanged(QuizResult quizResult) {
                resultCategory.setText("Category: " + quizResult.getCategory());
                difficultValue.setText(quizResult.getDifficulty()+"");
                correctAnswersAmount.setText(quizResult.getCorrectAnswersAmount() + "/" + quizResult.getQuestions().size());
                int correctAnswersPercent = (int) ((double) quizResult.getCorrectAnswersAmount() / quizResult.getQuestions().size() * 100);
                resultPercent.setText(correctAnswersPercent + " %");
                Log.e("popopo", "difficulty "+difficultValue + "-------" +quizResult.getDifficulty() );
            }
        });
    }
    public void initViews(){
        viewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        difficultValue = findViewById(R.id.difficulty_result);
        resultPercent = findViewById(R.id.correct_answer_procent);
        correctAnswersAmount = findViewById(R.id.correct_answer_result);
        resultCategory = findViewById(R.id.category_result);
    }


    public void finishQuiz(View view) {
        finish();
    }
}
