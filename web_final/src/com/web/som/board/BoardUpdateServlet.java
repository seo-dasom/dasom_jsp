package com.web.som.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.som.account.db.AccountVO;
import com.web.som.board.db.BoardDAO;
import com.web.som.board.db.BoardTypeVO;
import com.web.som.board.db.BoardVO;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		
		BoardVO item = new BoardVO();
		item.setId(id);
		
		BoardDAO dao = new BoardDAO();
		dao.selectItem(item);
		ArrayList<BoardTypeVO> boardtypes = (ArrayList)dao.getBoardTypes();
		dao.close();
		
		if(item.getAid() == ((AccountVO)session.getAttribute("account")).getId()) {
			request.setAttribute("boardtypes", boardtypes);
			request.setAttribute("item", item);
			
			String path = "/WEB-INF/jsp/board/update.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		} else {
			String path = "/WEB-INF/jsp/error/noauth.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String btype = request.getParameter("btype");
		String contents = request.getParameter("contents");
		String nodel = request.getParameter("nodel");
		
		BoardVO item = new BoardVO();
		item.setId(id);
		
		BoardDAO dao = new BoardDAO();
		dao.selectItem(item);
		item.setTitle(title);
		item.setBtype(btype);
		item.setContents(contents);
		item.setNodel(nodel);
		boolean result = dao.update(item);
		dao.close();
		
		if(result) {
			// 수정 성공
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + item.getId());
		} else {
			// 수정 실패
			dao = new BoardDAO();
			request.setAttribute("boardtypes", dao.getBoardTypes());
			request.setAttribute("item", item);
			dao.close();
			
			String path = "/WEB-INF/jsp/board/update.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		}
	}

}
