package com.lawlett.quizapp.presentation.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lawlett.quizapp.App;
import com.lawlett.quizapp.data.model.QuizResult;

public class ResultViewModel extends ViewModel {
    MutableLiveData<QuizResult> quizResult = new MutableLiveData<>();

    public void getResult(Integer id) {
        quizResult.setValue(App.quizDatabase.historyDao().get(id));
    }
}
