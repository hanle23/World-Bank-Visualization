package src.analyses;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import src.concrete.dataExtractor;
import src.concrete.GeneralGraphTemplate;
import src.concrete.linkedList;
import src.concrete.Adapter;
import src.concrete.dataFetcher;
import src.interfaces.Iterator;
import src.interfaces.analyses;

public class HealthCareVsMortality implements analyses{
	private dataFetcher jsonObject;
	private Set<String> acceptGraph;
	public HealthCareVsMortality(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
		this.acceptGraph = (new GeneralGraphTemplate()).getTemplate();
	}
	
	private boolean isValid(int startYear, int endYear, String countryCode) {
		boolean result = true;
		if (endYear < startYear) {
			return false;
		}
		/*if (!util.COUNTRIES.contains(countryCode)) {
			return false;
		}*/
		return result;
	}
	public Set<String> getAcceptGraph() {
		return acceptGraph;
	}
	
	public linkedList analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		
		linkedList dataSet1 = new linkedList();
		linkedList dataSet2 = new linkedList();
		LinkedHashMap<Integer, Double> problemsAccessingHealthCare = dataExtractor.filter(jsonObject.getData("SH.ACS.MONY.Q1.ZS"));
		LinkedHashMap<Integer, Double> infantMortality = dataExtractor.filter(jsonObject.getData("SP.DYN.IMRT.IN"));
		
		if(problemsAccessingHealthCare == null || infantMortality == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		//have a popup message that analysis cannot be done since API doesn't have data, it returns null when there is no data for all the years, for 1 or more extractor
		
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
