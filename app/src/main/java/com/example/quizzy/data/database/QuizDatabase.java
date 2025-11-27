package com.example.quizzy.data.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quizzy.data.dao.QuestionDao;
import com.example.quizzy.data.dao.ScoreDao;
import com.example.quizzy.data.model.Question;
import com.example.quizzy.data.model.Score;

@Database(entities = {Question.class, Score.class}, version = 2, exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {
    private static volatile QuizDatabase instance;
    public abstract QuestionDao questionDao();
    public abstract ScoreDao scoreDao();
    //creation database
    public static synchronized QuizDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (QuizDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    QuizDatabase.class, "quizzy_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return instance;
    }
}
