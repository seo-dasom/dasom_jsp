package com.web.som.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.web.som.account.db.AccountDAO;

@WebServlet("/ajax/checker/nickname")
public class NicknameCheckerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NicknameCheckerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		AccountDAO dao = new AccountDAO();
		boolean res = dao.usedEmail(nickname);
		dao.close();
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("result", res);
		out.println(json.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
