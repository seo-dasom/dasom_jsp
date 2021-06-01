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
import com.web.som.account.db.AccountMybatis;

@WebServlet("/ajax/checker/email")
public class EmailCheckerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmailCheckerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		
//		AccountDAO dao = new AccountDAO();
//		boolean res = dao.usedEmail(email);
//		dao.close();
		AccountMybatis dao = new AccountMybatis();
		boolean res = dao.usedEmail(email);
		dao.close();
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("result", res);
		out.println(json.toJSONString());
		
//		String json = "";
//		json += "{";
//		json += "    \"result\": " + (res == true ? "true" : "false");
//		json += "}";
//		out.println(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
