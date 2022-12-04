package src.backend.analyses;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import src.backend.concrete.Adapter;
import src.backend.concrete.GeneralGraphTemplate;
import src.backend.concrete.dataExtractor;
import src.backend.concrete.dataFetcher;
import src.backend.concrete.linkedList;
import src.backend.interfaces.Iterator;
import src.backend.interfaces.analyses;

/**
 * Concrete implementation of analysis. This calculates the ratio of health care expenditure to hospital beds
 * Appropriate graphs for this class include line, scatter, time series, bar, and report
 */
public class healthVsBeds implements analyses {
	private dataFetcher jsonObject;
	private Set<String> acceptGraph;
	public healthVsBeds(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
		this.acceptGraph = (new GeneralGraphTemplate()).getTemplate();
	}
	
	/**
	 * Return a set of graph in String format that includes type of graph that could be used for this certain analysis. 
	 * 
	 * @return a set of graph that support this analysis
	 */
	public Set<String> getAcceptGraph() {
		return acceptGraph;
	}
	
	/**
	 * Return the linkedList format of the data after being fetched, transformed, and organized by year. Each node of the linkedList is a set of data in HashMap format
	 * 
	 * @return linkedList format of the data
	 */
	public linkedList analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		LinkedHashMap<Integer, Double> series1 = new LinkedHashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> bedData = dataExtractor.filter(jsonObject.getData("SH.MED.BEDS.ZS"));
		LinkedHashMap<Integer, Double> expendData = dataExtractor.filter(jsonObject.getData("SH.XPD.CHEX.PC.CD"));
		
		//checking if there was any data available in the world bank
		if(bedData == null || expendData == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		for (Entry<Integer, Double> temp : bedData.entrySet()) {
			  Integer year = temp.getKey();
			  Double bedAmount = temp.getValue();
			  Double expend = expendData.get(year) * 1000; 
			  if (bedAmount == 0) {
				  series1.put(year, (double) 0);
			  } else {
				  series1.put(year, expend/bedAmount);
			  }
			  
			}
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
	
	/**
	 * Return the result of checking parameters before fetching the data for this particular analysis
	 * 
	 * @param startYear the start year of the target analysis that user want to retrieve
	 * @param endYear the end year of the target analysis that user want to retrieve
	 * @param countryCode The country name in code format of the target country
	 * @return True if every parameters is valid for this particular analysis
	 */
	@Override
	public boolean isValid(int startYear, int endYear, String countryCode) {
		boolean result = true;
		if (endYear < startYear) {
			JOptionPane.showMessageDialog(null, "Years not Valid", "Years Selction", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (countryCode == null)
			return false;
		
		return result;
	}
	public static void main(String args[]) {
		healthVsBeds test = new healthVsBeds(1999, 2004, "can");
		linkedList result = test.analyzeData();
		while (result != null) {
			Iterator print = result.getIterator();
			System.out.println(result.getData());
			result = (linkedList) print.next();
		}
		

		
	}
}
