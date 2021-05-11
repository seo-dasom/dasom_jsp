package nexa.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
		
		PlatformData pdata = new PlatformData();
		
		DataSet ds = new DataSet("serverData");
		ds.addColumn("t", DataTypes.STRING, 1);
		ds.addColumn("id", DataTypes.STRING, 256);
		ds.addColumn("name", DataTypes.STRING, 256);
		ds.addColumn("age", DataTypes.STRING, 256);
		
		NexaTestDAO dao = new NexaTestDAO();
		ArrayList<NexaTestVO> datas = (ArrayList)dao.selectAll();
		
		int row;
		for(NexaTestVO d: datas) {
			row = ds.newRow();
			ds.set(row, "t", "");
			ds.set(row, "id", d.getId());
			ds.set(row, "name", d.getName());
			ds.set(row, "age", d.getAge());
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
