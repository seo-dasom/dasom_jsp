package samp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/getsend1")
public class GetSend1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetSend1Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		
		PrintWriter out = response.getWriter();
		String html = "";
		html += "Client : " + name;
		html += "<br>";
		html += "Server : 메세지를 잘 받았습니다.";
		out.print(html);
	}

}
