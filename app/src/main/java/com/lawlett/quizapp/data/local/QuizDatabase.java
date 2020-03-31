package com.lawlett.quizapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lawlett.quizapp.data.model.QuizResult;

@Database(entities = {QuizResult.class},version = 1,exportSchema = false)

public abstract class QuizDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
}
