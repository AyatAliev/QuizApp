package com.lawlett.quizapp.data.remote;

import com.lawlett.quizapp.data.model.GlobalResponse;
import com.lawlett.quizapp.data.model.QuizQuestionCount;
import com.lawlett.quizapp.data.model.TriviaCategories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestion(int amount, Integer category, String difficulty, final QuestionCallback callback) {
        Call<QuestionResponse> call = client.getQuestion(amount,
                category, difficulty);

        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body().getResults());
                    } else {
                        callback.onFailure(new Exception("Body is empty"));
                    }
                } else {
                    callback.onFailure(new Exception("Response error" + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                callback.onFailure(new Exception(t));

            }
        });
    }

    @Override
    public void getGlobal(final GlobalCallback globalCallback) {
        Call<GlobalResponse> call = client.getGlobal();
        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {
                globalCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {
                globalCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getQuestionResponse(Integer category, final QuestionResponseCallback questionResponseCallback) {
        Call<QuizQuestionCount> call = client.getQuestionCount(category);
        call.enqueue(new Callback<QuizQuestionCount>() {
            @Override
            public void onResponse(Call<QuizQuestionCount> call, Response<QuizQuestionCount> response) {
                questionResponseCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<QuizQuestionCount> call, Throwable t) {
                questionResponseCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getTriviaCategoryCallback(final TriviaCategoryCallback triviaCategoryCallback) {
        Call<TriviaCategories> call = client.getTriviaCategories();
        call.enqueue(new Callback<TriviaCategories>() {
            @Override
            public void onResponse(Call<TriviaCategories> call, Response<TriviaCategories> response) {
                triviaCategoryCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TriviaCategories> call, Throwable t) {
                triviaCategoryCallback.onFailure(t);
            }
        });
    }

    private interface QuizApi {
        @GET("/api.php")
        Call<QuestionResponse> getQuestion(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<TriviaCategories> getTriviaCategories();

        @GET("api_count_global.php")
        Call<GlobalResponse> getGlobal();

        @GET("api_count.php?category")
        Call<QuizQuestionCount> getQuestionCount(
                @Query("category") Integer category);

    }
}
