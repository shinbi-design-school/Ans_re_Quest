package com.design_shinbi.Ans_re_Quest.model.entity;

public class TowerEntity {
    private int towerId;
    private String name;
    private int flores;
    private int eventFlore;
    private String genre;
    private String difficulty;
    
    
    public TowerEntity(int towerId, String name, int flores, int eventFlore, String genre, String difficulty) {
		super();
		this.towerId = towerId;
		this.name = name;
		this.flores = flores;
		this.genre = genre;
		this.difficulty = difficulty;
	}
    
    public int getTowerId() {
        return towerId;
    }
    
    public void setTowerId(int towerId) {
        this.towerId = towerId;
    }
    


	public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getFlores() {
        return flores;
    }
    
    public void setFlores(int flores) {
        this.flores = flores;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
