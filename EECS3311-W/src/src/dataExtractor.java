package src;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class dataExtractor {

	public static HashMap<Integer, Double> filter(JsonArray object) {
		if (object == null) {
			return null;
		}
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		for (int i = 0; i < object.size(); i++) {
			JsonObject test2 = (JsonObject) object.get(i);
			int date = test2.get("date").getAsInt();
			if (test2.get("value").isJsonNull()) {
				result.put(date, (double) 0);
			} else {
				double value = test2.get("value").getAsDouble();
				result.put(date, value);
			}
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		dataFetcher test = new Adapter(2009, 2023, "can");
		HashMap<Integer, Double> test1 = filter(test.getData("AG.LND.AGRI.ZS"));
		System.out.println(test1);
	}
	
}
