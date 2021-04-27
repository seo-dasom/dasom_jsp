package servlet.sensor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.sensor.data.TempHumDAO;

@WebServlet("/temphum/view")
public class TempHumViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TempHumViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 출력할 페이지 번호      한 페이지에 출력할 목록 수량
		int page = 1;         int list_cnt = 10;
		//  시작 페이지 번호      끝 페이지 번호
		int st_page_num = 0;  int ed_page_num = 0;
		// 화면에 노출할 페이지 번호 수량
		int page_cnt = 5;
		
		// 사용자가 요청한 페이지 번호 정보 추출
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 사용자가 지정한 한 페이지 출력할 목록 수량 추출
		Cookie[] c = request.getCookies();
		if(c != null) {
			for(Cookie v: c) {
				if(v.getName().equals("list_cnt")) {
					list_cnt = Integer.parseInt(v.getValue());
				}
			}
		}
		   
		if(request.getParameter("list_cnt") != null) {
			list_cnt = Integer.parseInt(request.getParameter("list_cnt"));
			Cookie cookie = new Cookie("list_cnt", ""+list_cnt);
			cookie.setMaxAge(60*60*24*5);
			response.addCookie(cookie);
		}
		
		TempHumDAO dao = new TempHumDAO();
		// 사용자가 요청한 페이지에 해당하는 데이터 조회 후 setAttribute에 저장
		request.setAttribute("datas", dao.getPaging(page, list_cnt));
		
		// 가장 마지막 페이지 번호를 구하기 위한 부분
		int total = dao.totalRow();
		int max_page_num = total / list_cnt;
		if(total % list_cnt != 0) {  // 나머지 출력 해야하는 목록이 있는 경우 페이지 번호 증가
			max_page_num++;
		}
		
		// 사용자가 요청한 페이지를 기준으로 시작 페이지 번호를 구하기 위한 코드
		st_page_num = page - (page_cnt / 2) <= 0 ? 1 : page - (page_cnt / 2);
		
		// 사용자가 요청한 페이지를 기준으로 끝 페이지 번호를 구하기 위한 코드
		ed_page_num = st_page_num + (page_cnt - 1) <= max_page_num ? st_page_num + (page_cnt - 1) : max_page_num;
		dao.close();
		
		request.setAttribute("page", page);
		request.setAttribute("st_page_num", st_page_num);
		request.setAttribute("ed_page_num", ed_page_num);
		RequestDispatcher dp = request.getRequestDispatcher("/WEB-INF/jsp/sensor/view.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
