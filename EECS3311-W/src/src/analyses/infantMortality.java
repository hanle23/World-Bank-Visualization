package src.analyses;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import src.GenerateGraphTemplate;
import src.dataExtractor;
import src.concrete.linkedList;
import src.interfaces.analyses;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;

public class infantMortality implements analyses {
	private dataFetcher jsonObject;
	private Set<String> acceptGraph;
	public infantMortality(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
		this.acceptGraph = (new GenerateGraphTemplate()).template2();
	}
	public Set<String> getAcceptGraph() {
		return acceptGraph;
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
	
	public linkedList analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		LinkedHashMap<Integer, Double> tmp = new LinkedHashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> mortalityData = dataExtractor.filter(jsonObject.getData("SP.DYN.IMRT.IN"));
		
		if(mortalityData == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		for (Entry<Integer, Double> temp : mortalityData.entrySet()) {
			  Integer year = temp.getKey();
			  Double infantMortality = temp.getValue();
			  System.out.println("Infant mortality rate per 1000 people for " + year + " is "+ infantMortality);
			  tmp.put(year, infantMortality);
		}
		linkedList result = new linkedList(tmp, "Infant Mortality Rate (per 1,000 live births)", null);
		
		return result;
		
	}
}
