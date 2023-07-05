package com.design_shinbi.Ans_re_Quest.model.entity;

public class EnemyEntity {
    private int id;
    private String name;
    private int hp;
    private int maxHp;
    private String genre;

    public EnemyEntity(int id, String name, int hp, String genre) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.genre = genre;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
  
    // その他、必要なメソッドを追加する
}