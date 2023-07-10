package com.design_shinbi.Ans_re_Quest.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerData;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.QuizEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.TowerEntity;

public class Battle {
	private TowerEntity tower;
    private List<QuizEntity> quizEntities;
    private PlayerEntity player;
	private List<EnemyEntity> enemies;
    private EnemyEntity currentEnemy;
    private List<ItemEntity> items;
    
    private int currentQuizIndex;
    private int totalQuizCount;
	private int currentEnemyIndex;
	private int currentFloor;
	
	private PlayerData playerdata;
	
	
    public Battle(TowerEntity tower, PlayerEntity player,List<EnemyEntity> enemies,List<QuizEntity> quizEntities,List<ItemEntity> items) {
        // 初期化などのコード
    	
    	this.tower = tower;
        this.player = player;
        this.enemies = enemies;
        this.currentEnemy = enemies.get(currentEnemyIndex);
        this.quizEntities = quizEntities;
        this.items = items;
    }
    
	public void startBattle() {
    	currentQuizIndex = 0;
    	currentEnemyIndex = 0;
    	currentFloor = 1;
    	totalQuizCount = 1;
    	shuffleQuizEntities();
    }
	
    public void answerQuiz(String choice) {
        QuizEntity currentQuestion = getCurrentQuestion();
        
        if (currentQuestion != null) {
            if (currentQuestion.getCorrectAnswer().equals(choice)) {
                handleCorrectAnswer();
            } else {
                handleWrongAnswer();
            }
            
            moveToNextQuestion();
        }
    }
    
    private void handleCorrectAnswer() {
        int currentHP = currentEnemy.getHp();
        currentEnemy.setHp(currentHP - 10);
    }
    
    private void handleWrongAnswer() {
        int currentHP = player.getHp();
        player.setHp(currentHP - 10);
    }
    
    private void moveToNextQuestion() {
        currentQuizIndex++;
        
        if (currentQuizIndex >= quizEntities.size()) {
            shuffleQuizEntities();
            currentQuizIndex = 0;
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
	    resetEnemyHP();
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
	                startNextBattle(currentEnemy);//ホーム実装後修正
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
	
	public void cleared() {
		player.setMoney(player.getMoney()+tower.getTowerId()*100);
		player.setAchieve(tower.getTowerId());
		setCurrentFloor(1);
        EnemyEntity firstEnemy = enemies.get(0);
        resetBattle(firstEnemy);
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
	    choices.add("×××");
	    choices.add("×××");
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
	//イベントフロア関連
	public boolean isEventFlore() {
			System.out.println("isEventFlore C=:"+currentFloor);
			System.out.println("isEventFlore E=:"+tower.getEventFlore());
			if(Integer.valueOf(currentFloor).equals(Integer.valueOf(tower.getEventFlore()+1))	){
			return true;
		} else {
		return false;
		}
	}
	
	public List<Integer> arriveEventFloor() {
		int pattern1;
		int pattern2;
        Random random = new Random();
        pattern1 = random.nextInt(3 - 1 + 1) + 1;
        pattern2 = random.nextInt(3- 1 + 1) + 1;
        List<Integer> patterns = new ArrayList<>(Arrays.asList(pattern1, pattern2));
        switch (pattern1) {
        case 1:
            // パターン1のイベント
        	change5050Quantity(pattern2);
            break;
        case 2:
            // パターン2のイベント
        	changeSkipQuantity(pattern2);
            break;
        case 3:
        	// パターン3のイベント
        	change5050Quantity(1);
        	changeSkipQuantity(1);
            break;
        default:
            System.out.println("無効なパターンです。");
            break;
    }
		return patterns;
	}
	
	//クリア関連
	public boolean isClearFlore() {
		System.out.println("isClearFlore Clear=:"+tower.getFlores());
		if(Integer.valueOf(currentFloor).equals(Integer.valueOf(tower.getFlores()+1))	){
			return true;
		}
		return false;
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
    
    public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	
	public int get5050Quantity() {
		int quantity = items.get(0).getQuantity();
		return quantity;
	}
	
	public int getSkipQuantity() {
		int quantity = items.get(1).getQuantity();
		return quantity;
	}
	
	public void change5050Quantity(int i) {
		int quantity = get5050Quantity();
		quantity = quantity + i;
		items.get(0).setQuantity(quantity);
	}
	
	public void changeSkipQuantity(int i) {
		int quantity = getSkipQuantity();
		quantity = quantity + i;
		items.get(1).setQuantity(quantity);
	}
	
	public void usedSKIP() {
		changeSkipQuantity(-1);
		currentQuizIndex++;
	}
	
	public void used5050() {
		change5050Quantity(-1);
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

	public void defeated() {
	    player.setHp(player.getMaxHp()); // プレイヤーのHPをリセット
	}
	//セーブロード機能
	public void saveData() {
	    PlayerData data = new PlayerData(player.getAchieve(), items);
	    playerdata = data;
	}
	
	public void loadData(PlayerData data) {
	    // ロード時の処理
	    // プレイヤーの到達度と所持アイテム情報を適切に反映する
	    player.setAchieve(data.getAchieve());
	    items = data.getItems();
	}
	
	public TowerEntity getTower() {
		return tower;
	}

	public PlayerData getPlayerdata() {
		return playerdata;
	}

	public void setPlayerdata(PlayerData playerdata) {
		this.playerdata = playerdata;
	}

	public PlayerEntity getPlayer() {
		return player;
	}
	//状態異常
	public  String maskString(String input, double maskRatio) {
        if (maskRatio <= 0 || maskRatio > 1) {
            throw new IllegalArgumentException("Invalid mask ratio");
        }
        
        StringBuilder maskedBuilder = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            maskedBuilder.append(c);
            
            if (random.nextDouble() < maskRatio && (i + 1) < input.length()) {
                maskedBuilder.append("■");
            }
        }
        
        return maskedBuilder.toString();
    }
}