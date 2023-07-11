package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;
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

import com.design_shinbi.Ans_re_Quest.model.Shop;
import com.design_shinbi.Ans_re_Quest.model.dao.ItemDAO;
import com.design_shinbi.Ans_re_Quest.model.dao.PlayerDAO;
import com.design_shinbi.Ans_re_Quest.model.entity.ItemEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;
import com.design_shinbi.Ans_re_Quest.util.DbUtil;


@WebServlet("/shop")
public class ShopServlet extends HttpServlet{

	private Connection connection;
	private PlayerDAO playerDAO;
    private ItemDAO itemDAO;
    private Shop shop;

	
    public void init() throws ServletException {
        try {
            // コネクションの初期化
            this.connection = DbUtil.connect();
            // DAOインスタンスの初期化
            this.playerDAO = new PlayerDAO(connection);
            this.itemDAO = new ItemDAO(connection);
            
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
    
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = request.getSession();
		PlayerEntity player = (PlayerEntity) session.getAttribute("player"); 
		List<ItemEntity> items = (List<ItemEntity>) session.getAttribute("items");
		this.shop = new Shop(player, items);
		
		String jsp = null;
		jsp = "WEB-INF/jsp/shop.jsp";
		request.setAttribute("money", player.getMoney());
		request.setAttribute("5050Quantity", items.get(0).getQuantity());
		request.setAttribute("skipQuantity", items.get(1).getQuantity());
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		try {
			operate(request, operation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}
	
	
	private String operate(HttpServletRequest request, String operation) throws Exception {
		String jsp = null;
		if (operation != null) {
			if (operation.equals("buy5050")) {
				shop.buy5050();
				request.setAttribute("isBuy5050","true");
			} else if (operation.equals("buySkip")) {
				shop.buySkip();
				request.setAttribute("isBuySkip","true");
			}
		}


		return jsp;
	}

}
