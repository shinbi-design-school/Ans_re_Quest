package com.design_shinbi.Ans_re_Quest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.design_shinbi.Ans_re_Quest.model.entity.TowerEntity;

public class TowerDAO {
    private Connection connection;

    public TowerDAO(Connection connection) {
        this.connection = connection;
    }

    public TowerEntity getTowerById(int towerId) throws SQLException {
        TowerEntity tower = null;

        String query = "SELECT * FROM enemies WHERE tower_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, towerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("tower_id");
                    String name = resultSet.getString("name");
                    int flore = resultSet.getInt("flore");
                    String genre = resultSet.getString("genre");
                    String difficulty = resultSet.getString("difficulty");

                    tower = new TowerEntity(towerId, name, flore, genre, difficulty);
                }
            }
        }

        return tower;
    }

    // その他、必要なメソッドを追加する（例: 敵の追加、更新、削除など）
}