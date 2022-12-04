package src.backend.concrete;

import java.util.HashMap;

import com.google.gson.JsonArray;

	/**
	 * An abstract object that store start date, end date and country for the fetcher to retrieve data from using the object variables
	 *
	 */
	abstract public class dataFetcher {
	private int startDate;
	private int endDate;
	private String country;
	
	/**
	 * The original constructor to set immediately start date, end date and country
	 * 
	 * @param startDate target start date for the data to be fetched
	 * @param endDate	target end date for the data to be fetched
	 * @param country	target country for the data to be fetched
	 */
	public dataFetcher(int startDate, int endDate, String country) {
		this.startDate = startDate;
		if (endDate < startDate) {
			this.endDate = -1;
		} else {
			this.endDate = endDate;
		}
		this.country = country;
	}
	
	/**
	 * A null constructor to be used later on after properly set start date, end date and country
	 */
	public dataFetcher() {
		this.startDate = - 1;
		this.endDate = -1;
		
	}
	
	/**
	 * Change current year to be the year in parameter
	 * 
	 * @param year year to replace current start year
	 */
	public void setStartDate(int year) {
		this.startDate = year;
	}
	
	/**
	 * Retrieve current Start date of the object
	 * 
	 * @return current start date
	 */
	public int getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Change end year to be the new year in parameter
	 * 
	 * @param year year to be replaced current end year
	 */
	public void setEndDate(int year) {
		if (year > this.startDate) {
			this.endDate = year;
		}
	}
	
	/**
	 * Retrieve current end year of the object
	 * 
	 * @return year current end year
	 */
	public int getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Change current country to be the new country in parameter
	 * 
	 * @param country target country to have data pulled from
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Retrieve the current country of the object
	 * 
	 * @return country the current set country
	 */
	public String getCountry() {
		return this.country;
	}
	
	abstract public JsonArray getData(String dataIndicator);
	abstract public HashMap<String, String> getCountriesCode();
	
}
