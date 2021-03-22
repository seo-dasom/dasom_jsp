package home;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/forward1")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ForwardServlet() {
        super();
    }

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 사용자 요청에 대한 처리..
		RequestDispatcher dispatcher = request.getRequestDispatcher("fwdone1");
		dispatcher.forward(request, response);
	}

}
