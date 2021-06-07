package com.web.som.board.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.web.som.board.comment.db.CommentMybatis;

@WebServlet("/ajax/comment/recommend")
public class CommentRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentRecommendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		int count = 0;
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		HashMap<String, Boolean> comment_rec_map = (HashMap)session.getAttribute("commentRec");
		if(comment_rec_map == null) {
			comment_rec_map = new HashMap<String, Boolean>();
		}
		
		if(comment_rec_map.get(id) == null) {
			CommentMybatis dao = new CommentMybatis();
			if(code.equals("g")) {
				dao.good(Integer.parseInt(id));
				dao.commit();
				count = dao.goodCount(Integer.parseInt(id));
				
				comment_rec_map.put(id, true);
			} else if(code.equals("b")) {
				dao.bad(Integer.parseInt(id));
				dao.commit();
				count = dao.badCount(Integer.parseInt(id));
				
				comment_rec_map.put(id, true);
			}
			session.setAttribute("commentRec", comment_rec_map);
			dao.close();
			
			json.put("res", "success");
			json.put("code", code);
			json.put("count", count);
		} else {
			json.put("res", "fail");
			json.put("message", "추천/비추천은 한 번만 할 수 있습니다.");
		}
		
		out.println(json.toJSONString());
	}

}
