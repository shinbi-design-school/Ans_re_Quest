package com.design_shinbi.Ans_re_Quest.model;

import java.util.List;

import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.QuizEntity;

public class Battle {
    private List<QuizEntity> quizEntities;
    private PlayerEntity player;
    private EnemyEntity enemy;
    private int currentQuizIndex;
    private int totalQuizCount;
	private int currentEnemyIndex;
	private int currentFloor;
	
    public Battle(PlayerEntity player,EnemyEntity enemy,List<QuizEntity> quizEntities) {
        // 初期化などのコード
    	
    	
        this.player = player;
        this.enemy = enemy;
        this.quizEntities = quizEntities;
    }

    public void startBattle() {
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
                
                int currentHP = enemy.getHp();
                enemy.setHp(currentHP - 10);

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
        return enemy.getName();
    }
    
    public int getEnemyHP() {
        return enemy.getHp();
    }
    
    public int getEnemyMaxHP() {
        return enemy.getMaxHp();
    }

    public boolean isPlayerAlive() {
        return player.getHp() > 0;
    }

    public boolean isEnemyAlive() {
        return enemy.getHp() > 0;
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
    // GetterとSetterなど、必要なメソッドを追加する

	public void startNextBattle(EnemyEntity currentEnemy) {
        // 次の戦闘の初期化処理を行う
        enemy = currentEnemy;
        currentQuizIndex = 0;
        player.setHp(player.getMaxHp());

	}
	public void resetBattle(EnemyEntity firstEnemy) {
	    // 最初の戦闘の初期化処理を行う
		enemy = firstEnemy;
	    currentQuizIndex = 0;
	    player.setHp(player.getMaxHp()); // プレイヤーのHPをリセット
	    
	    // その他の初期化処理を実装する
	    // 例: 最初の敵の設定、初期問題の設定など
	}

	public void resetEnemyHP() {
		enemy.setHp(enemy.getMaxHp());
	}
}
