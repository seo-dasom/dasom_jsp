package home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetServlet
 */
@WebServlet("/home/getdata")
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//	protected void service(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("service() 메서드 동작");
//		
//		super.service(request, response);
//	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String z[] = request.getParameterValues("z");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String html = "";
		html += "doGet() 메서드 동작";
		html += "<br>파라메터 x의 값을 추출 하였습니다. -> " + x;
		html += "<br>파라메터 y의 값을 추출 하였습니다. -> " + y;
		html += "<br>파라메터 z의 값을 추출 하였습니다. -> " + z.length;
		html += "<br>파라메터 z[0]의 값을 추출 하였습니다. -> " + z[0];
		html += "<br>파라메터 z[1]의 값을 추출 하였습니다. -> " + z[1];
		out.println(html);
	}

}
