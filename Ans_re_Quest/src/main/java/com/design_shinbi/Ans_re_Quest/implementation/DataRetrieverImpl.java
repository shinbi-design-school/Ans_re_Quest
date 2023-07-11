package com.design_shinbi.Ans_re_Quest.implementation;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.design_shinbi.Ans_re_Quest.interfaces.DataRetrieverInterface;
import com.design_shinbi.Ans_re_Quest.model.dao.ItemDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.PlayerDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.UserDAO;
import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.User;

public class DataRetrieverImpl implements DataRetrieverInterface {

	@Override
	public User retrieveUserPlayerItems(Connection connection, HttpSession session)
			throws SQLException, NoSuchAlgorithmException {
		UserDAO userDAO = new UserDAO(connection);
		PlayerDAO playerDAO = new PlayerDAO(connection);
		ItemDAO itemDAO = new ItemDAO(connection);

		User user = (User) session.getAttribute("user");
		System.out.println(user);
		if (user == null) {
			user = userDAO.getUserById(1);
			System.out.println("DAOユーザー");
		} else {
			System.out.println("セッションユーザー");
		}
		System.out.println("usertest"+user.getName());
		
		PlayerEntity player = (PlayerEntity) session.getAttribute("player");
		if (player == null) {
			player = playerDAO.getPlayerById(user.getId());
			
			if (player == null) {
				player = new PlayerEntity(user.getId(), user.getName(), 30, 0, 100);
				playerDAO.addOrUpdatePlayer(player);
				System.out.println("新規プレイヤー");
			} else {
				System.out.println("DAOプレイヤー");
			}
		} else {
			System.out.println("セッションプレイヤー");
		}
		System.out.println("名前テスト"+player.getName());
		
		List<ItemEntity> items = (List<ItemEntity>) session.getAttribute("items");
	    if(items == null || items.isEmpty()) {
	    	System.out.println("sessionItemsNull判定");
	    	items = itemDAO.getAllItemsByPlayerId(player.getId());
	    	if(items == null || items.isEmpty()) {
	    		System.out.println("DBItemsNull判定");
	    		itemDAO.addDefaultItemsByPlayer(player);
	    		items = itemDAO.getAllItemsByPlayerId(user.getPlayer_id());
	    		System.out.println("新規アイテム行作成");//
	    	} else {
	    		System.out.println("DAOItems有判定");
	    	}
	    } else {
	    	System.out.println("sessionItems有判定");
	    }
	    System.out.println("処理後のアイテム個数"+items.get(0).getQuantity());
		System.out.println("user,player,itemsセッション上げ");
		session.setAttribute("user", user);
		session.setAttribute("player", player);
		session.setAttribute("items", items);
            
		return user;
	}
}
