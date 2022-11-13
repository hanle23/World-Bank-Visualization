package src.analyses;

import java.util.HashMap;
import java.util.Map.Entry;

import src.dataExtractor;
import src.util;
import src.concrete.analyses;
import src.fetcher.Adapter;

public class infantMortality {
	private Adapter jsonObject;
	
	public infantMortality(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
	}
	
	private boolean isValid(int startYear, int endYear, String countryCode) {
		boolean result = true;
		if (endYear < startYear) {
			return false;
		}
		if (!util.COUNTRIES.contains(countryCode)) {
			return false;
		}
		return result;
	}
	
	public HashMap<Integer, Double> analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		HashMap<Integer, Double> mortalityData = dataExtractor.filter(jsonObject.getData("SP.DYN.IMRT.IN"));
		for (Entry<Integer, Double> temp : mortalityData.entrySet()) {
			  Integer year = temp.getKey();
			  Double infantMortality = temp.getValue();
			  System.out.println("Infant mortality rate per 1000 people for " + year + " is "+ infantMortality);
			  result.put(year, infantMortality);
		}
		
		return result;
		
	}
}
