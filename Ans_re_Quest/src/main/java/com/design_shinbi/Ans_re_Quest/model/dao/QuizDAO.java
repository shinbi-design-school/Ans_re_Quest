package com.design_shinbi.Ans_re_Quest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.Ans_re_Quest.model.entity.QuizEntity;

public class QuizDAO {
    private Connection connection;

    public QuizDAO(Connection connection) {
        this.connection = connection;
    }

    public List<QuizEntity> getAllQuestions() throws SQLException {
        List<QuizEntity> questions = new ArrayList<>();

        String query = "SELECT * FROM questions";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                String choice1 = resultSet.getString("choice1");
                String choice2 = resultSet.getString("choice2");
                String choice3 = resultSet.getString("choice3");
                String choice4 = resultSet.getString("choice4");
                String correctAnswer = resultSet.getString("correctAnswer");
                String aiAnswer = resultSet.getString("ai_answer");
                int limitTime = resultSet.getInt("limit_time");
                String genre = resultSet.getString("genre");
                String difficulty = resultSet.getString("difficulty");

                QuizEntity question = new QuizEntity(id, text, choice1, choice2, choice3, choice4, correctAnswer, aiAnswer, limitTime, genre,difficulty);
                questions.add(question);
            }
        }

        return questions;
    }
    public List<QuizEntity> getQuestionsByGenre(String genre) throws SQLException {
        List<QuizEntity> questions = new ArrayList<>();

        String query = "SELECT * FROM questions WHERE genre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();
        	
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                String choice1 = resultSet.getString("choice1");
                String choice2 = resultSet.getString("choice2");
                String choice3 = resultSet.getString("choice3");
                String choice4 = resultSet.getString("choice4");
                String correctAnswer = resultSet.getString("correctAnswer");
                String aiAnswer = resultSet.getString("ai_answer");
                int limitTime = resultSet.getInt("limit_time");
                String genreValue  = resultSet.getString("genre");
                String difficulty = resultSet.getString("difficulty");

                QuizEntity question = new QuizEntity(id, text, choice1, choice2, choice3, choice4, correctAnswer, aiAnswer, limitTime, genreValue ,difficulty);
                questions.add(question);
            }
        }

        return questions;
    }
    // その他、必要なメソッドを追加する（例: 問題の追加、更新、削除など）
}
