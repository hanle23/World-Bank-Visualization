package src.analyses;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import java.util.TreeMap;

import src.dataExtractor;
import src.util;
import src.concrete.linkedList;
import src.fetcher.Adapter;
import src.fetcher.dataFetcher;
import src.interfaces.analyses;

public class CO2_Energy_PM25 implements analyses {
	private dataFetcher jsonObject;
	private int startYear;
	public CO2_Energy_PM25(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear-1, endYear, countryCode);
		}
		this.startYear = startYear - 1;
	}
	@Override
	public linkedList analyzeData() {
		HashMap<Integer, Double> co2 = CO2_emmision();
		HashMap<Integer, Double> energy = energy_use();
		HashMap<Integer, Double> pm25 = pm25();
		
		if(co2 == null || energy == null || pm25 == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		linkedList result = new linkedList(pm25, new linkedList(energy, new linkedList(co2, null)));
		return result;
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
	
	private HashMap<Integer, Double> pm25() {
		HashMap<Integer, Double> unsorted = new HashMap<Integer, Double>();
		HashMap<Integer, Double> result = new HashMap<>();
		if (jsonObject == null) {
			return null;
		}
		HashMap<Integer, Double> fetchedData = dataExtractor.filter(jsonObject.getData("EN.ATM.PM25.MC.M3"));
		
		if(fetchedData == null) {
			return null;
		}
		
		for (int endYear : fetchedData.keySet()) {
			  if (endYear == this.startYear) {
				  continue;
			  }
			  int startYear = endYear - 1;
			  double CO2_initial = fetchedData.get(startYear);
			  double CO2_end = fetchedData.get(endYear);
			  if (CO2_initial == 0) {
				  unsorted.put(endYear, 0.00);
				  continue;
			  }
			  double pc_rate = ((CO2_end - CO2_initial) / CO2_initial) * 100;
			  unsorted.put(endYear, pc_rate);
			}
		result.putAll(unsorted);
		return result;
	}
	
	private HashMap<Integer, Double> energy_use() {
		HashMap<Integer, Double> unsorted = new HashMap<Integer, Double>();
		HashMap<Integer, Double> result = new HashMap<>();
		if (jsonObject == null) {
			return null;
		}
		HashMap<Integer, Double> fetchedData = dataExtractor.filter(jsonObject.getData("EG.USE.PCAP.KG.OE"));
		
		if(fetchedData == null) {
			return null;
		}
		
		for (int endYear : fetchedData.keySet()) {
			  if (endYear == this.startYear) {
				  continue;
			  }
			  int startYear = endYear - 1;
			  double CO2_initial = fetchedData.get(startYear);
			  double CO2_end = fetchedData.get(endYear);
			  if (CO2_initial == 0) {
				  unsorted.put(endYear, 0.00);
				  continue;
			  }
			  double pc_rate = ((CO2_end - CO2_initial) / CO2_initial) * 100;
			  unsorted.put(endYear, pc_rate);
			}
		result.putAll(unsorted);
		return result;
	}
	
	private HashMap<Integer, Double> CO2_emmision() {
		HashMap<Integer, Double> unsorted = new HashMap<Integer, Double>();
		HashMap<Integer, Double> result = new HashMap<>();
		if (jsonObject == null) {
			return null;
		}
		HashMap<Integer, Double> fetchedData = dataExtractor.filter(jsonObject.getData("EN.ATM.CO2E.PC"));
		
		if(fetchedData == null) {
			return null;
		}
		
		for (int endYear : fetchedData.keySet()) {
			  if (endYear == this.startYear) {
				  continue;
			  }
			  int startYear = endYear - 1;
			  double CO2_initial = fetchedData.get(startYear);
			  double CO2_end = fetchedData.get(endYear);
			  if (CO2_initial == 0) {
				  unsorted.put(endYear, 0.00);
				  continue;
			  }
			  double pc_rate = ((CO2_end - CO2_initial) / CO2_initial) * 100;
			  unsorted.put(endYear, pc_rate);
			}
		result.putAll(unsorted);
		return result;
	}
	

}
