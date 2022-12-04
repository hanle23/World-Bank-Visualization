package src.backend.concrete;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * An extracting class that takes in JsonArray or any objects, and put necessary data into organized data structure such as LinkedHashMap
 *
 */
public class dataExtractor {

	/**
	 * A filter that pick data from the JsonArray, clean up by excluding years from the excludedList, organize the rest data into a linkedHashMap that is sorted in an ascending order
	 * 
	 * @param object the target object to filter data from
	 * @return LinkedHashMap with data that is already filtered and sorted key in an ascending order
	 */
	public static LinkedHashMap<Integer, Double> filter(JsonArray object) {
		if (object == null) {
			return null;
		}
		List<Integer> excludedYear = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/src/excludedList.json"));
			Gson gson = new Gson();
	        JsonArray js = (gson.fromJson(bufferedReader, JsonObject.class)).get("Years").getAsJsonArray();

	        for (int i = 0; i < js.size(); i++) {
	        	excludedYear.add(js.get(i).getAsInt());
	        }
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HashMap<Integer, Double> tempResult = new HashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> result = new LinkedHashMap<Integer, Double>();
		for (int i = 0; i < object.size(); i++) {
			JsonObject test2 = (JsonObject) object.get(i);
			int year = test2.get("date").getAsInt();
			if (test2.get("value").isJsonNull() || excludedYear.contains(year)) {
				tempResult.put(year, (double) 0);
			} else {
				double value = test2.get("value").getAsDouble();
				tempResult.put(year, value);
			}
			
		}
		Object[] keys = tempResult.keySet().toArray();
		Arrays.sort(keys);
		for(Object key : keys)
			result.put((Integer) key, tempResult.get(key));
		
		return result;
	}

	
}
