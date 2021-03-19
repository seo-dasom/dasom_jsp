package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LottoServlet
 */
@WebServlet("/exam/lotto")
public class LottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LottoServlet() {
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
		html += "	<h1>1 ~ 45 범위의 로또 번호 생성</h1>";
		
		ArrayList<Integer> lotto = new ArrayList();
		while(lotto.size() < 6) {
			int rand = (new Random()).nextInt(45) + 1;
			if(!lotto.contains(rand)) {
				lotto.add(rand);
			}
		}
		for(Integer x: lotto) {
			html += "	<span>" + x + "</span>";
		}
		
		html += "</body>";
		html += "</html>";
		out.println(html);
	}

}
