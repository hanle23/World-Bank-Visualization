package main.java.model.analyses;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import main.java.model.concrete.Adapter;
import main.java.model.concrete.ScaleTo100GraphTemplate;
import main.java.model.concrete.dataExtractor;
import main.java.model.concrete.dataFetcher;
import main.java.model.concrete.linkedList;
import main.java.model.interfaces.Iterator;
import main.java.model.interfaces.analyses;


/**
 * Concrete implementation of analysis. This calculates the average government expenditure on education
 * Appropriate graphs for this class include pie, bar, and report since the data adds up to 100
 */
public class avgGovExpenditureOnEd implements analyses{
	private Set<String> acceptGraph; //a list of appropriate graphs for this analysis
	private dataFetcher jsonObject;
	public avgGovExpenditureOnEd(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
		this.acceptGraph = (new ScaleTo100GraphTemplate()).getTemplate();
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
	@Override
	public Set<String> getAcceptGraph() {
		return this.acceptGraph;
	}
	
	/**
	 * Return the linkedList format of the data after being fetched, transformed, and organized by year. Each node of the linkedList is a set of data in HashMap format
	 * 
	 * @return linkedList format of the data
	 */
	@Override
	public linkedList analyzeData() {
		int numOfYear = 0;
		double sumOfExpenditure = 0;
		
		if (jsonObject == null) {
			return null;
		}
		
		linkedList result = new linkedList();
		LinkedHashMap<String, Double> tempResult = new LinkedHashMap<String, Double>();
		LinkedHashMap<Integer, Double> govExpenditure = dataExtractor.filter(jsonObject.getData("SE.XPD.TOTL.GD.ZS")); //pulling the government expenditure data from the world bank
		
		if(govExpenditure == null) { 
			//checking if there was any data available in the world bank
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		//calculating the average expenditure by adding the values together and keeping track of the number of years with values
		for (Entry<Integer, Double> temp : govExpenditure.entrySet()) {
			Integer year = temp.getKey();
			Double expenditureAmount = temp.getValue();
			sumOfExpenditure += expenditureAmount;
			if(expenditureAmount != 0.0) {
				numOfYear++;
			}
			System.out.println("govExpenditure for " + year + " is "+ expenditureAmount);
		}
		Double average;
		//checking to see if the number of years is 0 or not since we cannot divide by 0
		if(numOfYear == 0) {
			average = 0.0;
		}
		else {
			average = sumOfExpenditure/numOfYear;
		}
		System.out.println("Average govExpenditure for the selected years is "+ average);
		
		tempResult.put("Avg government expenditure on education", average);
		tempResult.put("Expenditures for all other", 100 - average);
		result.setdata(tempResult);
		result.setName("Government Expenditure");
		
		return result;
	}
	

}
