package com.design_shinbi.Ans_re_Quest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;

public class PlayerDAO {
    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PlayerEntity> getAllPlayers() throws SQLException {
        List<PlayerEntity> players = new ArrayList<>();

        String query = "SELECT * FROM players";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("player_id");
                String name = resultSet.getString("name");
                int hp = resultSet.getInt("hp");

                PlayerEntity player = new PlayerEntity(hp);
                players.add(player);
            }
        }

        return players;
    }
    public PlayerEntity getPlayerById(int playerId) throws SQLException {
        PlayerEntity player = null;

        String query = "SELECT * FROM players WHERE player_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("player_id");
                    String name = resultSet.getString("name");
                    int hp = resultSet.getInt("hp");

                    player = new PlayerEntity(hp);
                }
            }
        }

        return player;
    }
    // その他、必要なメソッドを追加する（例: プレイヤーの追加、更新、削除など）
}
