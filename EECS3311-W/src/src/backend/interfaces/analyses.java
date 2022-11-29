package src.backend.interfaces;


import java.util.Set;

import src.backend.concrete.linkedList;

public interface analyses {
	//public HashMap<Integer, Double> analyzeData();
	public linkedList analyzeData();
	public Set<String> getAcceptGraph();
}
