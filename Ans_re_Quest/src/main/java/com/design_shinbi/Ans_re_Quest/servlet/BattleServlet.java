package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.design_shinbi.Ans_re_Quest.model.Battle;
import com.design_shinbi.Ans_re_Quest.model.dao.EnemyDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.PlayerDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.QuizDAO;
import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.QuizEntity;
import com.design_shinbi.Ans_re_Quest.util.DbUtil;

/**
 * Servlet implementation class Battle
 */
@WebServlet("/battle")
public class BattleServlet extends HttpServlet {
	private Battle battle;
	private List<EnemyEntity> enemies;
	//上2つはいつかBattle.java
	//home git
	//note git
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Connection connection = DbUtil.connect();
			QuizDAO quizDAO = new QuizDAO(connection);
			List<QuizEntity> quizEntities = quizDAO.getAllQuestions();
		
			PlayerDAO playerDAO = new PlayerDAO(connection);
			PlayerEntity player = playerDAO.getPlayerById(1);//<-Login()

			EnemyDAO enemyDAO = new EnemyDAO(connection);
			enemies = enemyDAO.getAllEnemies();
			
			
			battle = new Battle(player, enemies, quizEntities);
			battle.startBattle();
			
		} catch (SQLException | ClassNotFoundException e) {
		    System.out.println(e);
		    throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ビューに表示するデータを設定
		request.setAttribute("questionText", battle.getCurrentQuestion().getText());
		battle.shuffleChoices();
		request.setAttribute("choice1", battle.getCurrentQuestion().getChoice1());
		request.setAttribute("choice2", battle.getCurrentQuestion().getChoice2());
		request.setAttribute("choice3", battle.getCurrentQuestion().getChoice3());
		request.setAttribute("choice4", battle.getCurrentQuestion().getChoice4());

		request.setAttribute("currentQuizNo", battle.getCurrentQuizIndex()+1);
		request.setAttribute("totalQuizCount", battle.getTotalQuizCount());

		request.setAttribute("playerHP", battle.getPlayerHP());
		request.setAttribute("playerMaxHP", battle.getPlayerMaxHP());
		
		request.setAttribute("enemyName", battle.getEnemyName());
		request.setAttribute("enemyHP", battle.getEnemyHP());
		request.setAttribute("enemyMaxHP", battle.getEnemyMaxHP());
		
		request.setAttribute("towerName", "衒学の塔");
		
		request.setAttribute("currentFloor", battle.getCurrentFloor());

		request.setAttribute("isPlayerAlive", battle.isPlayerAlive());
		request.setAttribute("isEnemyAlive", battle.isEnemyAlive());


		// Battle.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // フォームからの回答を取得
	    request.setCharacterEncoding("UTF-8");
	    String choice = request.getParameter("choice");
	    battle.answerQuizEntity(choice);

	    // バトルの結果に応じてリダイレクト
	    if (!battle.isPlayerAlive() || !battle.isEnemyAlive()) {
	    	
	    	battle.handleBattleResult(request, response);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
	    	dispatcher.forward(request, response);
	    } else {
	    	doGet(request, response);
	   }
	}
}
