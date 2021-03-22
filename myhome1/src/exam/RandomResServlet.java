package exam;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exam/random/res")
public class RandomResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RandomResServlet() {
        super();
    }

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String mn = request.getParameter("mn");
		String mx = request.getParameter("mx");
		String rn = request.getParameter("rn");
		
		PrintWriter out = response.getWriter();
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset=\"UTF-8\">";
		html += "<title>랜덤</title>";
		html += "</head>";
		html += "<body>";
		html += "    <h1>" + mn + " ~ " + mx + " 범위의 임의의 값을 출력하는 서블릿</h1>";
		html += "    <h2>임의값 : " + rn + "</h2>";
		html += "</body>";
		html += "</html>";
		out.println(html);
	}

}
