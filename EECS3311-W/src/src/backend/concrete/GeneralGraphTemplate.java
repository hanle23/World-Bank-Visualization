package src.backend.concrete;

import java.util.HashSet;
import java.util.Set;

import src.backend.interfaces.GenerateGraphTemplate;

/**
 * A set of graphs that support general use cases, except for the graphs that only scale up to 100 such as pie graphs that will be used by analysis classes
 *
 */
public class GeneralGraphTemplate implements GenerateGraphTemplate {

	/**
	 *	Retrieve the Set of graphs as String
	 */
	@Override
	public Set<String> getTemplate() {
		Set<String> result = new HashSet<String>();
		result.add("Line Chart");
		result.add("Bar Chart");
		result.add("Scatter Chart");
		result.add("Time Series");
		result.add("Report");
		return result;
	}

}
