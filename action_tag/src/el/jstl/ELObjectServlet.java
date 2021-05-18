package el.jstl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/el/obj")
public class ELObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ELObjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("name", "session value");
		
		String path ="/WEB-INF/jsp/el_jstl/el_obj.jsp";
		request.setAttribute("name", "value");
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
