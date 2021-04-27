package servlet.sensor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.sensor.data.TempHumDAO;
import servlet.sensor.data.TempHumVO;

@WebServlet("/temphum")
public class TempHumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TempHumServlet() {
        super();
    }

    // /temphum?t=23.5&h=40
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		double temp = Double.parseDouble(request.getParameter("t"));
		int hum = (int)Double.parseDouble(request.getParameter("h"));
		
		System.out.println(temp + "/" + hum);
		
		// DAO 에 추출한 값을 전달.
		TempHumDAO dao = new TempHumDAO();
		TempHumVO data = new TempHumVO();
		data.setTemp(temp);
		data.setHum(hum);
		//System.out.println(data);
		if(dao.insertData(data)) {
			System.out.println("저장 성공!!");
		};
		dao.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
