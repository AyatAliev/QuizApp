package com.lawlett.quizapp;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lawlett.quizapp.data.local.HistoryStorage;

import com.lawlett.quizapp.data.local.QuizDatabase;
import com.lawlett.quizapp.data.local.QuizLocalDataSource;
import com.lawlett.quizapp.data.model.History;
import com.lawlett.quizapp.data.remote.QuizApiClient;
import com.lawlett.quizapp.data.remote.QuizRepository;

public class App extends Application {
    public static QuizRepository quizRepository;
    public static QuizDatabase quizDatabase;



    @Override
    public void onCreate() {
        super.onCreate();

        quizDatabase = Room.databaseBuilder(this, QuizDatabase.class, "quiz"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        quizDatabase.historyDao();
        quizRepository = new QuizRepository(new HistoryStorage(quizDatabase.historyDao()), new QuizApiClient());

    }
}
