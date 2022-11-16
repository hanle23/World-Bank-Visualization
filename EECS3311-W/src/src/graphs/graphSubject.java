package src.graphs;

import java.util.List;

import javax.swing.JPanel;

import src.concrete.linkedList;
import src.interfaces.Subject;

public class graphSubject implements Subject{
	private List<Graph> graphs;
	
	//Should we add attach/detach methods??
	public graphSubject() {
		//TODO
	}
	
	public List<Graph> getGraphs() {
		return this.graphs;
	}

	public void setGraphs(List<Graph> graphs) {
		this.graphs = graphs;
	}
	
	@Override
	public void notifyGraphs(linkedList dataSets, JPanel west) {
		for(Graph displayedGraph: this.graphs) {
			displayedGraph.update(dataSets, west);
		}
		
	}

}
