package src.analyses;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import src.dataExtractor;
import src.util;
import src.concrete.linkedList;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;
import src.interfaces.Iterator;
import src.interfaces.analyses;

public class HealthCareVsMortality implements analyses{
	private dataFetcher jsonObject;
	public HealthCareVsMortality(int startYear, int endYear, String countryCode) {
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
	
	public linkedList analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		
		linkedList dataSet1 = new linkedList();
		linkedList dataSet2 = new linkedList();
		HashMap<Integer, Double> problemsAccessingHealthCare = dataExtractor.filter(jsonObject.getData("SH.ACS.MONY.Q1.ZS"));
		HashMap<Integer, Double> infantMortality = dataExtractor.filter(jsonObject.getData("SP.DYN.IMRT.IN"));
		
		if(problemsAccessingHealthCare == null || infantMortality == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		//have a popup message that analysis cannot be done since API doesn't have data, it returns null when there is no data for all the years, for 1 or more extractor
		
		for (Entry<Integer, Double> temp : problemsAccessingHealthCare.entrySet()) {
			  Integer year = temp.getKey();
			  Double healthCareAmount = temp.getValue();
			  System.out.println("healthCareAmount for " + year + " is "+ healthCareAmount);
			  Double infantMortalityAmount = infantMortality.get(year); 
			  System.out.println("infantMortalityAmount for " + year + " is "+ infantMortalityAmount);
			}
		dataSet1.setdata(problemsAccessingHealthCare);
		dataSet2.setdata(infantMortality);
		dataSet1.setNext(dataSet2);
		//result.setNext(null);
		return dataSet1;
	}
	
	public static void main(String args[]) {
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "can");
		//System.out.println(test.analyzeData());
		linkedList data = test.analyzeData();
		while (data != null) {
			Iterator dataIterator = data.getIterator();
			HashMap<?,?> dataSet = data.getData();
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				System.out.println("In result: co2/GDP for " + temp.getKey()+ " is " + temp.getValue());
			}
			data = (linkedList) dataIterator.next();
			
		}
	}
}
