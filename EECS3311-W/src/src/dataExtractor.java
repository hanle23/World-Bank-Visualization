package src;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import src.fetcher.Adapter;
import src.fetcher.dataFetcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class dataExtractor {

	public static LinkedHashMap<Integer, Double> filter(JsonArray object) {
		if (object == null) {
			return null;
		}
		HashMap<Integer, Double> tempResult = new HashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> result = new LinkedHashMap<Integer, Double>();
		for (int i = 0; i < object.size(); i++) {
			JsonObject test2 = (JsonObject) object.get(i);
			int date = test2.get("date").getAsInt();
			if (test2.get("value").isJsonNull()) {
				tempResult.put(date, (double) 0);
			} else {
				double value = test2.get("value").getAsDouble();
				tempResult.put(date, value);
			}
			
		}
		Object[] keys = tempResult.keySet().toArray();
		Arrays.sort(keys);
		for(Object key : keys)
			result.put((Integer) key, tempResult.get(key));
		
		return result;
	}
	
	public static void main(String[] args) {
		dataFetcher test = new Adapter(2009, 2023, "can");
		HashMap<Integer, Double> test1 = filter(test.getData("AG.LND.AGRI.ZS"));
		System.out.println(test1);
	}
	
}
