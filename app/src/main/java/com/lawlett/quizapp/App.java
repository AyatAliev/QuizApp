package com.lawlett.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.lawlett.quizapp.data.local.HistoryStorage;

import com.lawlett.quizapp.data.local.QuizDatabase;
import com.lawlett.quizapp.data.remote.QuizApiClient;
import com.lawlett.quizapp.data.QuizRepository;

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
