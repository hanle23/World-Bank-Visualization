package src.concrete;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.JsonArray;

import com.google.gson.JsonParser;

public class adapteeDataFetcher {
	
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
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return array;
	}
	
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
			
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return result;
		
	}
	
	public static void main(String[] args) {
		Adapter test = new Adapter(2000, 2023, "can");
		JsonArray test1 = test.getData("AG.LND.AGRI.ZS");
		String name = "Canada";
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		//System.out.println(countries);
		for (Entry<String, String> entry : countries.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
		   // System.out.println(key.equals(name));
		    String value = entry.getValue();
		    System.out.println(value);

		}
		

	}
	
}
