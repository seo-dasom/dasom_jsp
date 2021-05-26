package com.web.som.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.som.account.db.AccountDAO;
import com.web.som.account.db.AccountVO;

@WebServlet("/account/join")
public class AccountJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountJoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/WEB-INF/jsp/account/join.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 값을 잘 불러오는지 확인
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		
//		System.out.println("이름 : " + username);
//		System.out.println("닉네임 : " + nickname);
//		System.out.println("이메일 : " + email);
//		System.out.println("패스워드 : " + password);
//		System.out.println("성별 : " + gender);
//		System.out.println("나이 : " + age);
		
		AccountVO data = new AccountVO();
		data.setUsername(username);
		data.setNickname(nickname);
		data.setEmail(email);
		data.setPassword(password);
		data.setGender(gender);
		data.setAge(age);
		
		AccountDAO dao = new AccountDAO();
		boolean res = dao.join(data);
		dao.close();
		
		if(res) {
			// 회원가입 완료 -> 로그인 페이지로 이동 시켜준다
			response.sendRedirect(request.getContextPath() + "/account/login");
		} else {
			// 회원가입 실패 -> 다시 회원가입로 돌아간다
			request.setAttribute("error", "회원가입 실패");
			
			String path = "/WEB-INF/jsp/account/join.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		}
	}

}
