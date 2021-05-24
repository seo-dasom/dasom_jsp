package com.web.som.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.som.account.db.AccountDAO;
import com.web.som.account.db.AccountVO;

@WebServlet("/account/login")
public class AccountLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/WEB-INF/jsp/account/login.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		AccountVO data = new AccountVO();
		data.setEmail(email);
		data.setPassword(password);
		
		AccountDAO dao = new AccountDAO();
		dao.loginCheck(data);
		dao.close();
		
		// 로그인이 성공한 경우 data.id 에 값이 1 이상으로 존재.
		// 로그인이 성공한 경우 session 생성, session 에 저장하는 값은 로그인을 한 사용자의 모든 정보
		if(data.getId() > 0) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			session.setAttribute("account", data);
			session.setAttribute("logined", true);
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("error", "로그인 실패");
			String path = "/WEB-INF/jsp/account/login.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		}
	}

}
