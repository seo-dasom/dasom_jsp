package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MemberDAO member = new MemberDAO();
		MemberVO m = member.getRecord(request.getParameter("name"));
		member.close();
		if(m != null) {
			System.out.println(m.getUserid());
			System.out.println(m.getPassword());
			System.out.println(m.getEmail());
			System.out.println(m.getJoindate());
		} else {
			System.out.println("조회 결과가 없습니다.");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
