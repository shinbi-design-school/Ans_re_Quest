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
                String choice1 = resultSet.getString("choiceA");
                String choice2 = resultSet.getString("choiceB");
                String choice3 = resultSet.getString("choiceC");
                String choice4 = resultSet.getString("choiceD");
                String correctAnswer = resultSet.getString("correctAnswer");

                QuizEntity question = new QuizEntity(id, text, choice1, choice2, choice3, choice4, correctAnswer);
                questions.add(question);
            }
        }

        return questions;
    }

    // その他、必要なメソッドを追加する（例: 問題の追加、更新、削除など）
}
