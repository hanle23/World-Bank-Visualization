package src;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class adapteeDataFetcher {
	
	public static JsonObject specificGetData(String country, String dataIndicator, int startDate, int endDate) {
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, dataIndicator, startDate, endDate);
		
		//Why is there a try here?
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			// IF THE RESPONSE IS 200 OK GET THE LINE WITH THE RESULTS
			if (responsecode == 200) {
				String inline = "";
				inline += "{";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				inline += "}";
				sc.close();
				System.out.println(inline);
				//JsonObject convertedObject = new Gson().fromJson(inline, JsonObject.class);
				JsonObject o = new JsonParser().parse(inline).getAsJsonObject();
				return o;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String country = "can";
		int startDate = 2000;
		int endDate = 2002;
		String dataIndicator = "SP.POP.TOTL";
		JsonObject jsonObj = specificGetData(country, dataIndicator, startDate, endDate);
		System.out.println(jsonObj.toString());
	}
	
}
