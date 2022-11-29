package src.view.concrete;

import src.view.graphs.bar;
import src.view.graphs.lineGraph;
import src.view.graphs.pie;
import src.view.graphs.report;
import src.view.graphs.scatter;
import src.view.graphs.timeSeries;
import src.view.interfaces.Graph;

public class GraphFactory {
	
	public Graph getGraph(String graphType) {
		
		if(graphType == null)
			return null;
		
		if(graphType.equalsIgnoreCase("Pie Chart"))
			return new pie();
		if(graphType.equalsIgnoreCase("Line Chart"))
			return new lineGraph();
		if(graphType.equalsIgnoreCase("Bar Chart"))
			return new bar();
		if(graphType.equalsIgnoreCase("Scatter Chart"))
			return new scatter();
		if(graphType.equalsIgnoreCase("Time Series"))
			return new timeSeries();
		if(graphType.equalsIgnoreCase("Report"))
			return new report();
		
		
		return null;
		
	}
	
}
