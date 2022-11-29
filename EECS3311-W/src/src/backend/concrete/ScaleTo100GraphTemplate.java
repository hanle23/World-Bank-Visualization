package src.backend.concrete;

import java.util.HashSet;
import java.util.Set;

import src.backend.interfaces.GenerateGraphTemplate;

public class ScaleTo100GraphTemplate implements GenerateGraphTemplate {

	@Override
	public Set<String> getTemplate() {
		Set<String> result =new HashSet<String>();
		result.add("Pie Chart");
		result.add("Bar Chart");
		result.add("Report");
		return result;
	}

}
