package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.Ans_re_Quest.model.Const;
import com.design_shinbi.Ans_re_Quest.model.dao.UserDAO;
import com.design_shinbi.Ans_re_Quest.model.entity.User;
import com.design_shinbi.Ans_re_Quest.util.DbUtil;

@WebServlet("/login")
public class loginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Connection connection = DbUtil.connect();
			String jsp =  null;
			String error = "";
			User user = null;
			
			UserDAO userDAO = new UserDAO(connection);
			
			String email = req.getParameter("email");
			if(email == null || email.isEmpty()) {
				error = error + "メールアドレスを入力しやがってください";
			}
			
			String password = req.getParameter("password");
			if(password == null || password.isEmpty()) {
				error = error + "パスワードを入力してください。";
			}
			
			if(error.isEmpty()) {
				user = userDAO.login(email, password);
			}
			
			if(user == null) {
				if(error.isEmpty()) {
					error = "ユーザ名、もしくはパスワードが違います。";
				}
				req.setAttribute("error", error);
				jsp = "WEB-INF/jsp/home.jsp";
			}else {
				HttpSession session = req.getSession();
				session.setAttribute(Const.LOGIN_USER_KEY, user);
				jsp = "/WEB-INF/jsp/home.jsp";
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			throw new ServletException(e); 
		}
	
	}

}