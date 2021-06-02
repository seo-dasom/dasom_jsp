package com.web.som.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.web.som.account.db.AccountMybatis;
import com.web.som.account.db.AccountVO;

@WebServlet("/ajax/account/expire")
public class AccountExpireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountExpireServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		AccountVO s_account = (AccountVO)session.getAttribute("account");
		
		String id = request.getParameter("id");
		
		AccountVO data = new AccountVO();
		data.setId(id);
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		// 세션에 저장된 사용자 ID 와 탈퇴 요청을 하는 사용자 ID가 동일한지 검사
		if(s_account.getId() == data.getId()) {
			AccountMybatis dao = new AccountMybatis();
			if(dao.expire(data.getId())) {
				json.put("res", "success");
			} else {
				// 탈퇴 처리에 문제 발생
				json.put("res", "fail");
			}
			dao.close();
		} else {
			// 사용자 요청에 문제 발생
			json.put("res", "fail");
		}
		
		out.println(json.toJSONString());
	}

}
