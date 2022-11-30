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
import src.backend.interfaces.analyses;

public class problemsAccessingHealthCare implements analyses {
	private dataFetcher jsonObject;
	private Set<String> acceptGraph;
	public problemsAccessingHealthCare(int startYear, int endYear, String countryCode) {
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
		LinkedHashMap<Integer, Double> healthCareProblems = dataExtractor.filter(jsonObject.getData("SH.ACS.MONY.Q1.ZS"));
		
		if(healthCareProblems == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		for (Entry<Integer, Double> temp : healthCareProblems.entrySet()) {
			  Integer year = temp.getKey();
			  Double problemPercent = temp.getValue();
			  series1.put(year, problemPercent);
		}
		linkedList result = new linkedList(series1, "Percent of Woman with Insuffiient Funds for Healthcare", null);
		
		return result;
}
		
	public static void main(String args[]) {
		forestArea test = new forestArea(2000, 2004, "can");
		System.out.println(test.analyzeData());
	}
}
