package com.example.quizzy.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizzy.data.model.Question;
import com.example.quizzy.repository.QuizRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    private final QuizRepository repository;
    private final MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLastQuestion = new MutableLiveData<Boolean>(false);
    MutableLiveData<Question> currentQuestion = new MutableLiveData<Question>();
    private Integer currentQuestionIndex = 0;

    private final String category;

    public QuizViewModel(@NonNull Application application, String category) {
        super(application);
        this.category = category;
        repository = new QuizRepository(application);
        loadQuestions(category);  // Charger les questions au lancement
    }

    private void loadQuestions(String category) {
        repository.getQuestionsByCategory(category).observeForever(questionList -> {
            if (questionList != null && !questionList.isEmpty()) {
                Collections.shuffle(questionList); // Mélange les questions
                questions.setValue(questionList);
                currentQuestion.postValue(questionList.get(0)); //Assigner la question
            } else {
                Log.e("QuizViewModel", "Aucune question trouvée pour cette catégorie !");
            }
        });
    }

    public LiveData<List<Question>> getQuestions() {
        return questions;
    }

    public LiveData<Question> getCurrentQuestion() {
        return currentQuestion;
    }

    public LiveData<Boolean> getIsLastQuestion() {
        return isLastQuestion;
    }

    public void nextQuestion() {
        List<Question> questionList = questions.getValue();

        if (questionList == null || questionList.isEmpty()) {
            Log.e("QuizViewModel", "Erreur : Liste de questions vide !");
            return;
        }

        int nextIndex = currentQuestionIndex + 1; // Avancer à la question suivante

        // Vérifier si c'est la dernière question avant de mettre à jour l'index
        if (nextIndex >= questionList.size()) {
            return;
        } else if (nextIndex == questionList.size() - 1) {
            isLastQuestion.postValue(true); // Indique qu'on est à la dernière question
        }

        // Sinon, mettre à jour l'index et afficher la question suivante
        currentQuestionIndex = nextIndex;
        currentQuestion.postValue(questionList.get(currentQuestionIndex)); // Mise à jour de la question actuelle

        Log.d("QuizViewModel", "Nouvelle question : " + questionList.get(currentQuestionIndex).getQuestionText());
    }



}
