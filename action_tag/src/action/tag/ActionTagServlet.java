package action.tag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class ActionTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ActionTagServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = "/WEB-INF/jsp/layout/top_left_main.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(page);
		dp.forward(request, response);
	}

}
