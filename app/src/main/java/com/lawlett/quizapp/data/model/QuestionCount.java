package com.lawlett.quizapp.data.model;

import com.google.gson.annotations.SerializedName;

public class QuestionCount {
    @SerializedName("total_question_count")
    private Integer totalQuestionCount;
    @SerializedName("total_easy_question_count")
    private Integer totalEasyQuestionCount;
    @SerializedName("total_medium_question_count")
    private Integer totalMediumQuestionCount;
    @SerializedName("total_hard_question_count")
    private Integer totalHardQuestionCount;

    public QuestionCount(Integer totalQuestionCount, Integer totalEasyQuestionCount, Integer totalMediumQuestionCount, Integer totalHardQuestionCount) {
        this.totalQuestionCount = totalQuestionCount;
        this.totalEasyQuestionCount = totalEasyQuestionCount;
        this.totalMediumQuestionCount = totalMediumQuestionCount;
        this.totalHardQuestionCount = totalHardQuestionCount;
    }

    public Integer getTotalQuestionCount() {
        return totalQuestionCount;
    }

    public void setTotalQuestionCount(Integer totalQuestionCount) {
        this.totalQuestionCount = totalQuestionCount;
    }

    public Integer getTotalEasyQuestionCount() {
        return totalEasyQuestionCount;
    }

    public void setTotalEasyQuestionCount(Integer totalEasyQuestionCount) {
        this.totalEasyQuestionCount = totalEasyQuestionCount;
    }

    public Integer getTotalMediumQuestionCount() {
        return totalMediumQuestionCount;
    }

    public void setTotalMediumQuestionCount(Integer totalMediumQuestionCount) {
        this.totalMediumQuestionCount = totalMediumQuestionCount;
    }

    public Integer getTotalHardQuestionCount() {
        return totalHardQuestionCount;
    }

    public void setTotalHardQuestionCount(Integer totalHardQuestionCount) {
        this.totalHardQuestionCount = totalHardQuestionCount;
    }
}
