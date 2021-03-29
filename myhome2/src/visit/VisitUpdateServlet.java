package visit;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/visit/update")
public class VisitUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VisitUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		VisitDAO visit = new VisitDAO();
		VisitVO data = visit.getRecord(id);
		
		request.setAttribute("data", data);
		RequestDispatcher dp = request.getRequestDispatcher("/WEB-INF/visit_update.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String author = request.getParameter("author");
		String context = request.getParameter("context");
		
		VisitDAO visit = new VisitDAO();
		VisitVO data = visit.getRecord(id);
		data.setAuthor(author);
		data.setContext(context);
		visit.updateData(data);
		
		response.sendRedirect("../visit");
	}

}
