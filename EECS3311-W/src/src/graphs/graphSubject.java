package src.graphs;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.JPanel;

import src.concrete.linkedList;
import src.interfaces.Subject;

public class graphSubject implements Subject{
	private HashMap<String, Graph> graphs;
	private static Properties props;
	private GraphFactory factory;
	
	public graphSubject() {
		try {
			props = new Properties();
			props.load(new FileInputStream("src/src/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Vector<String> graphNames = new Vector<String>();
		graphNames.addAll(Arrays.asList(props.getProperty("Charts").split(",")));
		graphs = new HashMap<String, Graph>();
		factory = new GraphFactory();
		
		for(String graphType: graphNames) {
			graphs.put(graphType, null);
		}
	}
	
	public HashMap<String, Graph> getGraphs() {
		return this.graphs;
	}
	
	public String getGraphsAsString() {
		String selectedGraphs = "";
		for(Entry<String, Graph> temp : graphs.entrySet()) {
			if(temp.getValue() != null)
				selectedGraphs = selectedGraphs + temp.getKey() + ", ";
		}
		return selectedGraphs;
	}

	public void setGraphs(HashMap<String, Graph> graphs) {
		this.graphs = graphs;
	}
	
	@Override
	public void notifyGraphs(linkedList dataSets, JPanel west, String graphTitle) {
		for(Entry<String, Graph> temp : this.graphs.entrySet()) {
			Graph displayedGraph = temp.getValue();
			
			//only display the graph if the user selects it
			if(displayedGraph != null) {
				displayedGraph.update(dataSets, west, graphTitle);
			}
		}
		
	}

	@Override
	public void attach(String graphType) {
		
		//only adds/creates a graph if it wasn't previously added
		if(graphs.get(graphType) == null) {
			graphs.put(graphType, factory.getGraph(graphType));
		}
		System.out.println(graphs);
		
	}

	@Override
	public void detach(String graphType) {
		
		graphs.put(graphType, null);
		System.out.println(graphs);
		
	}
	
	@Override
	public void detachAll() {
		for(String graphType: graphs.keySet()) {
			graphs.put(graphType, null);
		}
		System.out.println(graphs);
		
	}
	
	public static void main(String args[]) {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		test.attach("Scatter Chart");
		test.attach("Report");
		test.attach("Scatter Chart");
		test.detach("Pie Chart");
		test.detach("Pie Chart");
		test.detach("Bar Chart");
		test.detachAll();
		
		
	}


}
