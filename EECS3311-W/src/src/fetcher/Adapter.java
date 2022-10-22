package src.fetcher;

import com.google.gson.JsonArray;

public class Adapter extends dataFetcher{
	
	public Adapter(int startDate, int endDate, String country) {
		super(startDate, endDate, country);
	}

	private adapteeDataFetcher adaptee = new adapteeDataFetcher();

	public JsonArray getData(String dataIndicator) {
		return adaptee.specificGetData(this.getCountry(), dataIndicator, this.getStartDate(), this.getEndDate());
	}
}
