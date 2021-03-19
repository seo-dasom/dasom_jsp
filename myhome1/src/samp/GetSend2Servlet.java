package samp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/getsend2")
public class GetSend2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetSend2Servlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String pass = request.getParameter("pass");
		
		PrintWriter out = response.getWriter();
		String html = "";
		html += "Client : " + pass;
		html += "<br>";
		html += "Server : 패스워드 정보를 잘 받았습니다.";
		out.println(html);
	}

}
