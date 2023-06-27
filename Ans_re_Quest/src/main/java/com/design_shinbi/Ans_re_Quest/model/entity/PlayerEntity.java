package com.design_shinbi.Ans_re_Quest.model.entity;

public class PlayerEntity {
    private int id;
    private String name;
    private int hp;

    public PlayerEntity(int hp) {
//        this.id = id;
//        this.name = name;
        this.hp = hp;
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

    public void setHp(int hp) {
        this.hp = hp;
    }
  
    // その他、必要なメソッドを追加する
}
