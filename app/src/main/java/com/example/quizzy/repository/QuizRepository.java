package com.example.quizzy.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.quizzy.data.dao.QuestionDao;
import com.example.quizzy.data.dao.ScoreDao;
import com.example.quizzy.data.database.QuizDatabase;
import com.example.quizzy.data.model.Question;
import com.example.quizzy.data.model.QuestionData;
import com.example.quizzy.data.model.Score;

import java.util.List;

public class QuizRepository {
    private QuestionDao questionDao;
    private ScoreDao scoreDao;

    public QuizRepository(Application application) {
        QuizDatabase quizDatabase = QuizDatabase.getInstance(application);
        questionDao = quizDatabase.questionDao();
        scoreDao = quizDatabase.scoreDao();
    }
    public void insertQuestions() {
        List<Question> questions = QuestionData.getQuestions();
        if (questionDao.getCount() !=0){
            return;
        }
        questionDao.insertAll(questions);
    }
    public LiveData<List<Question>> getQuestionsByCategory(String category) {
        return questionDao.getQuestionsByCategory(category);
    }

    public void insert(Score score) {
        scoreDao.insert(score);
    }

    public void delete(Score score) {
        scoreDao.delete(score);
    }

    public LiveData<List<Score>> getAllScoresUser(String playerName) {
        return scoreDao.getAllScoresUser(playerName);
    }
    public LiveData<List<Score>> getAllScores() {
       return scoreDao.getAllScores();
    }
}
