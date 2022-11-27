package src.interfaces;


import java.util.Set;

import src.concrete.linkedList;

public interface analyses {
	//public HashMap<Integer, Double> analyzeData();
	public linkedList analyzeData();
	public Set<String> getAcceptGraph();
}
