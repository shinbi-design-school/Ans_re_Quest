package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class PageTestServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ページの表示を確認するための直接リンク用
		// 変数等が関わってくる場合は注意
		String jsp = null;
		jsp = "WEB-INF/jsp/clearEvent.jsp"; //ここの中身を改変して利用

		// 	<a href="test"><button>XxxxTest</button></a>
		// home辺りに置いて適宜利用を

		
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);

	}

}
