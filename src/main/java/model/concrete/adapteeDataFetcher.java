package main.java.model.concrete;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.JsonArray;

import com.google.gson.JsonParser;

/**
 * A specific data fetcher implementation for the original GET method before changes
 *
 */
public class adapteeDataFetcher {
	
	/**
	 * An implementation of a GET and convert from Scanner to JsonArray without filtering
	 * 
	 * @param country MainUI country selection
	 * @param dataIndicator	Type of data to pull from API
	 * @param startDate MainUI start date selection
	 * @param endDate	MainUI end date selection
	 * @return	JsonArray includes the result after fetched from API
	 */
	public JsonArray specificGetData(String country, String dataIndicator, int startDate, int endDate) {
		if (startDate == -1 || endDate == -1) {
			return null;
		}
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, dataIndicator, startDate, endDate);
		JsonArray array = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			// IF THE RESPONSE IS 200 OK GET THE LINE WITH THE RESULTS
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonArray jsonArray = new 
						JsonParser().parse(inline).getAsJsonArray();
				
				if(jsonArray.get(1).isJsonNull())
					return null;
				
				array = (JsonArray) jsonArray.get(1);
			}
		} catch (Exception e) {
			return null;
		}
		return array;
	}
	
	/**
	 * Retrieve a LinkedHashMap of all current available countries and its code from the API for function usages from the API
	 * 
	 * @return all the current available countries and its code format
	 */
	public LinkedHashMap<String, String> specificCountryCodeGetData() {
		String urlString = "http://api.worldbank.org/v2/country?format=json";
		JsonArray array = null;
		LinkedHashMap<String, String> result = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			// IF THE RESPONSE IS 200 OK GET THE LINE WITH THE RESULTS
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();

				array = new JsonParser().parse(inline).getAsJsonArray();
				array = array.get(1).getAsJsonArray();
				result = new LinkedHashMap<String, String>();
				for (int i = 0; i < array.size(); i++) {
					result.put(array.get(i).getAsJsonObject().get("name").toString().strip().trim().replaceAll("\"", ""), array.get(i).getAsJsonObject().get("id").toString().strip().trim().replaceAll("\"", ""));
				}
			}
			
		} catch (Exception e) {
			return null;
		}
		return result;
		
	}
	
	
}
