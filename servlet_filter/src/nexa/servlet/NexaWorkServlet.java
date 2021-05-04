package nexa.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/work")
public class NexaWorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NexaWorkServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpServletParameter p = new HttpServletParameter(request);
//		p.addParameter("screenid", "work");
//		p.addParameter("formname", "Work::frm_work.xfdl");
//		request = p;
		
		String path = "index.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
