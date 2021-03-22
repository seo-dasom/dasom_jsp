package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exam/random")
public class RandomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RandomServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String _min_val = request.getParameter("min_val");
		String _max_val = request.getParameter("max_val");
		
		int min = Integer.parseInt(_min_val);
		int max = Integer.parseInt(_max_val);
		
		int rand = (new Random()).nextInt(max - min + 1) + min;
		
		response.sendRedirect("random/res?mn="+min+"&mx="+max+"&rn="+rand);
    }
    
//	protected void service(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		// 응답 인코딩 설정
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//		
//		PrintWriter out = response.getWriter();
//		String html = "";
//		html += "<!DOCTYPE html>";
//		html += "<html lang=\"ko\">";
//		html += "<head>";
//		html += "    <meta charset=\"UTF-8\">";
//		html += "    <title>1 ~ 100 랜덤 생성</title>";
//		html += "</head>";
//		html += "<body>";
//		html += "	<h1>1 ~ 100 범위의 랜덤값 생성</h1>";
//		int rand = (new Random()).nextInt(100) + 1;
//		String color[] = {"#F85615", "#F8156C", "#159DF8", "#4AF815", "#F8E115"};
////		String color = "#000000";
////		if(1 <= rand && 20 >= rand) {
////			color = "#F85615";
////		} else if(21 <= rand && 40 >= rand) {
////			color = "#F8156C";
////		} else if(41 <= rand && 60 >= rand) {
////			color = "#159DF8";
////		} else if(61 <= rand && 80 >= rand) {
////			color = "#4AF815";
////		} else if(81 <= rand && 100 >= rand) {
////			color = "#F8E115";
////		}
//		html += "	<h2 style=\"color: " + color[(rand - 1) / 20] + "\">" + rand + "</h2>";
//		html += "</body>";
//		html += "</html>";
//		out.println(html);
//	}

}
