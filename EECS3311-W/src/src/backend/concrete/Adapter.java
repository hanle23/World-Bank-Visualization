package src.backend.concrete;


import java.util.LinkedHashMap;

import com.google.gson.JsonArray;

public class Adapter extends dataFetcher{
	
	public Adapter(int startDate, int endDate, String country) {
		super(startDate, endDate, country);
	}
	public Adapter() {
		super();
	}

	private adapteeDataFetcher adaptee = new adapteeDataFetcher();

	public JsonArray getData(String dataIndicator) {
		if (this.getCountry() == null || this.getStartDate() == -1 || this.getEndDate() == -1) {
			return null;
		}
		return adaptee.specificGetData(this.getCountry(), dataIndicator, this.getStartDate(), this.getEndDate());
	}
	
	public LinkedHashMap<String, String> getCountriesCode() {
		return adaptee.specificCountryCodeGetData();
	}
}
