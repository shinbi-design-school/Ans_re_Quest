package com.design_shinbi.Ans_re_Quest.model.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;

public class EnemyDAO {
    private Connection connection;

    public EnemyDAO(Connection connection) {
        this.connection = connection;
    }

    public EnemyEntity getEnemyById(int enemyId) throws SQLException {
        EnemyEntity enemy = null;

        String query = "SELECT * FROM enemies WHERE enemy_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, enemyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("enemy_id");
                    String name = resultSet.getString("name");
                    int hp = resultSet.getInt("hp");
                    String genre = resultSet.getString("genre");
                    String difficulty = resultSet.getString("difficulty");
                    Blob imageBlob = resultSet.getBlob("image");
                    byte[] enemyImage = imageBlob.getBytes(1, (int) imageBlob.length());
                    
                    enemy = new EnemyEntity(id,name,hp,genre,difficulty,enemyImage);
                }
            }
        }

        return enemy;
    }
    public List<EnemyEntity> getAllEnemies() throws SQLException {
        List<EnemyEntity> enemies = new ArrayList<>();

        String query = "SELECT * FROM enemies";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("enemy_id");
                String name = resultSet.getString("name");
                int hp = resultSet.getInt("hp");
                String genre = resultSet.getString("genre");
                String difficulty = resultSet.getString("difficulty");
                Blob imageBlob = resultSet.getBlob("image");
                byte[] enemyImage = imageBlob.getBytes(1, (int) imageBlob.length());

                EnemyEntity enemy = new EnemyEntity(id,name,hp,genre,difficulty,enemyImage);
                enemies.add(enemy);
            }
        }

        return enemies;
    }
    // その他、必要なメソッドを追加する（例: 敵の追加、更新、削除など）
}