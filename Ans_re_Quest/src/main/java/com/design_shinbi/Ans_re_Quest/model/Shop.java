package com.design_shinbi.Ans_re_Quest.model;

import java.util.List;

import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;

public class Shop {
	private PlayerEntity player;
	private List<ItemEntity> items;
	
	
	public Shop(PlayerEntity player, List<ItemEntity> items) {
		this.player = player;
		this.items = items;
	}
	
	public void buy5050() {
		items.get(0).setQuantity(items.get(0).getQuantity()+1);
		player.setMoney(player.getMoney()-20);
	}
	
	public void buySkip() {
		items.get(1).setQuantity(items.get(1).getQuantity()+1);
		player.setMoney(player.getMoney()-10);
	}
	
	//ゲッターセッター↓//
	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	//ゲッターセッター↑//

	
}
