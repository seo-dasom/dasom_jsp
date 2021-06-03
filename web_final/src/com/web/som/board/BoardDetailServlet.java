package com.web.som.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.som.board.comment.db.CommentDAO;
import com.web.som.board.comment.db.CommentVO;
import com.web.som.board.db.BoardDAO;
import com.web.som.board.db.BoardMybatis;
import com.web.som.board.db.BoardVO;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		
		BoardVO item = new BoardVO();
		item.setId(id);
		
		// 세션에 저장되어 있는 viewCnt의 데이터를 찾아서 board_map에 저장, 없으면 null
		HashMap<String, String> board_map = (HashMap)session.getAttribute("viewCnt");
		if(board_map == null) {
			board_map = new HashMap<String, String>();
		}
		
		if(board_map.get(Integer.toString(item.getId())) == null) {
			board_map.put(Integer.toString(item.getId()), "true");
			session.setAttribute("viewCnt", board_map);
			
			BoardMybatis b_dao = new BoardMybatis();
			b_dao.incView(item.getId());
			b_dao.commit();
			b_dao.close();
		}
		
		BoardDAO dao = new BoardDAO();
		dao.selectItem(item);
		dao.close();
		
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
