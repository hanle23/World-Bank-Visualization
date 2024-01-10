package main.java.model.analyses;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import main.java.model.concrete.Adapter;
import main.java.model.concrete.GeneralGraphTemplate;
import main.java.model.concrete.dataExtractor;
import main.java.model.concrete.dataFetcher;
import main.java.model.concrete.linkedList;
import main.java.model.interfaces.Iterator;
import main.java.model.interfaces.analyses;

/**
 * Concrete implementation of analysis. This pulls data for problems accessing health care and mortality rate
 * Appropriate graphs for this class include line, scatter, time series, bar, and report
 */
public class HealthCareVsMortality implements analyses{
	private dataFetcher jsonObject;
	private Set<String> acceptGraph;
	public HealthCareVsMortality(int startYear, int endYear, String countryCode) {
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
		/*if (!util.COUNTRIES.contains(countryCode)) {
			return false;
		}*/
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
		
		linkedList dataSet1 = new linkedList();
		linkedList dataSet2 = new linkedList();
		LinkedHashMap<Integer, Double> problemsAccessingHealthCare = dataExtractor.filter(jsonObject.getData("SH.ACS.MONY.Q1.ZS"));
		LinkedHashMap<Integer, Double> infantMortality = dataExtractor.filter(jsonObject.getData("SP.DYN.IMRT.IN"));
		
		//checking if there was any data available in the world bank
		if(problemsAccessingHealthCare == null || infantMortality == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		dataSet1.setdata(problemsAccessingHealthCare);
		dataSet1.setName("Problems in accessing health care (% of women)");
		dataSet2.setdata(infantMortality);
		dataSet2.setName("Mortality rate, infant (per 1,000 live births)");
		dataSet1.setNext(dataSet2);

		return dataSet1;
	}
	
	public static void main(String args[]) {
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "can");
		//System.out.println(test.analyzeData());
		linkedList data = test.analyzeData();
		while (data != null) {
			Iterator dataIterator = data.getIterator();
			LinkedHashMap<?,?> dataSet = data.getData();
			System.out.println(data.getName());
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				System.out.println("In result: co2/GDP for " + temp.getKey()+ " is " + temp.getValue());
			}
			System.out.println("\n");
			data = (linkedList) dataIterator.next();
			
		}
	}
}
