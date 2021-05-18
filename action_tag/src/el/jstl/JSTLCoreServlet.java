package el.jstl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jstl/core")
public class JSTLCoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JSTLCoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path ="/WEB-INF/jsp/el_jstl/jstl1.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
