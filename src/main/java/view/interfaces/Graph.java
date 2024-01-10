package main.java.view.interfaces;

import javax.swing.JPanel;

import main.java.model.concrete.linkedList;


/**
 * An interface that is used to implement a specific graph, usually is created through receiving a data set to be displayed, a JPanel to be displayed on, and a title
 *
 */
public interface Graph {
	
	/**
	 * Method that creates a graph and populates the data with the analyzed data
	 * 
	 * @param dataSets 	a linkedList that contains the data to be displayed
	 * @param west		the JPanel where the graphs are displayed
	 * @param graphTitle	a string for the title of the graph
	 */
	public void update(linkedList dataSets, JPanel west, String graphTitle);
	
	//Getter for the title of a graph, used for testing purposes
	public String getTitle();
	
	//Getter for data set of a graph, used for testing purposes.
	public linkedList getDataSet();
}
