package common.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	private Connection conn;
	
	public DBConnection() {
		this.connect();
	}
	
	private void connect() {
		// JNDI(Java Naming Directory Interface)
		InitialContext init_context = null;
		DataSource data_source = null;
		
		try {
			init_context = new InitialContext();
			data_source = (DataSource)init_context.lookup("java:comp/env/ojdbc/oracle11g-r2/sensor");
			this.conn = data_source.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}
