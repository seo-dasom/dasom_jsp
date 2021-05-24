package com.web.som.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.som.board.db.BoardDAO;
import com.web.som.board.db.BoardTypeVO;

@WebServlet("/board")
public class BoardMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardMainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<BoardTypeVO> boardtypes = new ArrayList<BoardTypeVO>();
		
		BoardDAO dao = new BoardDAO();
		boardtypes = (ArrayList)dao.getBoardTypes();
		dao.close();
		
		request.setAttribute("boardtypes", boardtypes);
		
		String path = "/WEB-INF/jsp/board/main.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
