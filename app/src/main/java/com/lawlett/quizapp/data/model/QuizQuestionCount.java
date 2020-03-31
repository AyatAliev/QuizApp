package com.lawlett.quizapp.data.model;

import com.google.gson.annotations.SerializedName;

public class QuizQuestionCount {
    @SerializedName("category_id")
    private Integer categoryId;
    @SerializedName("category_question_count")
    private QuestionCount questionCount;

    public QuizQuestionCount(Integer categoryId, QuestionCount questionCount) {
        this.categoryId = categoryId;
        this.questionCount = questionCount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public QuestionCount getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(QuestionCount questionCount) {
        this.questionCount = questionCount;
    }
}

