package com.design_shinbi.Ans_re_Quest.model.entity;

import java.util.List;

public class PlayerData {
    private int achieve;
    private List<ItemEntity> items;
    
    public PlayerData(int achieve, List<ItemEntity> items) {
        this.achieve = achieve;
        this.items = items;
    }

	public int getAchieve() {
		return achieve;
	}

	public void setAchieve(int achieve) {
		this.achieve = achieve;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
    
}
