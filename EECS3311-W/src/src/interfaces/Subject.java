package src.interfaces;

import javax.swing.JPanel;

import src.concrete.linkedList;

public interface Subject {
	
	public void notifyGraphs(linkedList dataSets, JPanel west);
	public void attach(String graphType);
	public void detach(String graphType);
	public void detachAll();
	
}
