package com.lawlett.quizapp.presentation.quiz;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lawlett.quizapp.App;
import com.lawlett.quizapp.data.model.Question;
import com.lawlett.quizapp.data.model.QuizResult;
import com.lawlett.quizapp.data.remote.IQuizApiClient;
import com.lawlett.quizapp.data.remote.QuizRepository;
import com.lawlett.quizapp.utils.SingleLiveEvent;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private QuizRepository quizRepository = App.quizRepository;
    Integer count;
    //todo
    List<Question> listQuestion;
    MutableLiveData<List<Question>> dataWithQuestion = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    public void init(int amount, Integer category, String difficulty) {
        isLoading.setValue(true);
    }

    void queryOnData(int amount, final Integer category, String difficulty) {
        App.quizRepository.getQuestion(amount, category, difficulty, new IQuizApiClient.QuestionCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                isLoading.setValue(false);
                listQuestion = result;
                dataWithQuestion.setValue(result);
                dataWithQuestion.postValue(listQuestion);
                //todo postValue
                currentQuestionPosition.setValue(0);


            }

            @Override
            public void onFailure(Throwable t) {
                isLoading.setValue(false);
            }
        });
    }

    void onFinishQuiz() {
        QuizResult quizResult = new QuizResult(
                0,
                listQuestion.get(0).getCategory(),
                listQuestion.get(0).getDifficulty(),
                dataWithQuestion.getValue(),
                getCorrectAnswersAmount(),
                new Date()
        );
        int resultId = quizRepository.saveQuizResult(quizResult);

        Log.e("olololo", "resultid" + resultId);
        openResultEvent.setValue(resultId);
        finishEvent.call();
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {
        if (listQuestion.size() > position && position >= 0) {
            listQuestion.get(position).setSelectedAnswersPosition(selectedAnswerPosition);
            listQuestion.get(position).setAnswered(true);
            dataWithQuestion.setValue(listQuestion);
            if (position + 1 == listQuestion.size()) {
                onFinishQuiz();

            } else {
                int seconds = 1;
                CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.e("olo", "onTick: ");
                    }

                    @Override
                    public void onFinish() {
                        currentQuestionPosition.setValue(++count);
                    }
                }.start();

            }
        }
    }

    void onSkip() {
        if (listQuestion.size() >= currentQuestionPosition.getValue()) {
            listQuestion.get(currentQuestionPosition.getValue()).setAnswered(true);
            dataWithQuestion.setValue(listQuestion);
            currentQuestionPosition.setValue(++count);
            if (currentQuestionPosition.getValue() + 1 == listQuestion.size()) {
                onFinishQuiz();
            }
        }
    }

    void onBackPress() {
        if (currentQuestionPosition.getValue() != 0) {
            currentQuestionPosition.setValue(--count);
        } else {
            finishEvent.call();
        }
    }

    private int getCorrectAnswersAmount() {
        int correctAnswersAmount = 0;
        for (int i = 0; i < listQuestion.size() - 1; i++) {
            if (listQuestion.get(i).getSelectedAnswersPosition() != null) {
                String correctAnswer = listQuestion.get(i).getCorrectAnswer();
                String selectedAnswer = listQuestion.get(i).getAnswers()
                        .get(listQuestion.get(i).getSelectedAnswersPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswersAmount++;
                }
            }
        }

        return correctAnswersAmount;
    }
}
