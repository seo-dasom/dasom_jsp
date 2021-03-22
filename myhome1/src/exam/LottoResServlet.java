package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exam/lotto/res")
public class LottoResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LottoResServlet() {
        super();
    }

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ArrayList<Integer> lotto = (ArrayList)request.getAttribute("lotto");
		PrintWriter out = response.getWriter();
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html lang=\"ko\">";
		html += "<head>";
		html += "    <meta charset=\"UTF-8\">";
		html += "    <title>Document</title>";
		html += "</head>";
		html += "<body>";
		html += "	<h1>1 ~ 45 범위의 로또 번호 생성</h1>";
		
		for(Integer x: lotto) {
			html += "	<span>" + x + "</span>";
		}
		
		html += "</body>";
		html += "</html>";
		out.println(html);
	}

}
