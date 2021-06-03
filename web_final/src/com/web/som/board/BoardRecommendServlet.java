package com.web.som.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.web.som.board.db.BoardMybatis;

@WebServlet("/ajax/board/recommend")
public class BoardRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardRecommendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		int count = 0;
		
		BoardMybatis dao = new BoardMybatis();
		switch(code) {
			case "g":
				dao.good(Integer.parseInt(id));
				dao.commit();
				count = dao.goodCount(Integer.parseInt(id));
				break;
			case "b":
				dao.bad(Integer.parseInt(id));
				dao.commit();
				count = dao.badCount(Integer.parseInt(id));
				break;
		}
		dao.close();
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		json.put("res", "success");
		json.put("code", code);
		json.put("count", count);
		
		out.println(json.toJSONString());
	}

}
