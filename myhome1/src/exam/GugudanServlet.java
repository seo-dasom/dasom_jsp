package exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GugudanServlet
 */
@WebServlet("/exam/gugudan")
public class GugudanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GugudanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 응답 인코딩 설정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html lang=\"ko\">";
		html += "<head>";
		html += "    <meta charset=\"UTF-8\">";
		html += "    <title>구구단</title>";
		html += "</head>";
		html += "<body>";
		
		for(int i = 1; i <= 9; i++) {
			html += "<table border=\"1\">";
			html += "	<caption>" + i + " 단</caption>";
			html += "	<tr>";
			for(int j = 1; j <= 9; j++) {
				html += "    	<td>" + i + " x " + j + " = " + (i * j) + "</td>";
				if(j % 3 == 0 && j != 9) {
					html += "	</tr>";
					html += "	<tr>";
				}
			}
			html += "	</tr>";
			html += "</table>";
		}

		html += "</body>";
		html += "</html>";
		out.println(html);
	}

}
