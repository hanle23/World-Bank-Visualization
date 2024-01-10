package main.java.view.interfaces;

import java.util.HashMap;

import javax.swing.JPanel;

import main.java.model.concrete.linkedList;

public interface Subject {
	
	/**
	 * Method that notifies all the graphs that the user has added
	 * 
	 * @param dataSets 	a linkedList that contains the data to be displayed
	 * @param west		the JPanel where the graphs are displayed
	 * @param graphTitle	a string for the title of the graph
	 */
	public void notifyGraphs(linkedList dataSets, JPanel west, String graphTitle);
	
	/**
	 * Method that adds a graph object to the list of graphs to be displayed if it hasn't been created yet
	 * 
	 * @param graphType 	the name of the graph the user wants to add
	 */
	public void attach(String graphType);
	
	/**
	 * Method that removes a graph object from the list of graphs to be displayed
	 * 
	 * @param graphType 	the name of the graph the user wants to remove
	 */
	public void detach(String graphType);
	
	/**
	 * Method that removes all graphs from the list of graphs to be displayed
	 * 
	 */
	public void detachAll();
	
	/**
	 * Return the list of graphs
	 * 
	 * @return a HashMap that contains the list of graphs the user has added
	 */
	public HashMap<String, Graph> getGraphs();
	
	/**
	 * Return a String representing the names of the graphs the user has added
	 * 
	 * @return a String that contains the list of graphs the user has added
	 */
	public String getGraphsAsString();
	
	/**
	 * Return a boolean to see if a graph has been added to the list
	 * 
	 * @return true if the graph has been added, else returns false
	 */
	public boolean isExist(String graphType);
	
}
