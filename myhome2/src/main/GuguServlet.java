package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class GuguServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GuguServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getRequestDispatcher("gugu.jsp");
		dp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int dan_num = Integer.parseInt(request.getParameter("dan_num"));
		
		ArrayList<String> gugu_res = new ArrayList<String>();
		for(int i = 1; i <= 9; i++) {
			gugu_res.add(dan_num + " x " + i + " = " + (i * dan_num));
		}
		
		request.setAttribute("gugu_res", gugu_res);
		RequestDispatcher dp = request.getRequestDispatcher("gugures.jsp");
		dp.forward(request, response);
	}

}
