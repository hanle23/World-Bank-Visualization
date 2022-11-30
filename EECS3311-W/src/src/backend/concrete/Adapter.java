package src.backend.concrete;


import java.util.LinkedHashMap;

import com.google.gson.JsonArray;

/**
 * An Adapter pattern to guard against changes in API for client side in case of changes in GET requires parameters
 *
 */
public class Adapter extends dataFetcher{
	
	private adapteeDataFetcher adaptee = new adapteeDataFetcher();
	
	/**
	 * Constructor for Adapter class with available parameters
	 * 
	 * @param startDate input start date from main UI
	 * @param endDate input end date from main UI
	 * @param country input country from main UI in code format
	 */
	public Adapter(int startDate, int endDate, String country) {
		super(startDate, endDate, country);
	}
	
	/**
	 * A null constructor to retrieve available countries code from API
	 */
	public Adapter() {
		super();
	}

	/**
	 *	Retrieve data from a specific implementation of getData for certain scenarios
	 */
	public JsonArray getData(String dataIndicator) {
		if (this.getCountry() == null || this.getStartDate() == -1 || this.getEndDate() == -1) {
			return null;
		}
		return adaptee.specificGetData(this.getCountry(), dataIndicator, this.getStartDate(), this.getEndDate());
	}
	
	/**
	 *	Adapter of the country code retrieval function
	 */
	public LinkedHashMap<String, String> getCountriesCode() {
		return adaptee.specificCountryCodeGetData();
	}
}
