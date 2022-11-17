package src.concrete;

import javax.swing.JPanel;

import src.graphs.graphSubject;
import src.interfaces.Subject;

public class systemFacade {
	
	private Subject graphPublisher;
	
	public systemFacade() {
		graphPublisher = new graphSubject();
	}
	
	public void addGraph(String graphType) {
		graphPublisher.attach(graphType);
	}
	
	public void removeGraph(String graphType) {
		graphPublisher.detach(graphType);
	}
	
	public void recalculate(int startYear, int endYear, String country, String analysis, JPanel graphPanel) {
		System.out.println("Recieved - startYear:" + startYear + " endYear:" + endYear + " country:"+ country + " analysis:"+analysis);
	}
}
