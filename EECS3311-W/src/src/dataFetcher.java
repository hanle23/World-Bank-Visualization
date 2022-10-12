package src;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

	abstract public class dataFetcher {
	private int startDate;
	private int endDate;
	private String country;
	
	public dataFetcher(int startDate, int endDate, String country) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
	}
	
	public void setStartDate(int year) {
		this.startDate = year;
	}
	
	public int getStartDate() {
		return this.startDate;
	}
	
	public void setendDate(int year) {
		this.endDate = year;
	}
	
	public int getEndDate() {
		return this.endDate;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	abstract public JsonArray getData(String dataIndicator);
	
}
