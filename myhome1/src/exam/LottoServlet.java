package exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exam/lotto")
public class LottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LottoServlet() {
        super();
    }

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Integer> lotto = new ArrayList();
		
		while(lotto.size() < 6) {
			int rand = (new Random()).nextInt(45) + 1;
			if(!lotto.contains(rand)) {
				lotto.add(rand);
			}
		}
		request.setAttribute("lotto", lotto);
		
		RequestDispatcher dp = request.getRequestDispatcher("lotto/res");
		dp.forward(request, response);
	}

}
