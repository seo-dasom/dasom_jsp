package com.web.som.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.web.som.board.comment.db.CommentMybatis;

@WebServlet("/ajax/comment/delete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		CommentMybatis dao = new CommentMybatis();
		if(dao.delete(Integer.parseInt(id))) {
			json.put("res", "success");
		} else {
			json.put("res", "fail");
			json.put("message", "삭제 실패..");
		}
		dao.close();
		
		out.println(json.toJSONString());
	}

}
