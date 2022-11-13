package src.analyses;

import java.util.HashMap;
import java.util.Map.Entry;

import src.dataExtractor;
import src.util;
import src.concrete.linkedList;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;
import src.interfaces.Iterator;
import src.interfaces.analyses;

public class avgGovExpenditureOnEd implements analyses{
	
	private dataFetcher jsonObject;
	public avgGovExpenditureOnEd(int startYear, int endYear, String countryCode) {
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

	@Override
	public linkedList analyzeData() {
		int numOfYear = 0;
		double sumOfExpenditure = 0;
		
		if (jsonObject == null) {
			return null;
		}
		
		linkedList result = new linkedList(null, null);
		HashMap<String, Double> tempResult = new HashMap<String, Double>();
		HashMap<Integer, Double> govExpenditure = dataExtractor.filter(jsonObject.getData("SE.XPD.TOTL.GD.ZS"));
		
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
		//result.setNext(null);
		
		return result; //need discussion of how to store data for average/pie charts
	}
	
	public static void main(String args[]) {
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "can");
		//System.out.println(test.analyzeData());
		linkedList data = test.analyzeData();
		Iterator dataIterator = data.getIterator();
		while (data != null) {
			HashMap<?,?> dataSet = data.getData();
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				System.out.println(temp.getKey()+ " is " + temp.getValue());
			}
			data = (linkedList) dataIterator.next();
		}
	}

}
