package nexa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataTypes;
import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.VariableList;
import com.nexacro17.xapi.tx.HttpPlatformRequest;
import com.nexacro17.xapi.tx.HttpPlatformResponse;
import com.nexacro17.xapi.tx.PlatformException;
import com.nexacro17.xapi.tx.PlatformType;

@WebServlet("/nexa/init")
public class NexaWorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NexaWorkServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 넥사크로 플랫폼에서 사용하는 모든 Dataset을 담을 수 있는 객체
		HttpPlatformRequest req = new HttpPlatformRequest(request);
		HttpPlatformResponse res = new HttpPlatformResponse(
				response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		try {
			req.receiveData();	// 클라이언트에서 전송한 데이터 정보를 받기위한 메서드
		} catch (PlatformException e1) {
			e1.printStackTrace();
		}
		PlatformData req_data = req.getData();
		String data = req_data.getVariable("data").getString();
		System.out.println("클라이언트가 전송한 데이터: " + data);
		
		PlatformData pdata = new PlatformData();
		
		DataSet ds = new DataSet("serverData");
		ds.addColumn("id", DataTypes.STRING, 256);
		ds.addColumn("name", DataTypes.STRING, 256);
		ds.addColumn("age", DataTypes.STRING, 256);
		
		int row;
		if(data.equals("홍길동") || data.equals("") || data.equals("undefined")) {
			row = ds.newRow();
			ds.set(row, "id", "1");
			ds.set(row, "name", "홍길동");
			ds.set(row, "age", "32");
		}
		if(data.equals("김철수") || data.equals("") || data.equals("undefined")) {
			row = ds.newRow();
			ds.set(row, "id", "2");
			ds.set(row, "name", "김철수");
			ds.set(row, "age", "31");
		}
		if(data.equals("박연우") || data.equals("") || data.equals("undefined")) {
			row = ds.newRow();
			ds.set(row, "id", "3");
			ds.set(row, "name", "박연우");
			ds.set(row, "age", "29");
		}
		
		pdata.addDataSet(ds);
		
		VariableList var_list = pdata.getVariableList();
		var_list.add("ErrorCode", 0);
		var_list.add("ErrorMsg", "SUCCESS");
		
		res.setData(pdata);
		
		try {
			res.sendData();
		} catch (PlatformException e) {
			e.printStackTrace();
		}
	}

}
