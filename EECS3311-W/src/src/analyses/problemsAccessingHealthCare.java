package src.analyses;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import src.concrete.linkedList;

import src.dataExtractor;
import src.util;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;
import src.interfaces.analyses;

public class problemsAccessingHealthCare implements analyses {
	private dataFetcher jsonObject;
	
	public problemsAccessingHealthCare(int startYear, int endYear, String countryCode) {
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
		HashMap<Integer, Double> series1 = new HashMap<Integer, Double>();
		HashMap<Integer, Double> healthCareProblems = dataExtractor.filter(jsonObject.getData("SH.ACS.MONY.Q1.ZS"));
		
		if(healthCareProblems == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		for (Entry<Integer, Double> temp : healthCareProblems.entrySet()) {
			  Integer year = temp.getKey();
			  Double problemPercent = temp.getValue();
			  System.out.println("Percent of woman with insuffiient funds for healthcare for " + year + " is "+ problemPercent);
			  System.out.println("percentage of woman without problems for " + year + " is "+ (100-problemPercent));
			  series1.put(year, problemPercent);
		}
		linkedList result = new linkedList(series1, null);
		
		return result;
}
		
	public static void main(String args[]) {
		forestArea test = new forestArea(2000, 2004, "can");
		System.out.println(test.analyzeData());
	}
}
