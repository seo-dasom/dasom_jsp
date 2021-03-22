package home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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

	protected void doPost(HttpServletRequest request,
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
		request.setAttribute("tall", tall);
		request.setAttribute("weight", weight);
		request.setAttribute("bmi", bmi);
		request.setAttribute("bmi_type", bmi_type);
		
		RequestDispatcher dp = request.getRequestDispatcher("res/view");
		dp.forward(request, response);
	}

}
