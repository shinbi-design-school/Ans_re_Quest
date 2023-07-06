package com.design_shinbi.Ans_re_Quest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;

public class ItemDAO {
	private Connection connection;

	public ItemDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ItemEntity getItemById(int itemId) throws SQLException {
		ItemEntity item = null;
		
	     String query1 = 
	    		 //SELECT items.*, player_items.quantity FROM items LEFT JOIN player_items ON items.item_id = player_items.item_id WHERE items.item_id = 1;
	    		 "SELECT items.*, player_items.quantity "
	    		 +"FROM items " 
	    		 +"LEFT JOIN player_items " 
	    		 +"ON items.item_id = player_items.item_id " 
	    		 +"WHERE items.item_id = ?";

	    		 
	        try (PreparedStatement statement = connection.prepareStatement(query1)) {
	            statement.setInt(1, itemId);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int id = resultSet.getInt("item_id");
	                    String name = resultSet.getString("name");
	                    String effect = resultSet.getString("effect");
	                    int quantity = resultSet.getInt("quantity");
	                    
	                    item = new ItemEntity(id,name,effect,quantity);
	                }
	            }
	        }
			return item;
	}
	
	public List<ItemEntity> getAllItemsByPlayerId(int playerId) throws SQLException{
		List<ItemEntity> items = new ArrayList<>();
		//SELECT items.*, player_items.quantity FROM items LEFT JOIN player_items ON items.item_id = player_items.item_id WHERE player_items.player_id = 1;
	     String query1 = 
	    		 "SELECT items.*, player_items.quantity "
	    				 +"FROM items "
	    				 +"LEFT JOIN player_items ON items.item_id = player_items.item_id "
	    				 +"WHERE player_items.player_id = ?";
	     try (PreparedStatement statement = connection.prepareStatement(query1)) {
	         statement.setInt(1, playerId);
	         
	         try (ResultSet resultSet = statement.executeQuery()) {
	             while (resultSet.next()) {
	                 int id = resultSet.getInt("item_id");
	                 String name = resultSet.getString("name");
	                 String effect = resultSet.getString("effect");
	                 int quantity = resultSet.getInt("quantity");
	                 
	                 ItemEntity item = new ItemEntity(id, name, effect, quantity);
	                 items.add(item);
	             }
	         }
	     }
	     
	     return items;
	 }
	
}
