package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scenario")
public class ScenarioServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsp = null;
		jsp = "WEB-INF/jsp/scenarioTop.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    String action = req.getParameter("scenarioAction");
		    String url = null;

		    if ("GotoPrologue".equals(action)) {
		        url = "WEB-INF/jsp/scenario1_prologue.jsp";
		    } else if ("GotoSupport".equals(action)) {
		        url = "WEB-INF/jsp/scenario2_supportMeeting.jsp";
		    } else if ("GotoShop".equals(action)) {
		        url = "WEB-INF/jsp/scenario3_shop.jsp";
		    } else if ("GotoBoss".equals(action)) {
		        url = "WEB-INF/jsp/scenario4_boss.jsp";
		    } else {
		        url = "WEB-INF/jsp/error.jsp";
		    }

		    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		    dispatcher.forward(req, resp);
		}
		

	}
	

