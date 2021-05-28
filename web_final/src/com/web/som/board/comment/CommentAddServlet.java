package com.web.som.board.comment;

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
import com.web.som.board.comment.db.CommentDAO;
import com.web.som.board.comment.db.CommentVO;

@WebServlet("/ajax/comment/add")
public class CommentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		AccountVO account = (AccountVO)session.getAttribute("account");
		
		String bid = request.getParameter("bid");
		String comment = request.getParameter("comment");
		
		CommentVO data = new CommentVO();
		data.setBid(bid);
		data.setContents(comment);
		data.setAid(account.getId());
		
		CommentDAO dao = new CommentDAO();
		if(dao.insert(data)) {
			// 저장 성공
			dao.selectItem(data);
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("res", "success");
			
			JSONObject jcomment = new JSONObject();
			jcomment.put("contents", data.getContents());
			jcomment.put("cdate", data.getCdate().toString());
			jcomment.put("gcnt", data.getGcnt());
			jcomment.put("bcnt", data.getBcnt());
			json.put("comment", jcomment);
			
			out.println(json.toString());
		} else {
			// 저장 실패
		}
		dao.close();
		
	}

}
