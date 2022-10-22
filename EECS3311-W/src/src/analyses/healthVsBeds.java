package src.analyses;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonObject;

import src.dataExtractor;
import src.util;
import src.concrete.analyses;
import src.fetcher.Adapter;

public class healthVsBeds implements analyses {
	private Adapter jsonObject;
	public healthVsBeds(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
	}
	
	public HashMap<Integer, Double> analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		HashMap<Integer, Double> bedData = dataExtractor.filter(jsonObject.getData("SH.MED.BEDS.ZS"));
		HashMap<Integer, Double> expendData = dataExtractor.filter(jsonObject.getData("SH.XPD.CHEX.PC.CD"));
		for (Entry<Integer, Double> temp : bedData.entrySet()) {
			  Integer year = temp.getKey();
			  Double bedAmount = temp.getValue();
			  System.out.println(bedAmount);
			  Double expend = expendData.get(year) * 1000; 
			  System.out.println(expend);
			  result.put(year, expend/bedAmount);
			}
		return result;
	}
	
	public HashMap<Integer, Double> getBedData() {
		if (jsonObject == null) {
			return null;
		}
		return dataExtractor.filter(jsonObject.getData("SH.MED.BEDS.ZS"));
	}
	
	public HashMap<Integer, Double> getExpend() {
		// This expend from API is per capital, we need to multiply to 1000
		if (jsonObject == null) {
			return null;
		}
		return dataExtractor.filter(jsonObject.getData("SH.XPD.CHEX.PC.CD"));
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
	public static void main(String args[]) {
		healthVsBeds test = new healthVsBeds(2000, 2004, "can");
		System.out.println(test.analyzeData());
	}
}
