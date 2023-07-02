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
	private int currentEnemyIndex;
	private int currentFloor;
	//上2つはいつかBattle.java
	//home git

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Connection connection = DbUtil.connect();
			QuizDAO quizDAO = new QuizDAO(connection);
			PlayerDAO playerDAO = new PlayerDAO(connection);
			EnemyDAO enemyDAO = new EnemyDAO(connection);
			PlayerEntity player = playerDAO.getPlayerById(1);//<-Login()
			enemies = enemyDAO.getAllEnemies();
			List<QuizEntity> quizEntities = quizDAO.getAllQuestions();
			EnemyEntity currentEnemy = enemies.get(currentEnemyIndex);
			
			currentFloor = 1;
			currentEnemyIndex = 0;
			
			battle = new Battle(player, currentEnemy, quizEntities);
			battle.startBattle();
		} catch (SQLException | ClassNotFoundException e) {
			// エラーハンドリング
			throw new ServletException(e);
		}
	System.out.println("home"); //home git

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ビューに表示するデータを設定
		request.setAttribute("questionText", battle.getCurrentQuestion().getText());
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
		
		request.setAttribute("currentFloor", currentFloor);

		request.setAttribute("isPlayerAlive", battle.isPlayerAlive());
		request.setAttribute("isEnemyAlive", battle.isEnemyAlive());
		System.out.println("home"); //home git

		


		// Battle.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームからの回答を取得
		request.setCharacterEncoding("UTF-8");
		String choice = request.getParameter("choice");
		System.out.println(choice);//後で消す確認用出力
		System.out.println(battle.getCurrentQuestion().getCorrectAnswer());//後で消す確認用出力
		battle.answerQuizEntity(choice);

		// バトルの結果に応じてリダイレクト
		if (!battle.isPlayerAlive() || !battle.isEnemyAlive()) {
			request.setAttribute("isPlayerAlive", battle.isPlayerAlive());
			request.setAttribute("isEnemyAlive", battle.isEnemyAlive());
			request.setAttribute("currentQuizIndex", battle.getCurrentQuizIndex());
			if (!battle.isEnemyAlive()) {
				// 敵が倒された場合の処理
				// 次の戦闘の初期化を行う（例: battle.startNextBattle()）
				battle.resetEnemyHP();
				currentFloor++;
				currentEnemyIndex++;
				if (currentEnemyIndex < enemies.size()) {
					EnemyEntity currentEnemy = enemies.get(currentEnemyIndex);
					battle.startNextBattle(currentEnemy);
				} else {
					currentEnemyIndex = 0;
					EnemyEntity currentEnemy = enemies.get(currentEnemyIndex);
					battle.startNextBattle(currentEnemy);
				}	
				
			}
	        if (!battle.isPlayerAlive()) {
	            // プレイヤーが敗北した場合の処理
	            // 最初の戦闘に戻るために初期化を行う
	        	EnemyEntity firstEnemy = enemies.get(0);
	            battle.resetBattle(firstEnemy);
	        }



			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		} else {
			// doGet()を呼び出して再度Battle.jspにフォワード
			System.out.println("home"); //home git
			doGet(request, response);
		}
	}
}
