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

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<BoardTypeVO> boardtypes = new ArrayList<BoardTypeVO>();
		
		BoardDAO dao = new BoardDAO();
		boardtypes = (ArrayList)dao.getBoardTypes();
		dao.close();
		
		request.setAttribute("boardtypes", boardtypes);
		
		String path = "/WEB-INF/jsp/board/add.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션정보를 가져와서 로그인 확인 후 값 저장.
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String btype = request.getParameter("btype");
		String contents = request.getParameter("contents");
		String nodel = request.getParameter("nodel");
		
//		System.out.println("제목: " + title);
//		System.out.println("구분: " + btype);
//		System.out.println("내용: " + contents);
//		System.out.println("삭제금지: " + nodel);
		
		BoardVO data = new BoardVO();
		data.setTitle(title);
		data.setBtype(btype);
		data.setAid(((AccountVO)session.getAttribute("account")).getId());
		data.setContents(contents);
		data.setNodel(nodel);
		
		BoardDAO dao = new BoardDAO();
		dao.insert(data);
		dao.close();
		
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
