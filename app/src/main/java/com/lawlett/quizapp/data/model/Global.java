package com.lawlett.quizapp.data.model;

import com.google.gson.annotations.SerializedName;

public class Global {
    @SerializedName("total_num_of_questions")
    private String totalNumOfQuestion;
    @SerializedName("total_num_of_pending_questions")
    private String totalNumOfPendingQuestions;
    @SerializedName("total_num_of_verified_questions")
    private String totalNumOfVerifiedQuestions;
    @SerializedName("total_num_of_rejected_questions")
    private String totalNumOfRejectedQuestions;

    public Global(String totalNumOfQuestion, String totalNumOfPendingQuestions, String totalNumOfVerifiedQuestions, String totalNumOfRejectedQuestions) {
        this.totalNumOfQuestion = totalNumOfQuestion;
        this.totalNumOfPendingQuestions = totalNumOfPendingQuestions;
        this.totalNumOfVerifiedQuestions = totalNumOfVerifiedQuestions;
        this.totalNumOfRejectedQuestions = totalNumOfRejectedQuestions;
    }

    public String getTotalNumOfQuestion() {
        return totalNumOfQuestion;
    }

    public void setTotalNumOfQuestion(String totalNumOfQuestion) {
        this.totalNumOfQuestion = totalNumOfQuestion;
    }

    public String getTotalNumOfPendingQuestions() {
        return totalNumOfPendingQuestions;
    }

    public void setTotalNumOfPendingQuestions(String totalNumOfPendingQuestions) {
        this.totalNumOfPendingQuestions = totalNumOfPendingQuestions;
    }

    public String getTotalNumOfVerifiedQuestions() {
        return totalNumOfVerifiedQuestions;
    }

    public void setTotalNumOfVerifiedQuestions(String totalNumOfVerifiedQuestions) {
        this.totalNumOfVerifiedQuestions = totalNumOfVerifiedQuestions;
    }

    public String getTotalNumOfRejectedQuestions() {
        return totalNumOfRejectedQuestions;
    }

    public void setTotalNumOfRejectedQuestions(String totalNumOfRejectedQuestions) {
        this.totalNumOfRejectedQuestions = totalNumOfRejectedQuestions;
    }
}
