package main.java.model.interfaces;

import java.util.Set;

/**
 * A templates that includes all graphs that support a certain analysis to avoid putting wrong 
 * data on a wrong kind of graphs. 
 * Have one main function getTemplate to retrieve the list of graphs as a Set
 *
 */
public interface GenerateGraphTemplate {
	/**
	 * A set of graphs that includes all the supporting graph for a particular analysis
	 * 
	 * @return A set of graphs as String
	 */
	public Set<String> getTemplate();

}
