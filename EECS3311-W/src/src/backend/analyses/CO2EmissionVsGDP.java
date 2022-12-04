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
 * Concrete implementation of analysis. This calculates the ratio of CO2 emissions to GDP
 * Appropriate graphs for this class include line, scatter, time series, bar, and report
 */
public class CO2EmissionVsGDP implements analyses {
	private Set<String> acceptGraph;
	private dataFetcher jsonObject;
	public CO2EmissionVsGDP(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
		this.acceptGraph = (new GeneralGraphTemplate()).getTemplate();
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
		
	/*	if (!util.COUNTRIES.contains(countryCode) || countryCode == null) {
			System.out.println("country invalid");
			JOptionPane.showMessageDialog(null, "Country is Excluded From Data Fetching", "Country Selction", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}*/
		System.out.println("returning true, proceed");
		return result;
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

		LinkedHashMap<Integer, Double> tempResult = new LinkedHashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> co2Emissions = dataExtractor.filter(jsonObject.getData("EN.ATM.CO2E.PC"));
		LinkedHashMap<Integer, Double> GDP = dataExtractor.filter(jsonObject.getData("NY.GDP.PCAP.CD"));
		
		//checking if there was any data available in the world bank
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
			  //checking if denominator = 0
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
