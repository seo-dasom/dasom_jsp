package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/random")
public class RandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RandServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getRequestDispatcher("rand.jsp");
		dp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int min = Integer.parseInt(request.getParameter("min"));
		int max = Integer.parseInt(request.getParameter("max"));
		
		ArrayList<Integer> rand = new ArrayList<Integer>();
		for(int i = 0; i < 6; i++) {
			rand.add((new Random()).nextInt(max - min + 1) + min);
		}
		
		request.setAttribute("rand", rand);
		RequestDispatcher dp = request.getRequestDispatcher("randres.jsp");
		dp.forward(request, response);
	}

}
