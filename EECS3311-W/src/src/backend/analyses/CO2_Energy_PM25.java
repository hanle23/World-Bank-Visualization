package src.backend.analyses;

import java.util.HashMap;
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

public class CO2_Energy_PM25 implements analyses {
	private Set<String> acceptGraph;
	private dataFetcher jsonObject;
	private int startYear;
	public CO2_Energy_PM25(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear-1, endYear, countryCode);
		}
		this.startYear = startYear - 1;
		this.acceptGraph = (new GeneralGraphTemplate()).getTemplate();
	}
	@Override
	public linkedList analyzeData() {
		LinkedHashMap<Integer, Double> co2 = CO2_emmision();
		LinkedHashMap<Integer, Double> energy = energy_use();
		LinkedHashMap<Integer, Double> pm25 = pm25();
		
		if(co2 == null || energy == null || pm25 == null) {
			JOptionPane.showMessageDialog(null, "World Bank Does Not Have Data For The Selected Year(s)", "Data Not Available", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		linkedList result = new linkedList(pm25, "PM25",new linkedList(energy, "Energy",new linkedList(co2, "CO2",null)));
		return result;
	}
	public Set<String> getAcceptGraph() {
		return acceptGraph;
	}
	@Override
	public boolean isValid(int startYear, int endYear, String countryCode) {
		boolean result = true;
		if (endYear < startYear) {
			JOptionPane.showMessageDialog(null, "Years not Valid", "Years Selction", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
//		if (!util.COUNTRIES.contains(countryCode)) {
//			return false;
//		}
		return result;
	}
	
	private LinkedHashMap<Integer, Double> pm25() {
		LinkedHashMap<Integer, Double> result = new LinkedHashMap<>();
		if (jsonObject == null) {
			return null;
		}
		LinkedHashMap<Integer, Double> fetchedData = dataExtractor.filter(jsonObject.getData("EN.ATM.PM25.MC.M3"));
		
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
				  result.put(endYear, 0.00);
				  continue;
			  }
			  double pc_rate = ((CO2_end - CO2_initial) / CO2_initial) * 100;
			  result.put(endYear, pc_rate);
			}
		return result;
	}
	
	private LinkedHashMap<Integer, Double> energy_use() {
		LinkedHashMap<Integer, Double> result = new LinkedHashMap<Integer, Double>();
		if (jsonObject == null) {
			return null;
		}
		LinkedHashMap<Integer, Double> fetchedData = dataExtractor.filter(jsonObject.getData("EG.USE.PCAP.KG.OE"));
		
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
				  result.put(endYear, 0.00);
				  continue;
			  }
			  double pc_rate = ((CO2_end - CO2_initial) / CO2_initial) * 100;
			  result.put(endYear, pc_rate);
			}
		return result;
	}
	
	private LinkedHashMap<Integer, Double> CO2_emmision() {
		LinkedHashMap<Integer, Double> result = new LinkedHashMap<>();
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
				  result.put(endYear, 0.00);
				  continue;
			  }
			  double pc_rate = ((CO2_end - CO2_initial) / CO2_initial) * 100;
			  result.put(endYear, pc_rate);
			}
		return result;
	}
	
	public static void main(String args[]) {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2014, 2021, "CAN");
		linkedList data = test.analyzeData();
		while (data != null) {
			Iterator dataIterator = data.getIterator();
			HashMap<?,?> dataSet = data.getData();
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				System.out.println("Year:  " + temp.getKey()+ " Value: " + temp.getValue());
			}
			data = (linkedList) dataIterator.next();
		}
	}
	

}
