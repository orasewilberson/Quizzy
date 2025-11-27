package com.example.quizzy.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "question_text")
    private String questionText;

    @ColumnInfo(name = "option_a")
    private String optionA;

    @ColumnInfo(name = "option_b")
    private String optionB;

    @ColumnInfo(name = "option_c")
    private String optionC;

    @ColumnInfo(name = "correct_answer")
    private String correctAnswer;

    @ColumnInfo(name = "category")
    private String category;

    public Question(String questionText, String optionA, String optionB, String optionC, String correctAnswer, String category) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    // Récupérer toutes les réponses sous forme de liste
    public List<String> getAllAnswers() {
        List<String> allAnswers = new ArrayList<>();
        allAnswers.add(optionA);
        allAnswers.add(optionB);
        allAnswers.add(optionC);
        allAnswers.add(correctAnswer);
        return allAnswers;
    }

    // Mélanger les réponses aléatoirement
    public List<String> getShuffledAnswers() {
        List<String> shuffledAnswers = getAllAnswers();
        Collections.shuffle(shuffledAnswers);
        return shuffledAnswers;
    }
}
