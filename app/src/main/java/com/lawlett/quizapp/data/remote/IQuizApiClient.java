package com.lawlett.quizapp.data.remote;

import android.content.Intent;

import com.lawlett.quizapp.core.ICoreCallback;
import com.lawlett.quizapp.data.model.Global;
import com.lawlett.quizapp.data.model.GlobalResponse;
import com.lawlett.quizapp.data.model.Question;
import com.lawlett.quizapp.data.model.QuizQuestionCount;
import com.lawlett.quizapp.data.model.TriviaCategories;

import java.util.List;

public  interface IQuizApiClient {
    void getQuestion(int amount, Integer category, String difficulty, QuestionCallback callback);

    void getGlobal(GlobalCallback globalCallback);

    void getQuestionResponse(Integer category,QuestionResponseCallback questionResponseCallback);

    void getTriviaCategoryCallback(TriviaCategoryCallback triviaCategoryCallback);

    interface QuestionCallback extends ICoreCallback<List<Question>> {
    }

    interface GlobalCallback extends ICoreCallback<GlobalResponse> {
    }

    interface QuestionResponseCallback extends ICoreCallback<QuizQuestionCount> {
    }

    interface TriviaCategoryCallback extends ICoreCallback<TriviaCategories> {
    }

}
