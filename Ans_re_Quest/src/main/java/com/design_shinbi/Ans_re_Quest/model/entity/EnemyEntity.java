package com.design_shinbi.Ans_re_Quest.model.entity;

public class EnemyEntity {
    private int id;
    private String name;
    private int hp;
    private int maxHp;

    public EnemyEntity(int id, String name,int hp) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
    
    public int getMaxHp() {
        return maxHp;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }
  
    // その他、必要なメソッドを追加する
}

