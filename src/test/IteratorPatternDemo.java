package test;

import java.util.HashMap;
import java.util.Map.Entry;

import main.java.model.analyses.CO2EmissionVsGDP;
import main.java.model.concrete.linkedList;
import main.java.model.interfaces.Iterator;

public class IteratorPatternDemo {
	public static void main(String[] args) {
		linkedList test = new linkedList();
		CO2EmissionVsGDP test1 = new CO2EmissionVsGDP(2000, 2004, "can");
		linkedList dataSet1 = test1.analyzeData();
		test.setdata(dataSet1.getData());
		CO2EmissionVsGDP test2 = new CO2EmissionVsGDP(2005, 2007, "can");
		linkedList dataSet2 = test2.analyzeData();
		test.setNext(dataSet2);
		/*for(Iterator iter = test.getIterator(); iter.hasNext();) {
			System.out.println(iter.current());
		}*/
		
		while (test != null) {
			Iterator dataIterator = test.getIterator();
			HashMap<?,?> dataSet = test.getData();
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				System.out.println("In result: co2/GDP for " + temp.getKey()+ " is " + temp.getValue());
			}
			test = dataIterator.next();
			
		}
	}
}
