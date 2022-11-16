package src.graphs;

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
