package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 아이디가 저장되어 있는 쿠키가 존재하는 경우 해당 아이디를 추출하여 login.jsp에 전달.
		Cookie[] cookies = request.getCookies();
		
		request.setAttribute("username", "");
		for(Cookie c: cookies) {
			if(c.getName().equals("username")) {
				request.setAttribute("username", c.getValue());
			}
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("/WEB-INF/member/login.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 기억하기가 체크되어 있으면 아이디 값을 저장한 쿠키 생성
		String remember = request.getParameter("remember");
		String username = request.getParameter("username");
		
		Cookie[] cookies = request.getCookies();
		if(remember != null) {
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		} else {
			for(Cookie c: cookies) {
				if(c.getName().equals("username")) {
					c.setMaxAge(0); //  if zero, deletes the cookie
					response.addCookie(c);
					break;
				}
			}
		}
		
		response.sendRedirect(request.getContextPath());
	}

}
