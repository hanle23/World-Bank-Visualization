package src.analyses;


import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import src.dataExtractor;
import src.util;
import src.concrete.linkedList;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;
import src.interfaces.Iterator;
import src.interfaces.analyses;

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
			JOptionPane.showMessageDialog(null, "Years not Valid", "Years Selction", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	/*	if (!util.COUNTRIES.contains(countryCode) || countryCode == null) {
			System.out.println("country invalid");
			JOptionPane.showMessageDialog(null, "Country is Excluded From Data Fetching", "Country Selction", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}*/
		System.out.println("returning true, proceed");
		return result;
	}
	
	public linkedList analyzeData() {
		if (jsonObject == null) {
			return null;
		}

		LinkedHashMap<Integer, Double> tempResult = new LinkedHashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> co2Emissions = dataExtractor.filter(jsonObject.getData("EN.ATM.CO2E.PC"));
		LinkedHashMap<Integer, Double> GDP = dataExtractor.filter(jsonObject.getData("NY.GDP.PCAP.CD"));
		
		if(co2Emissions == null || GDP == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		for (Entry<Integer, Double> temp : co2Emissions.entrySet()) {
			  Integer year = temp.getKey();
			  Double co2Amount = temp.getValue();
			  System.out.println("co2 for " + year + " is "+ co2Amount);
			  Double GDPAmount = GDP.get(year); 
			  System.out.println("GDP for " + year + " is "+ GDPAmount);
			  if(GDPAmount == 0) {
				  tempResult.put(year, 0.0);
				  System.out.println("co2/GDP for " + year + " is 0");
			  }
			  else {
				  tempResult.put(year, co2Amount/GDPAmount);
				  System.out.println("co2/GDP for " + year + " is " + co2Amount/GDPAmount);
			  }
			}
		linkedList result = new linkedList(tempResult, "CO2/GDP",null);
		return result;
	}
	public static void main(String args[]) {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "can");
		linkedList data = test.analyzeData();
		Iterator dataIterator = data.getIterator();
		while (data != null) {
			LinkedHashMap<?,?> dataSet = data.getData();
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				System.out.println("In result: co2/GDP for " + temp.getKey()+ " is " + temp.getValue());
			}
			data = (linkedList) dataIterator.next();
		}
	}
}
