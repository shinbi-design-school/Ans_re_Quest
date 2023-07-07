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
 * Servlet implementation class Battle
 */
@WebServlet("/battle")
public class BattleServlet extends HttpServlet {
	private Battle battle;
	
    private Connection connection;
    private UserDAO userDAO;
    private TowerDAO towerDAO;
    private QuizDAO quizDAO;
    private PlayerDAO playerDAO;
    private EnemyDAO enemyDAO;
    private ItemDAO itemDAO;
    
    public void init() throws ServletException {
        try {
            // コネクションの初期化
            this.connection = DbUtil.connect();
            // DAOインスタンスの初期化
            this.userDAO = new UserDAO(connection);
            this.towerDAO = new TowerDAO(connection);
            this.quizDAO = new QuizDAO(connection);
            this.playerDAO = new PlayerDAO(connection);
            this.enemyDAO = new EnemyDAO(connection);
            this.itemDAO = new ItemDAO(connection);
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            throw new ServletException(e);
        }
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// battle インスタンスが null の場合は初期化処理を再度実行
		  if (battle == null) {
		        HttpSession session = request.getSession();
		        PlayerEntity player = (PlayerEntity) session.getAttribute("player");
		        List<ItemEntity> items = (List<ItemEntity>) session.getAttribute("items");
	
//		        if (player != null && items != null) {
		            try {
		    			User user = userDAO.getUserById(1);//<-仮置き実際はログイン時にセッションスコープにあげる
		    			session.setAttribute("user", user);//<-仮置きテスト用
		    			user = (User)session.getAttribute("user");//<-セッションスコープから受け取り
		    			
//		                int towerId = Integer.parseInt(request.getParameter("towerId"));//<-★★★★★ホームのページパラメーターから取得★★★★★
//		                TowerEntity tower = towerDAO.getTowerById(towerId);//<-★★★★★ホームのページパラメーターから取得★★★★★
		                TowerEntity tower = towerDAO.getTowerById(1);//<-仮置き ↑	    			
		                List<QuizEntity> quizEntities = quizDAO.getQuestionsByGenre(tower.getGenre());//ジャンルは今normalのみ

		                player = playerDAO.getPlayerById(user.getPlayer_id());//<-仮置き、ログイン時playerをセッションスコープに載せたら不要

		                List<EnemyEntity> enemies = enemyDAO.getAllEnemies();

		                items = itemDAO.getAllItemsByPlayerId(user.getPlayer_id());//<-仮置き、ログイン時itemsをセッションスコープに載せたら不要 

		                battle = new Battle(tower, player, enemies, quizEntities, items);
		                battle.startBattle();

		            } catch (SQLException e) {
		                System.out.println(e);
		                throw new ServletException(e);
		            }
		        }
		//tower
		request.setAttribute("towerName", battle.getTowerName());
		request.setAttribute("currentFloor", battle.getCurrentFloor());
		//player
		request.setAttribute("playerHP", battle.getPlayerHP());
		request.setAttribute("playerMaxHP", battle.getPlayerMaxHP());
		request.setAttribute("isPlayerAlive", battle.isPlayerAlive());
		//enemy
		request.setAttribute("enemyName", battle.getEnemyName());
		request.setAttribute("enemyHP", battle.getEnemyHP());
		request.setAttribute("enemyMaxHP", battle.getEnemyMaxHP());
		request.setAttribute("isEnemyAlive", battle.isEnemyAlive());
		//quiz
		request.setAttribute("currentQuizNo", battle.getCurrentQuizIndex()+1);
		request.setAttribute("totalQuizCount", battle.getTotalQuizCount());
		//question
		request.setAttribute("questionText", battle.getCurrentQuestion().getText());
		//SKIP使用時の表示情報
		if(Boolean.parseBoolean(request.getParameter("isUsedSkip"))) {
			request.setAttribute("isUsedSkip", true);
		} else {
			request.setAttribute("isUsedSkip", false);
		}
		
		//5050使用時の表示情報
		if (Boolean.parseBoolean(request.getParameter("isUsed5050"))) {
			List<String> choices = battle.adapt5050();
			request.setAttribute("choice1", choices.get(0));
			request.setAttribute("choice2", choices.get(1));
			request.setAttribute("choice3", choices.get(2));
			request.setAttribute("choice4", choices.get(3));
			request.setAttribute("isUsed5050", true);
		//通常表示
		} else {
			battle.shuffleChoices();
		request.setAttribute("choice1", battle.getCurrentQuestion().getChoice1());
		request.setAttribute("choice2", battle.getCurrentQuestion().getChoice2());
		request.setAttribute("choice3", battle.getCurrentQuestion().getChoice3());
		request.setAttribute("choice4", battle.getCurrentQuestion().getChoice4());
		}
		request.setAttribute("limitTime", battle.getCurrentQuestion().getLimitTime());

		//hint
		request.setAttribute("isUsed5050", false);
		request.setAttribute("aiAnswer", battle.getCurrentQuestion().getAi_answer());
		request.setAttribute("5050Quantity", battle.get5050Quantity());
		request.setAttribute("skipQuantity", battle.getSkipQuantity());

		// Battle.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームからの回答を取得
		
		request.setCharacterEncoding("UTF-8");
		
		//SKIP使ったら
		if (Boolean.parseBoolean(request.getParameter("isUsedSkip"))) {
			battle.usedSKIP();
			doGet(request, response);
		//5050使ったら
		} else if (Boolean.parseBoolean(request.getParameter("isUsed5050"))) {
			battle.used5050();
			doGet(request, response);
		//通常選択処理
		} else {
			String choice = request.getParameter("choice");
			battle.answerQuiz(choice);

			// どちらかのHPが尽きたか
			if (!battle.isPlayerAlive() || !battle.isEnemyAlive()) {
				request.setAttribute("isPlayerAlive", battle.isPlayerAlive());
				request.setAttribute("isEnemyAlive", battle.isEnemyAlive());
				
				battle.handleBattleResult();
				//イベントフロアか
				if (battle.isEventFlore()) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/middleEvent.jsp");
					dispatcher.forward(request, response);
					//クリアか
				} else if(battle.isClearFlore()) {
					battle.cleared();
					try {
						System.out.println("塔クリアのためのセーブ処理1");
						playerDAO.updatePlayer(battle.getPlayer());
						System.out.println("塔クリアのためのセーブ処理2");
						itemDAO.updateItems(battle.getPlayer().getId(),battle.getItems());
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					battle = null;
					//ここでデータベースに残りアイテムを記録
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/clearEvent.jsp");
					dispatcher.forward(request, response);
					//負けたか
				} else {
					if(!battle.isPlayerAlive()) {
						try {
							System.out.println("敗北時セーブ処理1");
							itemDAO.updateItems(battle.getPlayer().getId(),battle.getItems());
						} catch (SQLException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
						battle.defeated();//特にペナルティーの予定なし。
						battle = null;
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				doGet(request, response);
			}
		}
	}
}
