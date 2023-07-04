package com.design_shinbi.Ans_re_Quest.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.QuizEntity;

public class Battle {
    private List<QuizEntity> quizEntities;
    private PlayerEntity player;
	private List<EnemyEntity> enemies;
    private EnemyEntity currentEnemy;
    
    private int currentQuizIndex;
    private int totalQuizCount;
	private int currentEnemyIndex;
	private int currentFloor;
	
    public Battle(PlayerEntity player,List<EnemyEntity> enemies,List<QuizEntity> quizEntities) {
        // 初期化などのコード
    	
    	
        this.player = player;
        this.enemies = enemies;
        this.currentEnemy = enemies.get(0);
        this.quizEntities = quizEntities;
    }

    public EnemyEntity getCurrentEnemy() {
		return currentEnemy;
	}

	public void setCurrentEnemy(EnemyEntity currentEnemy) {
		this.currentEnemy = currentEnemy;
	}

	public void startBattle() {
    	currentQuizIndex = 0;
    	currentEnemyIndex = 0;
    	currentFloor = 1;
    	totalQuizCount = 1;
        // バトルの開始処理
        // 最初の問題を設定するなど
    }

    public void answerQuizEntity(String choice) {
        // 問題に対する回答処理
        QuizEntity currentQuestion = getCurrentQuestion();
        if (currentQuestion != null) {
            if (currentQuestion.getCorrectAnswer().equals(choice)) {
                // 正解の場合はスコアを加算などの処理を行う
                // 今回はスコアを加算する処理は省略
                System.out.println("正解です！");
                
                int currentHP = currentEnemy.getHp();
                currentEnemy.setHp(currentHP - 10);

            } else {
                System.out.println("不正解です...");

                // 不正解の場合はプレイヤーのHPを減少させる処理を行う
                int currentHP = player.getHp();
                player.setHp(currentHP - 10); // 仮の減少値
            }
            // 次の問題へ進む
            currentQuizIndex++;
        }
    }





	public Object getResult() {
    	return null;
    }

    public int getPlayerHP() {
        return player.getHp();
    }
    
    public int getPlayerMaxHP() {
        return player.getMaxHp();
    }

    public String getEnemyName() {
        return currentEnemy.getName();
    }
    
    public int getEnemyHP() {
        return currentEnemy.getHp();
    }
    
    public int getEnemyMaxHP() {
        return currentEnemy.getMaxHp();
    }

    public boolean isPlayerAlive() {
        return player.getHp() > 0;
    }

    public boolean isEnemyAlive() {
        return currentEnemy.getHp() > 0;
    }
    
	public int getCurrentQuizIndex() {
		return currentQuizIndex;
	}
	
    public void setCurrentQuizIndex(int currentQuizIndex) {
		this.currentQuizIndex = currentQuizIndex;
	}
    
    public int getTotalQuizCount() {
    	return quizEntities.size();
    }


    public QuizEntity getCurrentQuestion() {
        if (currentQuizIndex >= 0 && currentQuizIndex < quizEntities.size()) {
            return quizEntities.get(currentQuizIndex);
        }
        return null;
    }
    
	public int getCurrentEnemyIndex() {
		return currentEnemyIndex;
	}

	public void setCurrentEnemyIndex(int currentEnemyIndex) {
		this.currentEnemyIndex = currentEnemyIndex;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
    // GetterとSetterなど、必要なメソッドを追加する

	public void startNextBattle(EnemyEntity currentEnemy) {
        // 次の戦闘の初期化処理を行う
        currentEnemy = currentEnemy;
        currentQuizIndex = 0;
        player.setHp(player.getMaxHp());
        resetEnemyHP();

	}
	public void resetBattle(EnemyEntity firstEnemy) {
	    // 最初の戦闘の初期化処理を行う
		currentEnemy = firstEnemy;
	    currentQuizIndex = 0;
	    currentFloor = 1;
	    player.setHp(player.getMaxHp()); // プレイヤーのHPをリセット
	    
	    
	    //階層リセット
	    // その他の初期化処理を実装する
	    // 例: 最初の敵の設定、初期問題の設定など
	}

	public void resetEnemyHP() {
		currentEnemy.setHp(currentEnemy.getMaxHp());
	}
	
	public void handleBattleResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // バトルの結果に応じた処理を実装する
	    if (!isPlayerAlive() || !isEnemyAlive()) {
	        request.setAttribute("isPlayerAlive", isPlayerAlive());
	        request.setAttribute("isEnemyAlive", isEnemyAlive());
	        request.setAttribute("currentQuizIndex", getCurrentQuizIndex());
	        if (!isEnemyAlive()) {
	            // 敵が倒された場合の処理
	            // 次の戦闘の初期化を行う（例: startNextBattle()）
	            setCurrentFloor(getCurrentFloor() + 1);
	            setCurrentEnemyIndex(getCurrentEnemyIndex() + 1);
	            if (getCurrentEnemyIndex() < enemies.size()) {
	                EnemyEntity currentEnemy = enemies.get(getCurrentEnemyIndex());
	                startNextBattle(currentEnemy);
	            } else {
	                setCurrentEnemyIndex(0);
	                EnemyEntity currentEnemy = enemies.get(getCurrentEnemyIndex());
	                startNextBattle(currentEnemy);
	            }
	        }

	        if (!isPlayerAlive()) {
	            // プレイヤーが敗北した場合の処理
	            // 最初の戦闘に戻るために初期化を行う
	            setCurrentFloor(1);
	            EnemyEntity firstEnemy = enemies.get(0);
	            resetBattle(firstEnemy);
	        }

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
	        dispatcher.forward(request, response);
	    }
	}

}

