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

@WebServlet("/nexa/save")
public class NexaWorkSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NexaWorkSaveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpPlatformRequest req = new HttpPlatformRequest(request);
		HttpPlatformResponse resp = new HttpPlatformResponse(
				response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		try {
			req.receiveData();
		} catch (PlatformException e) {
			e.printStackTrace();
		}
		
		PlatformData req_data = req.getData();
		DataSet ds = req_data.getDataSet("data");
		NexaTestDAO dao = new NexaTestDAO();
		int id;
		for(int i = 0; i < ds.getRowCount(); i++) {
			NexaTestVO data = new NexaTestVO();
			id = ds.getString(i, "id") == null ? 0 : Integer.parseInt(ds.getString(i, "id"));
			data.setId(id);
			data.setName(ds.getString(i, "name"));
			data.setAge(Integer.parseInt(ds.getString(i, "age")));
			
			// 0: DataSet.ROW_TYPE_NORMAL, 변경사항이 없는 Row 데이터
			// 1: DataSet.ROW_TYPE_INSERTED, 추가된 Row 데이터
			// 2: DataSet.ROW_TYPE_UPDATED, 수정된 Row 데이터
			if(ds.getRowType(i) == DataSet.ROW_TYPE_INSERTED) {
				dao.insert(data);
			} else if(ds.getRowType(i) == DataSet.ROW_TYPE_UPDATED) {
				if(ds.getString(i, "t").equals("D")) {
					dao.delete(data);
				} else {
					dao.update(data);
				}
			}
		}
		
		// 반영된 내용을 다시 조회
		ArrayList<NexaTestVO> datas = (ArrayList)dao.selectAll();
		PlatformData pdata = new PlatformData();
		
		DataSet resp_data = new DataSet("serverData");
		resp_data.addColumn("t", DataTypes.STRING, 1);
		resp_data.addColumn("id", DataTypes.STRING, 256);
		resp_data.addColumn("name", DataTypes.STRING, 256);
		resp_data.addColumn("age", DataTypes.STRING, 256);
		
		int row;
		for(NexaTestVO d: datas) {
			row = resp_data.newRow();
			resp_data.set(row, "t", "");
			resp_data.set(row, "id", d.getId());
			resp_data.set(row, "name", d.getName());
			resp_data.set(row, "age", d.getAge());
		}
		
		pdata.addDataSet(resp_data);
		
		VariableList var_list = pdata.getVariableList();
		var_list.add("ErrorCode", 0);
		var_list.add("ErrorMsg", "SUCCESS");
		
		resp.setData(pdata);
		
		try {
			resp.sendData();
		} catch (PlatformException e) {
			e.printStackTrace();
		}
	}
}
