package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.Ans_re_Quest.implementation.DataRetrieverImpl;
import com.design_shinbi.Ans_re_Quest.model.dao.ItemDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.PlayerDAO;
import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.User;
import com.design_shinbi.Ans_re_Quest.util.DbUtil;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
    private Connection connection;
    private PlayerDAO playerDAO;
    private ItemDAO itemDAO;
	private User user = null;
	private PlayerEntity player;
	private List<ItemEntity> items;
	private DataRetrieverImpl dataRetrieverImpl;
	
    public void init() throws ServletException {
        try {
            // コネクションの初期化
            this.connection = DbUtil.connect();
            // DAOインスタンスの初期化
            this.playerDAO = new PlayerDAO(connection);
            this.itemDAO = new ItemDAO(connection);
            this.dataRetrieverImpl = new DataRetrieverImpl();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		try {
			user = dataRetrieverImpl.retrieveUserPlayerItems(connection, session);
			player = (PlayerEntity) session.getAttribute("player");
			items = (List<ItemEntity>) session.getAttribute("items");
		} catch (NoSuchAlgorithmException | SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
				

				
    	
		String jsp = null;
		jsp = "WEB-INF/jsp/home.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}

}
