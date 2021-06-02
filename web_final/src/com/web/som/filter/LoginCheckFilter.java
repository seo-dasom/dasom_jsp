package com.web.som.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
	urlPatterns = {
		"/account/info",
		"/board/add", "/board/update",
		"/ajax/comment/add"
	}
)
public class LoginCheckFilter implements Filter {

    public LoginCheckFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// session 정보 중 login과 관련한 세션 정보 값이 존재하는지 확인하여 login 검사
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		if(session.getAttribute("logined") != null) {
			if((boolean)session.getAttribute("logined")) {
				if(session.getAttribute("account") != null) {
					chain.doFilter(request, response);
				} else {
					session.invalidate();
					resp.sendRedirect(req.getContextPath() + "/account/login");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/account/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/account/login");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
