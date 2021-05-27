package com.web.som.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if(item.getId() != -1 || item.getDeleted().equals("n")) {
			request.setAttribute("item", item);
			
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
