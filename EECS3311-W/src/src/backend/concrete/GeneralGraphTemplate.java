package src.backend.concrete;

import java.util.HashSet;
import java.util.Set;

import src.backend.interfaces.GenerateGraphTemplate;

public class GeneralGraphTemplate implements GenerateGraphTemplate {

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
