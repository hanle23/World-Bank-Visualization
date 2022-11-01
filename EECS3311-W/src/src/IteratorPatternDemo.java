package src;

import java.util.HashMap;

import src.analyses.CO2EmissionVsGDP;
import src.concrete.linkedList;
import src.interfaces.Iterator;

public class IteratorPatternDemo {
	public static void main(String[] args) {
		linkedList test = new linkedList();
		CO2EmissionVsGDP test1 = new CO2EmissionVsGDP(2000, 2004, "can");
		HashMap<?,?> test2 = test1.analyzeData();
		test.setdata(test2);
		CO2EmissionVsGDP test3 = new CO2EmissionVsGDP(2005, 2007, "can");
		HashMap<?,?> test4 = test3.analyzeData();
		test.setNext(test4);
		for(Iterator iter = test.getIterator(); iter.hasNext();) {
			System.out.println(iter.current());
		}
	}
}
