package src;

import java.util.HashSet;
import java.util.Set;

public class GenerateGraphTemplate {
	public GenerateGraphTemplate() {}
	public Set<String> template1() {
		Set<String> result =new HashSet<String>();
		result.add("Pie Chart");
		result.add("Bar Chart");
		result.add("Report");
		return result;
	}
	
	public Set<String> template2() {
		Set<String> result = new HashSet<String>();
		result.add("Line Chart");
		result.add("Bar Chart");
		result.add("Scatter Chart");
		result.add("Time Series");
		result.add("Report");
		return result;
	}
}
