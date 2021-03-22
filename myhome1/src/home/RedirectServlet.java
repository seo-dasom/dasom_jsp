package home;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RedirectServlet() {
        super();
    }

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// response.sendRedirect("reddone");	// 상대경로로 적용 가능
		response.addHeader("Refresh", "3,url=reddone");
		PrintWriter out = response.getWriter();
		out.println("3초 뒤에 다른페이지로 이동합니다.");
	}

}
