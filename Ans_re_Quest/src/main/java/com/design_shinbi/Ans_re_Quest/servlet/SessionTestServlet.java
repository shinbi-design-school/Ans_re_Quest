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

import com.design_shinbi.Ans_re_Quest.model.Battle;
import com.design_shinbi.Ans_re_Quest.model.dao.EnemyDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.ItemDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.PlayerDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.QuizDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.TowerDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.UserDAO;
import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.QuizEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.TowerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.User;
import com.design_shinbi.Ans_re_Quest.util.DbUtil;

/**
 * Servlet implementation class SessionTestServlet
 */
@WebServlet("/SessionTestServlet")
public class SessionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	private Battle battle;
	private List<EnemyEntity> enemies;
	private List<ItemEntity> items;
	private PlayerEntity player;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection connection = DbUtil.connect();
			HttpSession session = request.getSession();
			UserDAO userDAO = new UserDAO(connection);
			
			User user = userDAO.getUserById(1);//<-仮置き
			session.setAttribute("user", user);//<-仮置き
			user = (User)session.getAttribute("user");
			
			QuizDAO quizDAO = new QuizDAO(connection);
			List<QuizEntity> quizEntities = quizDAO.getAllQuestions();
		
			TowerDAO towerDAO = new TowerDAO(connection);
			TowerEntity tower = towerDAO.getTowerById(1);//<-メインページから取得orセッションスコープ
			
			PlayerDAO playerDAO = new PlayerDAO(connection);
			player = playerDAO.getPlayerById(1);//<-セッションスコープ;userテーブルにプレイヤーid追加

			EnemyDAO enemyDAO = new EnemyDAO(connection);
			enemies = enemyDAO.getAllEnemies();
			
			ItemDAO itemDAO = new ItemDAO(connection); 
			items = itemDAO.getAllItemsByPlayerId(1); //<-セッションスコープ;user
			
//			battle = new Battle(tower, player, enemies, quizEntities, items);//後でうつす
			battle = null;
			//battle.startBattle();//後でうつす
			
		} catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
		    System.out.println(e);
		    throw new ServletException(e);
		}
		
        HttpSession session = request.getSession();
        session.setAttribute("player", player);
        session.setAttribute("items", items);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sessionTest.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("choice"));
		System.out.println(request.getParameter("choice"));
		doGet(request, response);
	}

}
