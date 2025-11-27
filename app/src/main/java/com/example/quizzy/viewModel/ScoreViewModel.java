package com.example.quizzy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizzy.data.model.Score;
import com.example.quizzy.repository.QuizRepository;

import java.util.List;

public class ScoreViewModel extends AndroidViewModel {
    private QuizRepository quizRepository;

    public ScoreViewModel(@NonNull Application application) {
        super(application);
        quizRepository = new QuizRepository(application);
    }

    public void insert(Score score) {
        quizRepository.insert(score);
    }

    public void delete(Score score) {
        quizRepository.delete(score);
    }

    public LiveData<List<Score>> getAllScoresUser(String playerName) {
        return quizRepository.getAllScoresUser(playerName);
    }

    public LiveData<List<Score>> getAllScores() {
        return quizRepository.getAllScores();
    }
}
