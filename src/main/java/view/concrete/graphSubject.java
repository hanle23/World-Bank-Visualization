package main.java.view.concrete;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.model.concrete.linkedList;
import main.java.view.interfaces.Graph;
import main.java.view.interfaces.Subject;

/**
 * A concrete implementation of a Subject class. This is responsible for keeping a list of Graphs that the user requests to be displayed.
 * This will also notify all the graphs when the notifyGraphs method is called
 * Has 3 key fields:
 *  - graphs: the list of "observers"
 *  - An GraphFactory to retrieve/create the graph object chosen by the user
 *  - props: a Properties object that will be used to pull the graph names
 */
public class graphSubject implements Subject{
	private LinkedHashMap<String, Graph> graphs;
	private static Properties props;
	private GraphFactory factory;
	
	public graphSubject() {
		
		try {
			props = new Properties();
			props.load(new FileInputStream("src/main/resources/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//getting the list of graph names from the config file, this is so we can correlate the Graph object with a name as see in the menu 
		Vector<String> graphNames = new Vector<String>();
		graphNames.addAll(Arrays.asList(props.getProperty("Charts").split(",")));
		graphs = new LinkedHashMap<String, Graph>();
		factory = new GraphFactory();
		
		//each entry in the hashmap will start with null value (no graph created for that type)
		for(String graphType: graphNames) {
			graphs.put(graphType, null);
		}
	}
	
	/**
	 * Return the list of graphs
	 * 
	 * @return a HashMap that contains the list of graphs the user has added
	 */
	public HashMap<String, Graph> getGraphs() {
		return this.graphs;
	}
	
	/**
	 * Return a String representing the names of the graphs the user has added
	 * 
	 * @return a String that contains the list of graphs the user has added
	 */
	public String getGraphsAsString() {
		String selectedGraphs = "";
		int i = 0;
		for(Entry<String, Graph> temp : graphs.entrySet()) {
			if(temp.getValue() != null) {
				if (i >= 1) {
					selectedGraphs += ", ";
				}
				selectedGraphs += temp.getKey();
				i++;
			}

			
		}
		return selectedGraphs;
	}
	
	/**
	 * Method that sets the list of graphs
	 * 
	 * @param graphs 	the list of graphs the user wants to add/be displayed
	 */
	public void setGraphs(LinkedHashMap<String, Graph> graphs) {
		this.graphs = graphs;
	}
	
	/**
	 * Method that notifies all the graphs that the user has added
	 * 
	 * @param dataSets 	a linkedList that contains the data to be displayed
	 * @param west		the JPanel where the graphs are displayed
	 * @param graphTitle	a string for the title of the graph
	 */
	@Override
	public void notifyGraphs(linkedList dataSets, JPanel west, String graphTitle) {
		int i = 0;
		for(Entry<String, Graph> temp : this.graphs.entrySet()) {
			Graph displayedGraph = temp.getValue();
			
			//only display the graph if the user selects it
			if(displayedGraph != null) {
				i++;
				displayedGraph.update(dataSets, west, graphTitle);
			}
		}
		
		if(i == 0)
			JOptionPane.showMessageDialog(null, "No Graphs Has Been Selected", "Noticed!", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/**
	 * Return a boolean to see if a graph has been added to the list
	 * 
	 * @return true if the graph has been added, else returns false
	 */
	public boolean isExist(String graphType) {
		if (graphs.get(graphType) != null) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Method that adds a graph object to the list of graphs to be displayed if it hasn't been created yet
	 * 
	 * @param graphType 	the name of the graph the user wants to add
	 */
	@Override
	public void attach(String graphType) {
		
		//only adds/creates a graph if it wasn't previously added
		if(graphs.get(graphType) == null) {
			graphs.put(graphType, factory.getGraph(graphType));
		}
		
	}
	
	/**
	 * Method that removes a graph object from the list of graphs to be displayed
	 * 
	 * @param graphType 	the name of the graph the user wants to remove
	 */
	@Override
	public void detach(String graphType) {
		graphs.put(graphType, null);
		System.out.println(graphs);
		
	}
	
	/**
	 * Method that removes all graphs from the list of graphs to be displayed
	 * 
	 */
	@Override
	public void detachAll() {
		for(String graphType: graphs.keySet()) {
			graphs.put(graphType, null);
		}
		System.out.println(graphs);
		
	}


}
