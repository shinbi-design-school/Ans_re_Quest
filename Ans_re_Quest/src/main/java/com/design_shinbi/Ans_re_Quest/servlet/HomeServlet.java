package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.Ans_re_Quest.model.dao.ItemDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.PlayerDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.UserDAO;
import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.User;
import com.design_shinbi.Ans_re_Quest.util.DbUtil;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
    private Connection connection;
    private PlayerDAO playerDAO;
    private ItemDAO itemDAO;
	private User user = null;
	private PlayerEntity player;
	private List<ItemEntity> items;
	
    public void init() throws ServletException {
        try {
            // コネクションの初期化
            this.connection = DbUtil.connect();
            // DAOインスタンスの初期化
            this.playerDAO = new PlayerDAO(connection);
            this.itemDAO = new ItemDAO(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		
    	if((User)session.getAttribute("user") == null) {
    		System.out.println("UserDAOから");
    		UserDAO userDAO;
			try {
				userDAO = new UserDAO(connection);
				user = userDAO.getUserById(1);//<-アカウント作らない人用
				player = playerDAO.getPlayerById(user.getPlayer_id());
				if(player == null) {
					System.out.println("新規プレイヤー作成");
					player = new PlayerEntity(user.getId(), user.getName(), 30, 0, 100);
					playerDAO.addOrUpdatePlayer(player);
				}
				items = itemDAO.getAllItemsByPlayerId(player.getId());
				if(items == null || items.isEmpty()) {
					System.out.println("新規アイテム所持追加");
					itemDAO.addDefaultItemsByPlayer(player);
					items = itemDAO.getAllItemsByPlayerId(user.getPlayer_id());
				}
				System.out.println("全セッション上げ");
				session.setAttribute("user", user);
				session.setAttribute("player", player);
				session.setAttribute("items", items);

				
			} catch (NoSuchAlgorithmException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
    	} else {
    		try {
    		System.out.println("Userセッションスコープから");
    		user = (User)session.getAttribute("user");//<-セッションスコープから受け取り
			player = (PlayerEntity)session.getAttribute("player");
			items = (List<ItemEntity>) session.getAttribute("items");
			////
			if(player == null) {
				System.out.println("nodb 新規プレイヤー作成");
				player = new PlayerEntity(user.getId(), user.getName(), 30, 0, 100);
				playerDAO.addOrUpdatePlayer(player);
			}
			if(items == null) {
				System.out.println("nodb 新規アイテム追加");
				itemDAO.addDefaultItemsByPlayer(player);
				items = itemDAO.getAllItemsByPlayerId(user.getPlayer_id());
			}
			System.out.println("全セッション上げ");
			session.setAttribute("user", user);
			session.setAttribute("player", player);
			session.setAttribute("items", items);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
    	}
    	
		String jsp = null;
		jsp = "WEB-INF/jsp/home.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}

}
