package src.backend.analyses;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import src.backend.concrete.Adapter;
import src.backend.concrete.GeneralGraphTemplate;
import src.backend.concrete.dataExtractor;
import src.backend.concrete.dataFetcher;
import src.backend.concrete.linkedList;
import src.backend.interfaces.analyses;

public class infantMortality implements analyses {
	private dataFetcher jsonObject;
	private Set<String> acceptGraph;
	public infantMortality(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
		this.acceptGraph = (new GeneralGraphTemplate()).getTemplate();
	}
	public Set<String> getAcceptGraph() {
		return acceptGraph;
	}
	
	private boolean isValid(int startYear, int endYear, String countryCode) {
		boolean result = true;
		if (endYear < startYear) {
			JOptionPane.showMessageDialog(null, "Years not Valid", "Years Selction", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return result;
	}
	
	public linkedList analyzeData() {
		if (jsonObject == null) {
			return null;
		}
		LinkedHashMap<Integer, Double> series1 = new LinkedHashMap<Integer, Double>();
		LinkedHashMap<Integer, Double> mortalityData = dataExtractor.filter(jsonObject.getData("SP.DYN.IMRT.IN"));
		
		if(mortalityData == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		for (Entry<Integer, Double> temp : mortalityData.entrySet()) {
			  Integer year = temp.getKey();
			  Double infantMortality = temp.getValue();
			  series1.put(year, infantMortality);
		}
		linkedList result = new linkedList(series1, "Infant Mortality Rate (per 1,000 live births)", null);
		
		return result;
		
	}
}
