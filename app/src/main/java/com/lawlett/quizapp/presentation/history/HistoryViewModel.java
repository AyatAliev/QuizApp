package com.lawlett.quizapp.presentation.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lawlett.quizapp.App;
import com.lawlett.quizapp.data.model.History;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    LiveData<List<History>> historyLiveData = App.quizRepository.getAllHistory();

}
