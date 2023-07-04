package com.design_shinbi.Ans_re_Quest.model.entity;

import java.sql.Timestamp;

public class User {
private int id;
private Timestamp createdAt;
private Timestamp updatedAt;
private String email;
private String name;
private String password;
private boolean admin;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public Timestamp getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(Timestamp createdAt) {
	this.createdAt = createdAt;
}
public Timestamp getUpdatedAt() {
	return updatedAt;
}
public void setUpdatedAt(Timestamp updatedAt) {
	this.updatedAt = updatedAt;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isAdmin() {
	return admin;
}
public void setAdmin(boolean admin) {
	this.admin = admin;
}


}
