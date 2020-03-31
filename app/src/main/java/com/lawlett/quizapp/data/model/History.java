package com.lawlett.quizapp.data.model;

import java.util.Date;

public class History {
    private int id;

    private String category;

    private String difficulty;

    private int questionAmount;

    private int correctAnswers;

    private Date createAt;

    public History(int id, String category, String difficulty, int questionAmount, int correctAnswers, Date createAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questionAmount = questionAmount;
        this.correctAnswers = correctAnswers;
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

    public int getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(int questionAmount) {
        this.questionAmount = questionAmount;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
