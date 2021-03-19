package home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bmi/res")
public class BMIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BMIServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		double tall = Double.parseDouble(request.getParameter("tall"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		double bmi = weight / (tall * tall);
		String bmi_type = "정상";
		
		if(bmi < 18.5) {
			bmi_type = "저체중";
		} else if(bmi >= 18.5 && bmi < 23) {
			bmi_type = "정상";
		} else if(bmi >= 23 && bmi < 25) {
			bmi_type = "과체중";
		} else {
			bmi_type = "비만";
		}
		
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
