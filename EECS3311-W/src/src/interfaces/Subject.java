package src.interfaces;

import java.util.HashMap;

import javax.swing.JPanel;

import src.concrete.linkedList;

public interface Subject {
	
	public void notifyGraphs(linkedList dataSets, JPanel west, String graphTitle);
	public void attach(String graphType);
	public void detach(String graphType);
	public void detachAll();
	public HashMap<String, Graph> getGraphs();
	public String getGraphsAsString();
	public boolean isExist(String graphType);
	
}
