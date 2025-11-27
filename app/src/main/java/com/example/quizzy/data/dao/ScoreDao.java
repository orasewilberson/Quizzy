package com.example.quizzy.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizzy.data.model.Score;

import java.util.List;

@Dao
public interface ScoreDao {
    @Insert
    void insert(Score score);

    @Delete
    void delete(Score score);

    @Query("SELECT * FROM scores where playerName= :playerName ORDER BY score DESC")
    LiveData<List<Score>> getAllScoresUser(String playerName);

    @Query("SELECT * FROM scores ORDER BY score DESC")
    LiveData<List<Score>> getAllScores();
}
