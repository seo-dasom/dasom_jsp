package order;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order/pizza/res")
public class PizzaResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PizzaResServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		html += "<style type=\"text/css\">";
		html += "p {";
		html += "    font-weight: bold;";
		html += "}";
		html += ".piz {";
		html += "    color: red;";
		html += "}";
		html += ".topping {";
		html += "    color: green;";
		html += "}";
		html += ".side {";
		html += "    color: blue;";
		html += "}";
		html += "</style>";
		html += "<body>";
		html += "    <h2>주문 내역(Servlet 사용)</h2>";
		html += "    <p>피자는 <span class=\"piz\">" + request.getAttribute("pizza") + "</span>,";
		html += "        토핑은 <span class=\"topping\">" + String.join(", ", (String[])request.getAttribute("topping")) + "</span>,";
		html +=	"        사이드는 <span class=\"side\">" + String.join(", ", (String[])request.getAttribute("side")) + "</span> 주문하셨습니다.</p>";
		html += "    <p>총합 : " + request.getAttribute("total") + " 원</p>";
		html += "</body>";
		html += "</html>";
		out.println(html);
		
	}

}
