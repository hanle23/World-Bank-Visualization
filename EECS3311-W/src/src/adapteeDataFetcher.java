package src;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
				array = (JsonArray) jsonArray.get(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return array;
	}
	
	public static void main(String[] args) {
		Adapter test = new Adapter(2000, 2001, "can");
		JsonArray test1 = test.getData("AG.LND.AGRI.ZS");
		System.out.println(test1);
	}
	
}
