package com.design_shinbi.Ans_re_Quest.interfaces;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.design_shinbi.Ans_re_Quest.model.entity.User;

public interface DataRetrieverInterface { //
    public User retrieveUserPlayerItems(Connection connection, HttpSession session) throws SQLException, NoSuchAlgorithmException;
}