package com.lawlett.quizapp.core;

public interface ICoreCallback<T> {
    void onSuccess(T result);
    void onFailure(Throwable t);
}
