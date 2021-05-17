package action.tag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bean")
public class UseBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UseBeanServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/WEB-INF/jsp/bean/usebean.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(path);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int error = 0;
		// 데이터 처리 작업
		// 처리 중 문제 발생하면 -1로 설정
		error = -1;
		if(error == 0) {
			String path = "/WEB-INF/jsp/bean/set.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		} else if(error == -1) {
			String path = "/WEB-INF/jsp/bean/usebean.jsp";
			RequestDispatcher dp = request.getRequestDispatcher(path);
			dp.forward(request, response);
		}
	}

}
