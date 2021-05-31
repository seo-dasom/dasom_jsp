package com.web.som.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.som.board.comment.db.CommentDAO;
import com.web.som.board.comment.db.CommentVO;
import com.web.som.board.db.BoardDAO;
import com.web.som.board.db.BoardVO;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		BoardVO item = new BoardVO();
		item.setId(id);
		
		BoardDAO dao = new BoardDAO();
		dao.selectItem(item);
		
		// 조회가 안되었거나, deleted 플래그가 활성화 된 경우 noitem 페이지로 이동
		if(item.getId() != -1 || item.getDeleted().equals("n")) {
			CommentDAO c_dao = new CommentDAO();
			ArrayList<CommentVO> comment_list = (ArrayList)c_dao.selectList(item);
			c_dao.close();
			
			request.setAttribute("newline", "\n");
			request.setAttribute("item", item);
			request.setAttribute("comment_list", comment_list);
			
			String path = "/WEB-INF/jsp/board/detail.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		} else {
			String path = "/WEB-INF/jsp/error/noitem.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
