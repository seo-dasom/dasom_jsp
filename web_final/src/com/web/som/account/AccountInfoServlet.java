package com.web.som.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.som.account.db.AccountMybatis;
import com.web.som.account.db.AccountVO;

@WebServlet("/account/info")
public class AccountInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountVO account = (AccountVO)session.getAttribute("account");
		
		AccountMybatis dao = new AccountMybatis();
		AccountVO data = dao.select(account.getId());
		dao.close();
		
		request.setAttribute("account", data);
		
		String path = "/WEB-INF/jsp/account/info.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountVO s_account = ((AccountVO)session.getAttribute("account"));
		
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		
		AccountVO data = new AccountVO();
		data.setId(id);
		data.setNickname(nickname);
		data.setGender(gender);
		data.setAge(age);
		
		AccountMybatis dao = new AccountMybatis();
		AccountVO account = dao.select(s_account.getId());
		
		if(data.getId() == account.getId()) {
			// 계정 정보 수정
			if(dao.update(data)) {
				// 업데이트 완료 -> 기존 세션에 저장된 계정 정보 수정
				account = dao.select(s_account.getId());
				s_account.setNickname(account.getNickname());
				s_account.setGender(account.getGender());
				s_account.setAge(account.getAge());
				
				response.sendRedirect(request.getContextPath() + "/account/info");
			} else {
				// 업데이트 실패
				request.setAttribute("error", "계정 수정 작업중 문제가 발생하였습니다.");
				String path = "/WEB-INF/jsp/account/info.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(path);
				dp.forward(request, response);
			}
		} else {
			// 계정 정보에 문제 발생
			request.setAttribute("error", "계정 정보에 문제가 발생하였습니다.");
			String path = "/WEB-INF/jsp/account/info.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		}
	}

}
