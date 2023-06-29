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

    public int getEnemyHP() {
        return enemy.getHp();
    }

    public boolean isPlayerAlive() {
        return player.getHp() > 0;
    }

    public boolean isEnemyAlive() {
        return enemy.getHp() > 0;
    }

    public QuizEntity getCurrentQuestion() {
        if (currentQuizIndex >= 0 && currentQuizIndex < quizEntities.size()) {
            return quizEntities.get(currentQuizIndex);
        }
        return null;
    }
    // GetterとSetterなど、必要なメソッドを追加する
}

