package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;
import java.sql.Connection;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		super.doGet(req, resp);
		String jsp = "WEB-INF/jsp/top.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Connection connection = DbUtil.connect();
			String jsp =  null;
			String error = "";
			User user = null;
			
			UserDAO userDAO = new UserDAO(connection);
			PlayerDAO playerDAO = new PlayerDAO(connection); 
			ItemDAO itemDAO = new ItemDAO(connection);
			
			String email = req.getParameter("email");
			if(email == null || email.isEmpty()) {
				error = error + "メールアドレスを入力してください";
			}
			
			String password = req.getParameter("password");
			if(password == null || password.isEmpty()) {
				error = error + "パスワードを入力してください。";
			}
			
			if(error.isEmpty()) {
				user = userDAO.login(email, password);
			}
			
			if(user == null) {
				if(error.isEmpty()) {
					error = "ユーザ名、もしくはパスワードが違います。";
				}
				req.setAttribute("error", error);
				jsp = "/WEB-INF/jsp/error.jsp";
			}else {
				HttpSession session = req.getSession();
				PlayerEntity player = playerDAO.getPlayerById(user.getPlayer_id());
				if(player == null) {
					System.out.println("新規プレイヤー作成");
					PlayerEntity newPlayer = new PlayerEntity(user.getId(), user.getName(), 30, 0, 100);
					playerDAO.addOrUpdatePlayer(newPlayer);
					player = playerDAO.getPlayerById(user.getPlayer_id());
				}
				List<ItemEntity> items = itemDAO.getAllItemsByPlayerId(player.getId());
				items = itemDAO.getAllItemsByPlayerId(player.getId());
				System.out.println(items);
				if(items == null || items.isEmpty()) {
					System.out.println("itemnull判定");
					itemDAO.addDefaultItemsByPlayer(player);
					items = itemDAO.getAllItemsByPlayerId(user.getPlayer_id());
					System.out.println("items作成");
				}
				session.setAttribute("user", user);
				session.setAttribute("player", player);
				session.setAttribute("items", itemDAO.getAllItemsByPlayerId(user.getPlayer_id()));
				System.out.println("ログイン後セッション上げ");
				jsp = "/WEB-INF/jsp/home.jsp";
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			throw new ServletException(e); 
		}
	
	}

}
