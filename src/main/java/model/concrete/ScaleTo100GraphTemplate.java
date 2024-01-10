package main.java.model.concrete;

import java.util.HashSet;
import java.util.Set;

import main.java.model.interfaces.GenerateGraphTemplate;

/**
 * A list of graph that support data set that has a total of 100 or scale up to 100, usually is presented in percentages.
 *
 */
public class ScaleTo100GraphTemplate implements GenerateGraphTemplate {

	/**
	 * Retrieve the Set of graphs as String
	 */
	@Override
	public Set<String> getTemplate() {
		Set<String> result =new HashSet<String>();
		result.add("Pie Chart");
		result.add("Bar Chart");
		result.add("Report");
		return result;
	}

}
