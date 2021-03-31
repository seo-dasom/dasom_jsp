package main;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 	쿠키(Cookie)
		 * 		서버가 클라이언트에 다양한 데이터를 저장하기 위해 사용하는 작은 정보 조각
		 * 		클라이언트의 상태를 기록하기 위한 용도로 사용(HTTP는 상태를 관리하지 않기 때문에)
		 * 		쿠키는 클라이언트에 암호화되어 저장하지 않기 때문에 보안에 취약하기 때문에
		 * 		개인정보와 같은 민감한 데이터는 쿠키로 저장하지 않는다.
		 * 			- 오늘의 팝업 메시지를 계속 띄우느냐/띄우지 않느냐.
		 * 			- 이전에 로그인을 한 계정명을 기억하느냐/기억하지 않느냐.
		 * 			- 웹 페이지 테마를 어떠한 테마로 설정했느냐. 등등...
		 * 
		 * 	세션(Session)
		 * 		쿠키 처럼 서버가 클라이언트에 세션 정보를 저장을 하나, 이 세션 정보는 암호화가된 세션 ID 이고
		 * 		이 세션 ID 에는 어떠한 개인정보도 포함되어 있지 않다.
		 * 		이 세션 ID 가 서버에 전달이 되면 서버에 저장되어 있는 동일한 세션 ID의 정보를 획득하여
		 * 		이전 사용자에 대한 기록을 확인 할 수 있다.
		 */
		Cookie[] c = request.getCookies();
		if(c != null) {
			for(Cookie ck: c) {
				if(!ck.getName().equals("name")) {
					// 쿠키 생성				   쿠키이름    쿠키값
					Cookie cookie = new Cookie("name", "value");
					cookie.setMaxAge(60*60*24);	// 클라이언트에 저장된 쿠키 정보가 유지되는 시간
					// 클라이언트에 보낼 응답 객체에 생성한 쿠키를 담는다.
					response.addCookie(cookie);
				} else if(ck.getName().equals("name")){
					System.out.println("쿠키명 : " + ck.getName());
					System.out.println("쿠키값 : " + ck.getValue());
				}
			}
		}
			
		RequestDispatcher dp = request.getRequestDispatcher("/WEB-INF/main.jsp");
		dp.forward(request, response);
	}

}
