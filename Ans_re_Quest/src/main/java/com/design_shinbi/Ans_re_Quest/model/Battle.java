package com.design_shinbi.Ans_re_Quest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.QuizEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.TowerEntity;

public class Battle {
	private TowerEntity tower;
    private List<QuizEntity> quizEntities;
    private PlayerEntity player;
	private List<EnemyEntity> enemies;
    private EnemyEntity currentEnemy;
    
    private int currentQuizIndex;
    private int totalQuizCount;
	private int currentEnemyIndex;
	private int currentFloor;
	
	
    public Battle(TowerEntity tower, PlayerEntity player,List<EnemyEntity> enemies,List<QuizEntity> quizEntities) {
        // 初期化などのコード
    	
    	this.tower = tower;
        this.player = player;
        this.enemies = enemies;
        this.currentEnemy = enemies.get(currentEnemyIndex);
        this.quizEntities = quizEntities;
    }
    
	public void startBattle() {
    	currentQuizIndex = 0;
    	currentEnemyIndex = 0;
    	currentFloor = 1;
    	totalQuizCount = 1;
    	shuffleQuizEntities();
        // バトルの開始処理
        // 最初の問題を設定するなど
    }
	
    public void answerQuiz(String choice) {
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
            if (currentQuizIndex >= 0 && currentQuizIndex < quizEntities.size()-1) {
            currentQuizIndex++;
            } else {
            	shuffleQuizEntities();
            	currentQuizIndex = 0;
            }
        }
    }
    
	public void startNextBattle(EnemyEntity currentEnemy) {
        // 次の戦闘の初期化処理を行う
        this.currentEnemy = currentEnemy;
        player.setHp(player.getMaxHp());
        resetEnemyHP();
	}
	public void resetBattle(EnemyEntity firstEnemy) {
	    // 最初の戦闘の初期化処理を行う
		currentEnemy = firstEnemy;
	    currentQuizIndex = 0;
	    currentFloor = 1;
	    player.setHp(player.getMaxHp()); // プレイヤーのHPをリセット
	    currentEnemy.setHp(currentEnemy.getMaxHp());
	    
	    //階層リセット
	    // その他の初期化処理を実装する
	    // 例: 最初の敵の設定、初期問題の設定など
	}
	public void resetEnemyHP() {
		currentEnemy.setHp(currentEnemy.getMaxHp());
	}
	
	public void handleBattleResult(){
	    // バトルの結果に応じた処理を実装する

	    
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
	                startNextBattle(currentEnemy);//ホーム実装合修正
	            }
	        }

	        if (!isPlayerAlive()) {
	            // プレイヤーが敗北した場合の処理
	            // 最初の戦闘に戻るために初期化を行う
	            setCurrentFloor(1);
	            EnemyEntity firstEnemy = enemies.get(0);
	            resetBattle(firstEnemy);
	        }
	}
	
	public void usedSKIP() {
		currentQuizIndex++;
	}
	
	public List<String> adapt5050() {
		QuizEntity currentQuestion = getCurrentQuestion();
		
		List<String> choices = new ArrayList<>();
		String correctAnswer = currentQuestion.getCorrectAnswer();
		choices.add(correctAnswer);
		
	    List<String> otherChoices = currentQuestion.getOtherChoices();

	    Random random = new Random();
	    while (choices.size() < 2) {
	        int randomIndex = random.nextInt(otherChoices.size());
	        String choice = otherChoices.get(randomIndex);
	        if (!choices.contains(choice)) {
	            choices.add(choice);
	        }
	    }
	    choices.add("×");
	    choices.add("×");
		return choices;
	}

	public void shuffleChoices() {
		QuizEntity currentQuestion = getCurrentQuestion();
		List<String> choices = new ArrayList<>();
		choices.add(currentQuestion.getChoice1());
		choices.add(currentQuestion.getChoice2());
		choices.add(currentQuestion.getChoice3());
		choices.add(currentQuestion.getChoice4());
		Collections.shuffle(choices);

		currentQuestion.setChoice1(choices.get(0));
		currentQuestion.setChoice2(choices.get(1));
		currentQuestion.setChoice3(choices.get(2));
		currentQuestion.setChoice4(choices.get(3));
	}
	
	public void shuffleQuizEntities() {
	   Collections.shuffle(quizEntities);
	}

	public boolean isEventFlore() {
			System.out.println("C=:"+currentFloor);
			System.out.println("E=:"+tower.getEventFlore());
			if(Integer.valueOf(currentFloor).equals(Integer.valueOf(tower.getEventFlore()))	){
			return true;
		} else {
		return false;
		}
	}
	
    public EnemyEntity getCurrentEnemy() {
		return currentEnemy;
	}

	public void setCurrentEnemy(EnemyEntity currentEnemy) {
		this.currentEnemy = currentEnemy;
	}

	public Object getResult() {
    	return null;
    }

	public String getTowerName() {
		String towerName = tower.getName();
		return towerName;
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
            QuizEntity currentQuestion = quizEntities.get(currentQuizIndex);
            
            return currentQuestion;
        } else {
        	currentQuizIndex = 0;
        	QuizEntity currentQuestion = quizEntities.get(currentQuizIndex);
        	 return currentQuestion;
        }
       
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







}