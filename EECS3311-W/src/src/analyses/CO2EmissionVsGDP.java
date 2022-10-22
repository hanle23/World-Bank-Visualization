package src.analyses;

import java.util.HashMap;
import java.util.Map.Entry;

import src.dataExtractor;
import src.util;
import src.concrete.analyses;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;

public class CO2EmissionVsGDP implements analyses {
	private dataFetcher jsonObject;
	public CO2EmissionVsGDP(int startYear, int endYear, String countryCode) {
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
		HashMap<Integer, Double> co2Emissions = dataExtractor.filter(jsonObject.getData("EN.ATM.CO2E.PC"));
		HashMap<Integer, Double> GDP = dataExtractor.filter(jsonObject.getData("NY.GDP.PCAP.CD"));
		for (Entry<Integer, Double> temp : co2Emissions.entrySet()) {
			  Integer year = temp.getKey();
			  Double co2Amount = temp.getValue();
			  System.out.println("co2 for " + year + " is "+ co2Amount);
			  Double GDPAmount = GDP.get(year); 
			  System.out.println("GDP for " + year + " is "+ GDPAmount);
			  if(GDPAmount == 0) {
				  result.put(year, 0.0);
				  System.out.println("co2/GDP for " + year + " is 0");
			  }
			  else {
				  result.put(year, co2Amount/GDPAmount);
				  System.out.println("co2/GDP for " + year + " is " + co2Amount/GDPAmount);
			  }
			}
		return result;
	}
	public static void main(String args[]) {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "can");
		System.out.println(test.analyzeData());
	}
}
