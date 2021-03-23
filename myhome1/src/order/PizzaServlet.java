package order;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order/pizza")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PizzaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pizza = request.getParameter("pizza");
		String[] topping = request.getParameterValues("topping");
		String[] side = request.getParameterValues("side");
		
		HashMap<String, HashMap<String, String>> pizza_map = new HashMap();
		pizza_map.put("cheese", new HashMap<String, String>() {{ put("name", "치즈피자"); put("price", "5000"); }});
		pizza_map.put("combination", new HashMap<String, String>() {{ put("name", "콤비네이션피자"); put("price", "6000"); }});
		pizza_map.put("potato", new HashMap<String, String>() {{ put("name", "포테이토피자"); put("price", "7000"); }});
		pizza_map.put("goguma", new HashMap<String, String>() {{ put("name", "고구마피자"); put("price", "7000"); }});
		pizza_map.put("bulgogi", new HashMap<String, String>() {{ put("name", "불고기피자"); put("price", "8000"); }});
		
		HashMap<String, HashMap<String, String>> topping_map = new HashMap();
		topping_map.put("goguma", new HashMap<String, String>() {{ put("name", "고구마무스"); put("price", "1000"); }});
		topping_map.put("corncream", new HashMap<String, String>() {{ put("name", "콘크림무스"); put("price", "1000"); }});
		topping_map.put("pineapple", new HashMap<String, String>() {{ put("name", "파인애플토핑"); put("price", "2000"); }});
		topping_map.put("cheeseT", new HashMap<String, String>() {{ put("name", "치즈토핑"); put("price", "2000"); }});
		topping_map.put("cheeseC", new HashMap<String, String>() {{ put("name", "치즈크러스트"); put("price", "2000"); }});
		topping_map.put("cheeseB", new HashMap<String, String>() {{ put("name", "치즈바이트"); put("price", "3000"); }});
		
		HashMap<String, HashMap<String, String>> side_map = new HashMap();
		side_map.put("ovenchicken", new HashMap<String, String>() {{ put("name", "오픈구이통닭"); put("price", "9000"); }});
		side_map.put("chickenwing", new HashMap<String, String>() {{ put("name", "치킨스틱&윙"); put("price", "4900"); }});
		side_map.put("cheesespa", new HashMap<String, String>() {{ put("name", "치즈오픈스파게티"); put("price", "4000"); }});
		side_map.put("shrimpwedge", new HashMap<String, String>() {{ put("name", "새우링&웨지감자"); put("price", "3500"); }});
		side_map.put("garlicpotato", new HashMap<String, String>() {{ put("name", "갈릭포테이토"); put("price", "3000"); }});
		side_map.put("coke", new HashMap<String, String>() {{ put("name", "콜라"); put("price", "1500"); }});
		side_map.put("cider", new HashMap<String, String>() {{ put("name", "사이다"); put("price", "1500"); }});
		side_map.put("garlicsauce", new HashMap<String, String>() {{ put("name", "갈릭소스"); put("price", "500"); }});
		side_map.put("pickle", new HashMap<String, String>() {{ put("name", "피클"); put("price", "300"); }});
		side_map.put("hotsauce", new HashMap<String, String>() {{ put("name", "핫소스"); put("price", "100"); }});
		side_map.put("parmesan", new HashMap<String, String>() {{ put("name", "파마산치즈가루"); put("price", "100"); }});
		
		int total = Integer.parseInt(pizza_map.get(pizza).get("price"));
		pizza = pizza_map.get(pizza).get("name");
		int i = 0;
		for(String k: topping) {
			total += Integer.parseInt(topping_map.get(k).get("price"));
			topping[i] = topping_map.get(k).get("name");
			i++;
		}
		i = 0;
		for(String k: side) {
			total += Integer.parseInt(side_map.get(k).get("price"));
			side[i] = side_map.get(k).get("name");
			i++;
		}
		
		request.setAttribute("total", total);
		request.setAttribute("pizza", pizza);
		request.setAttribute("topping", topping);
		request.setAttribute("side", side);
		RequestDispatcher dp = request.getRequestDispatcher("pizza/res");
		dp.forward(request, response);
	}

}
