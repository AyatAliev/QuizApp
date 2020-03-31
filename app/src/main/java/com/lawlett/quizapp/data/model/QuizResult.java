package com.lawlett.quizapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lawlett.quizapp.data.db.converters.DataConverter;
import com.lawlett.quizapp.data.db.converters.QuestionConverter;

import java.util.Date;
import java.util.List;
@Entity(tableName = "quiz_result")
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "questions")
    @TypeConverters({QuestionConverter.class})
    private List<Question> questions;

    @ColumnInfo(name = "correct_answers_amount")
    private int correctAnswersAmount;

    @TypeConverters({DataConverter.class})
    @ColumnInfo(name = "create_at")
    private Date createAt;

    public QuizResult(int id, String category, String difficulty, List<Question> questions, int correctAnswersAmount, Date createAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswersAmount = correctAnswersAmount;
        this.createAt = createAt;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectAnswersAmount() {
        return correctAnswersAmount;
    }

    public void setCorrectAnswersAmount(int correctAnswersAmount) {
        this.correctAnswersAmount = correctAnswersAmount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


}
