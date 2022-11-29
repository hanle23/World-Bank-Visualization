package src.controller.concrete;

import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.backend.concrete.AnalysisFactory;
import src.backend.concrete.linkedList;
import src.backend.interfaces.analyses;
import src.view.concrete.graphSubject;
import src.view.interfaces.Subject;

public class systemFacade {
	
	private Subject graphPublisher;
	private AnalysisFactory analysisCreator;
	private analyses analysis;
	
	public systemFacade() {
		graphPublisher = new graphSubject();
		analysisCreator = new AnalysisFactory();
	}
	
	public void addGraph(String graphType) {
		String message = "";
		if (graphPublisher.isExist(graphType)) {
			message = "Graph is already exists\nGraphs Selected: " + graphPublisher.getGraphsAsString();
		} else {
			graphPublisher.attach(graphType);
			message = "Graph Added: " + graphType + "\nGraphs Selected: " + graphPublisher.getGraphsAsString();
		}
		JOptionPane.showMessageDialog(null, message, "Graph Selction", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void removeGraph(String graphType) {
		graphPublisher.detach(graphType);
		String message = "Graph Removed: " + graphType + "\nGraphs Selected: " + graphPublisher.getGraphsAsString();
		JOptionPane.showMessageDialog(null, message, "Graph Selction", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void setAnalysis(int startYear, int endYear, String countryCode, String analysisType) {
		this.analysis = analysisCreator.getAnalysis(startYear, endYear, countryCode, analysisType);
	}
	
	public void recalculate(int startYear, int endYear, String countryCode, String analysisType, JPanel graphPanel) {
		//System.out.println("Recieved - startYear:" + startYear + " endYear:" + endYear + " country:"+ country + " analysis:"+analysis);
		setAnalysis(startYear, endYear, countryCode, analysisType);
		if(this.analysis != null) {
			Set<String> AcceptedGraphs = this.analysis.getAcceptGraph();
			String detachedGraphs = "";
			int i = 0;
			for (String key : graphPublisher.getGraphs().keySet()) {
			    if (!AcceptedGraphs.contains(key)) {
			    	graphPublisher.detach(key);
					if (i >= 1) {
						detachedGraphs += ", ";
					}
					detachedGraphs += key;
					i++;
			    }
			}
			if (i > 0) {
				String message = "There are unsupported graphs for this analyses\nGraphs removed: " + detachedGraphs;
				JOptionPane.showMessageDialog(null, message, "Noticed!", JOptionPane.INFORMATION_MESSAGE);
			}
			linkedList analysisData = this.analysis.analyzeData();
			if(analysisData != null)
				graphPublisher.notifyGraphs(analysisData, graphPanel, analysisType);
		}
		graphPublisher.detachAll();//should we detachAll everytime or only when analysis is sucessfull?
	}
}
