package src;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Adapter extends dataFetcher{
	
	public Adapter(int startDate, int endDate, String country) {
		super(startDate, endDate, country);
	}

	private adapteeDataFetcher adaptee = new adapteeDataFetcher();

	public JsonObject getData(String country, String dataIndicator, int startDate, int endDate) {
		return adaptee.specificGetData(country, dataIndicator, startDate, endDate);
	}
}
