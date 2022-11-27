package src.analyses;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import src.GenerateGraphTemplate;
import src.dataExtractor;
import src.concrete.linkedList;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;
import src.interfaces.analyses;
import src.interfaces.Iterator;

public class healthVsBeds implements analyses {
	private dataFetcher jsonObject;
	private String[] acceptGraph;
	public healthVsBeds(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
		this.acceptGraph = (new GenerateGraphTemplate()).template2();
	}
	public String[] getAcceptGraph() {
		return acceptGraph;
	}
	
	public linkedList analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		LinkedHashMap<Integer, Double> unsorted = new LinkedHashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> bedData = dataExtractor.filter(jsonObject.getData("SH.MED.BEDS.ZS"));
		LinkedHashMap<Integer, Double> expendData = dataExtractor.filter(jsonObject.getData("SH.XPD.CHEX.PC.CD"));
		
		if(bedData == null || expendData == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		for (Entry<Integer, Double> temp : bedData.entrySet()) {
			  Integer year = temp.getKey();
			  Double bedAmount = temp.getValue();
			  Double expend = expendData.get(year) * 1000; 
			  unsorted.put(year, expend/bedAmount);
			}
		LinkedHashMap<Integer, Double> series1 = new LinkedHashMap<>();
		series1.putAll(unsorted);
		linkedList result = new linkedList(series1, "Current Health Expenditure (per 1,000 people)/Hospital Beds(per 1,000 people)",null);
		return result;
	}
	
	public LinkedHashMap<Integer, Double> getBedData() {
		if (jsonObject == null) {
			return null;
		}
		return dataExtractor.filter(jsonObject.getData("SH.MED.BEDS.ZS"));
	}
	
	public LinkedHashMap<Integer, Double> getExpend() {
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
//		if (!util.COUNTRIES.contains(countryCode)) {
//			return false;
//		}
		return result;
	}
	public static void main(String args[]) {
		healthVsBeds test = new healthVsBeds(2000, 2004, "can");
		linkedList result = test.analyzeData();
		while (result != null) {
			Iterator print = result.getIterator();
			System.out.println(result.getData());
			result = (linkedList) print.next();
		}
		

		
	}
}
