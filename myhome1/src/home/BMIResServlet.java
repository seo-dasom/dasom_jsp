package home;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bmi/res/view")
public class BMIResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BMIResServlet() {
        super();
    }

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Double tall = (Double)request.getAttribute("tall");
		Double weight = (Double)request.getAttribute("weight");
		Double bmi = (Double)request.getAttribute("bmi");
		String bmi_type = (String)request.getAttribute("bmi_type");
		
		
		PrintWriter out = response.getWriter();
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset=\"UTF-8\">";
		html += "<title>BMI 계산기</title>";
		html += "</head>";
		html += "<body>";
		html += "	<h1>BMI 계산기</h1>";
		html += "	<p>계산 결과</p>";
		html += "	<p>키 : " + tall + "</p>";
		html += "	<p>체중 : " + weight + "</p>";
		html += "	<p>BMI : " + bmi + "</p>";
		html += "	<p>구분 : " + bmi_type + "</p>";
		html += "</body>";
		html += "</html>";
		out.println(html);
	}

}
