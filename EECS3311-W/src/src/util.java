package src;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

public class util {
	public static final Set<String> COUNTRIES = Set.of("can");
	private util() 
	{	
	}
	public static HashMap<String, String> jsonToDict(String filename) throws Exception {
		FileReader fis = new FileReader(filename);
		@SuppressWarnings("rawtypes")
		Map<String, ArrayList> json = new Gson()
		        .fromJson(fis, new TypeToken<HashMap<String, ArrayList>>() {}.getType());
		ArrayList<?> array = json.get("data");
		HashMap<String, String> result = new HashMap<String, String>();
		for (int i = 0; i < array.size(); i++) {
			@SuppressWarnings("unchecked")
			LinkedTreeMap<String, String> temp = (LinkedTreeMap<String, String>) array.get(i);
			result.put(temp.get("username").toString(), temp.get("password").toString());
		}
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		HashMap<String, String> test = jsonToDict("loginCredentials/credentials.json");
		if (test.containsKey("admin")) {
			System.out.println("Success");
		}
		

		
	}

}
