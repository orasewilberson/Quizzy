package com.example.quizzy.viewModel;



import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class QuizViewModelFactory implements ViewModelProvider.Factory {
    private final String category;
    private final Application application;


    public QuizViewModelFactory(Application application, String category) {
        this.category = category;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(QuizViewModel.class)) {
            return (T) new QuizViewModel(application, category);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
