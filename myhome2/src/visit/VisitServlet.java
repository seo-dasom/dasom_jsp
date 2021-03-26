package visit;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/visit")
public class VisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VisitServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		VisitDAO visit = new VisitDAO();
		ArrayList<VisitVO> datas = visit.getAll();
		// ArrayList<VisitVO> datas = visit.getRecords(Date.valueOf("2021-03-26"));
		visit.close();
		
		request.setAttribute("datas", datas);
		RequestDispatcher dp = request.getRequestDispatcher("/WEB-INF/visit.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 1. 클라이언트가 전달한 파라메터 추출
		String author = request.getParameter("author");
		String context = request.getParameter("context");
		
		// 2. 추출한 파라메터를 VisitVO를 생성하여 저장
		VisitVO data = new VisitVO(author, context);
		
		// 3. VisitDAO를 생성 후 2번에서 만든 VisitVO를 전달 후 저장
		VisitDAO visit = new VisitDAO();
		visit.saveData(data);
		visit.close();
		
		// 4. 저장 완료 후 localhost:8080/home2/visit 를 다시 요청하도록 클라이언트에
		//    리다이렉트 메세지 전달
		response.sendRedirect("./visit");
	}

}
