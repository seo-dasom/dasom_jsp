package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 전송한 쿠키 정보를 획득
		Cookie[] cookies = request.getCookies();
		
//		// login.jsp 페이지에서 사용할 아이디 초기값을 미리 생성
//		request.setAttribute("username", "");
		
		// 클라이언트가 전송한 쿠키 정보 중 로그인 아이디 쿠키 정보가 있는지 검사
		for(Cookie c: cookies) {
			if(c.getName().equals("username")) {
				// 로그인 아이디 쿠키 정보를 login.jsp 페이지에서 사용할 아이디 초기값으로 설정
				request.setAttribute("username", c.getValue());
				break;
			}
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("/WEB-INF/member/login.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 입력한 데이터 획득
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		
		// member_t 테이블에 저장되어 있는 username, password 가 있는지 확인하는 절차
		MemberDAO dao = new MemberDAO();
		MemberVO member = dao.login(username, password); // 계정 정보가 DB에 있는지 검색
		HttpSession session = request.getSession();
		
		if(member.getUserid() != null) { // 계정 정보가 있는 경우
			session.setAttribute("login", "true");
			session.setAttribute("username", member.getUserid());
			session.setAttribute("email", member.getEmail());
			session.setAttribute("joindate", member.getJoindate());
			
			// 클라이언트가 전송한 쿠키 정보 획득
			Cookie[] cookies = request.getCookies();
			
			// 기억하기 체크 박스가 체크되어 있는 경우 아이디 정보를 저장하는 쿠키 생성
			if(remember != null) {
				Cookie cookie = new Cookie("username", username);
				cookie.setMaxAge(60*60*24);	// 만료시간 24시간으로 설정
				response.addCookie(cookie);
			} else {	// 기억하기 체크 박스가 해제되어 있는 경우 기존 아이디 정보를 저장한 쿠키 만료시간을 0 으로 설정
				for(Cookie c: cookies) {
					if(c.getName().equals("username")) {
						c.setMaxAge(0); //  if zero, deletes the cookie
						response.addCookie(c);
						break;
					}
				}
			}
			
			response.sendRedirect(request.getContextPath());
		} else {
			RequestDispatcher dp = request.getRequestDispatcher("/WEB-INF/member/login.jsp");
			dp.forward(request, response);
		}
		
	}

}
