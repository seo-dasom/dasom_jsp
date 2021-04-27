package servlet.sensor.data;

import java.sql.Timestamp;

public class TempHumVO {
	private int id;
	private double temp;
	private int hum;
	private Timestamp sdate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getTemp() {
		return temp;
	}
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public int getHum() {
		return hum;
	}
	public void setHum(int hum) {
		this.hum = hum;
	}
	
	public Timestamp getSdate() {
		return sdate;
	}
	
	public void setSdate(Timestamp sdate) {
		this.sdate = sdate;
	}
	
}
