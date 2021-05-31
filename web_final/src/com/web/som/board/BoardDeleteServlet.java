package com.web.som.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.web.som.account.db.AccountVO;
import com.web.som.board.db.BoardDAO;
import com.web.som.board.db.BoardVO;

@WebServlet("/ajax/board/delete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=utf-8");
		String id = request.getParameter("id");
		
		BoardVO item = new BoardVO();
		item.setId(id);
		
		BoardDAO dao = new BoardDAO();
		dao.selectItem(item);
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(item.getAid() == ((AccountVO)session.getAttribute("account")).getId()) {
			if(dao.delete(item)) {
				json.put("res", "success");
				json.put("redirect", request.getContextPath() + "/board");
			} else {
				json.put("res", "fail");
				json.put("redirect", false);
			}
		} else {
			json.put("res", "auth_fail");
			json.put("redirect", false);
		}
		out.println(json.toJSONString());
		dao.close();
	}

}
