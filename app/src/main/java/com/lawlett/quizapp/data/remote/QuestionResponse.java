package com.lawlett.quizapp.data.remote;

import com.google.gson.annotations.SerializedName;
import com.lawlett.quizapp.data.model.Question;

import java.util.List;

public class QuestionResponse {
    @SerializedName("response_code")
    private int responseCode;

    private List<Question> results;

    public QuestionResponse(int responseCode, List<Question> results) {
        this.responseCode = responseCode;
        this.results = results;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
