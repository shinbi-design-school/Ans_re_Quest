package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.Ans_re_Quest.implementation.DataRetrieverImpl;
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
    private DataRetrieverImpl dataRetrieverImpl;
    
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
            this.dataRetrieverImpl = new DataRetrieverImpl();
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            throw new ServletException(e);
        }
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
	    
		// battle インスタンスが null の場合は初期化処理を再度実行
		  if (battle == null|| battle.getLoginTime() != (long)session.getAttribute("loginTime")) {
			  	System.out.println("battle作成処理");
	
		            try {
		            	User user = dataRetrieverImpl.retrieveUserPlayerItems(connection, session);
		            	PlayerEntity player = (PlayerEntity) session.getAttribute("player");
		            	List<ItemEntity> items = (List<ItemEntity>) session.getAttribute("items");
//		                int towerId = Integer.parseInt(request.getParameter("towerId"));//<-ホームのページパラメーターから取得 1~
//		                TowerEntity tower = towerDAO.getTowerById(towerId);//<-ホームのページパラメーターから取得
		                TowerEntity tower = towerDAO.getTowerById(1);//<-仮置き ↑	    			
		                List<QuizEntity> quizEntities = quizDAO.getQuestionsByGenre(tower.getGenre());//ジャンルは今現在はnormalのみ

		                List<EnemyEntity> enemies = enemyDAO.getAllEnemies();
		                
		                long loginTime;
		                Object loginTimeAttribute = session.getAttribute("loginTime");
		                if (loginTimeAttribute != null) {
		                    loginTime = (long) loginTimeAttribute;
		                } else {
		                    loginTime = System.currentTimeMillis();
		                    session.setAttribute("loginTime", loginTime);
		                }		                	
		                
		                battle = new Battle(tower, player, enemies, quizEntities, items, loginTime);
		                battle.startBattle();
		            } catch (SQLException | NoSuchAlgorithmException e) {
		                System.out.println(e);
		                throw new ServletException(e);
		            }
		        }
		//tower
		request.setAttribute("towerName", battle.getTowerName());
		request.setAttribute("currentFloor", battle.getCurrentFloor());
		//player
		request.setAttribute("Name", battle.getPlayer().getName());
		request.setAttribute("playerHP", battle.getPlayerHP());
		request.setAttribute("playerMaxHP", battle.getPlayerMaxHP());
		request.setAttribute("isPlayerAlive", battle.isPlayerAlive());
		//enemy
		request.setAttribute("enemyName", battle.getEnemyName());
		request.setAttribute("enemyHP", battle.getEnemyHP());
		request.setAttribute("enemyMaxHP", battle.getEnemyMaxHP());
		request.setAttribute("isEnemyAlive", battle.isEnemyAlive());
		
		byte[] imageData = battle.getCurrentEnemy().getEnemyImage();
		// 画像データをBase64エンコードする
		String encodedImage = Base64.getEncoder().encodeToString(imageData);
		request.setAttribute("enemyImage", encodedImage);
		
		//quiz
		request.setAttribute("currentQuizNo", battle.getCurrentQuizIndex()+1);
		request.setAttribute("totalQuizCount", battle.getTotalQuizCount());
		//question
		
		//混乱か
		if(Boolean.parseBoolean(request.getParameter("isConfused"))) {
		request.setAttribute("questionText", battle.maskString(battle.getCurrentQuestion().getText(), 0.5));
		} else {
		request.setAttribute("questionText", battle.getCurrentQuestion().getText());
		}
		
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
			request.setAttribute("collectAnswer", battle.getCurrentQuestion().getCorrectAnswer());
			request.setAttribute("isUsed5050", true);
		//通常表示
		} else {
			battle.shuffleChoices();
		request.setAttribute("choice1", battle.getCurrentQuestion().getChoice1());
		request.setAttribute("choice2", battle.getCurrentQuestion().getChoice2());
		request.setAttribute("choice3", battle.getCurrentQuestion().getChoice3());
		request.setAttribute("choice4", battle.getCurrentQuestion().getChoice4());
		request.setAttribute("collectAnswer", battle.getCurrentQuestion().getCorrectAnswer());
		request.setAttribute("isUsed5050", false);

		}
		
		//制限時間
		if (Boolean.parseBoolean(request.getParameter("isImpatient"))) {
			//焦り状態
			request.setAttribute("limitTime", battle.getCurrentQuestion().getLimitTime()-10);
		} else {
			request.setAttribute("limitTime", battle.getCurrentQuestion().getLimitTime()-5);
		}
		//hint
		request.setAttribute("aiAnswer", battle.getCurrentQuestion().getAi_answer());
		request.setAttribute("quantity5050", battle.get5050Quantity());
		request.setAttribute("quantitySkip", battle.getSkipQuantity());
		//hint使用禁止用
		if (Boolean.parseBoolean(request.getParameter("isNoHint"))) {
		request.setAttribute("isNoHint", true);
		}
		
		// Battle.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// フォームからの回答を取得
		request.setCharacterEncoding("UTF-8");
		System.out.println("isusedai"+request.getParameter("isUsedAi"));
		System.out.println("choice:"+request.getParameter("choice"));
		System.out.println("isUsed:"+request.getParameter("isUsed5050"));

		
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
			System.out.println("通常選択処理");
			String choice = request.getParameter("choice");
			System.out.println(choice);
			Boolean isUsedAi = Boolean.parseBoolean(request.getParameter("isUsedAi"));
			battle.answerQuiz(choice,isUsedAi);

			// どちらかのHPが尽きたか
			if (!battle.isPlayerAlive() || !battle.isEnemyAlive()) {
				System.out.println("result処理");
				request.setAttribute("isPlayerAlive", battle.isPlayerAlive());
				request.setAttribute("isEnemyAlive", battle.isEnemyAlive());
				
				battle.handleBattleResult();
				//イベントフロアか
				if (battle.isEventFlore()) {
					List<Integer> patterns = battle.arriveEventFloor();
					request.setAttribute("patterns", patterns);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/middleEvent.jsp");
					dispatcher.forward(request, response);
					//クリアか
				} else if(battle.isClearFlore()) {
					battle.cleared();
					try {
						//ここでデータベースに残りアイテムを記録
						System.out.println("塔クリアのためのセーブ処理1");
						playerDAO.updatePlayer(battle.getPlayer());
						System.out.println("塔クリアのためのセーブ処理2");
						itemDAO.updateItems(battle.getPlayer().getId(),battle.getItems());
						System.out.println("セッションセーブ");
						session.setAttribute("player", battle.getPlayer());
						session.setAttribute("items", battle.getItems());
						request.setAttribute("getMoney", battle.getTower().getTowerId()*100);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					battle = null;
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/clearEvent.jsp");
					dispatcher.forward(request, response);
				} else {
						System.out.println(battle.isPlayerAlive());
						//敗北処理
					if(!battle.isPlayerAlive()) {
						System.out.println("敗北時セーブ処理1");
						try {
							itemDAO.updateItems(battle.getPlayer().getId(),battle.getItems());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						System.out.println("敗北時処理２");
						battle.defeated();//特にペナルティーの予定なし。
						session.setAttribute("player", battle.getPlayer());
						session.setAttribute("items", battle.getItems());
						battle = null;
				        }
						//通常勝利
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				//戦闘続き
				doGet(request, response);
			}
		}
	}
}
	