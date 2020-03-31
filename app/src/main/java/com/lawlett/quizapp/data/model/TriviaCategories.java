package com.lawlett.quizapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TriviaCategories {
    @SerializedName("trivia_categories")
    private List<Categories> categories;

    public TriviaCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
