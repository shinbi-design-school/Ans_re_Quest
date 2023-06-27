package com.design_shinbi.Ans_re_Quest.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.Ans_re_Quest.Battle;
import com.design_shinbi.Ans_re_Quest.model.entity.EnemyEntity;
import com.design_shinbi.Ans_re_Quest.model.entity.PlayerEntity;

/**
 * Servlet implementation class Battle
 */
@WebServlet("/Battle")
public class BattleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BattleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストからプレーヤーとモンスターの情報を取得
    	HttpSession session = request.getSession();
    	String jsp = null;
    	
    	
    	
		PlayerEntity player = (PlayerEntity) request.getSession().getAttribute("player");
        EnemyEntity monster = (EnemyEntity) request.getSession().getAttribute("enemy");

        // バトルを作成して、戦闘を実行
        Battle battle = new Battle(player, monster);
        battle.startBattle();

        // 戦闘結果をリクエストに保存
        request.setAttribute("battleResult", battle.getResult());

        // ビューにリダイレクト
        RequestDispatcher dispatcher = request.getRequestDispatcher("battleResult.jsp");
        dispatcher.forward(request, response);
    }

}
