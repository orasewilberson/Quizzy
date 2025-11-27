package com.example.quizzy.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizzy.data.model.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

    @Insert
    void insertAll(List<Question> questions);

    @Query("SELECT COUNT(*) FROM questions")
    int getCount();


    @Query("SELECT * FROM questions WHERE category = :category")
    LiveData<List<Question>> getQuestionsByCategory(String category);
}
