package com.design_shinbi.Ans_re_Quest.model.entity;

public class ItemEntity {
	private int itemID;
	private String name;
	private String effect;
	private int quantity;
	
	
	public ItemEntity(int itemID, String name, String effect, int quantity) {
		this.itemID = itemID;
		this.name = name;
		this.effect = effect;
		this.quantity = quantity;
	}


	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEffect() {
		return effect;
	}


	public void setEffect(String effect) {
		this.effect = effect;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
