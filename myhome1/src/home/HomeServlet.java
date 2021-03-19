package home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/servlet/my")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		html += "    <title>Document</title>";
		html += "</head>";
		html += "<body>";
		html += "    <table>";
		html += "        <thead>";
		html += "            <tr>";
		html += "                <th>번호</th>";
		html += "                <th>제목</th>";
		html += "                <th>일자</th>";
		html += "            </tr>";
		html += "        </thead>";
		html += "        <tbody>";
		
		for(int i = 1; i <= 10; i++) {
			html += "            <tr>";
			html += "                <td>" + i + "</td>";
			html += "                <td>안녕하세요</td>";
			html += "                <td>03/17</td>";
			html += "            </tr>";
		}
		
		html += "        </tbody>";
		html += "    </table>";
		html += "</body>";
		html += "</html>";
		out.println(html);
	}

}
