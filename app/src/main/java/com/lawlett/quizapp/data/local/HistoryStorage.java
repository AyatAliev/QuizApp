package com.lawlett.quizapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.lawlett.quizapp.data.model.History;
import com.lawlett.quizapp.data.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage  {
    private HistoryDao mHistoryDao;

    public HistoryStorage(HistoryDao historyDao) {
        this.mHistoryDao = historyDao;
    }

    public int saveQuizResult(QuizResult quizResult) {
        return (int) mHistoryDao.insert(quizResult);
    }

    public void delete(int id) {
        mHistoryDao.deleteById(id);
    }

    public void deleteAll() {
        mHistoryDao.deleteAll();
    }

    public QuizResult get(int id){
        return mHistoryDao.get(id);
    }

    public LiveData<List<QuizResult>> getAll() {
        return mHistoryDao.getAll();
    }

    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(), quizResult -> {
            ArrayList<History> histories = new ArrayList<>();
            if (quizResult.size() > 0) {
                for (int i = 0; i < quizResult.size(); i++) {
                    histories.add(i, new History(quizResult.get(i).getId(),
                            quizResult.get(i).getCategory(),
                            quizResult.get(i).getDifficulty(),
                            quizResult.get(i).getCorrectAnswersAmount(),
                            quizResult.get(i).getQuestions().size(),
                            quizResult.get(i).getCreateAt()));
                }
            }
            return histories;
        });
    }
}