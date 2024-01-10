package main.java.controller.concrete;

import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.model.concrete.AnalysisFactory;
import main.java.model.concrete.linkedList;
import main.java.model.interfaces.analyses;
import main.java.view.concrete.graphSubject;
import main.java.view.interfaces.Graph;
import main.java.view.interfaces.Subject;

/**
 * The controller of the system that attach data retrieved after calculated by a specific analysis, move the data through
 * filter-pipes, and attach to chosen graphs. Then all the chosen graphs be attached to the JPanel to be displayed in mainUI.
 * Have 3 main pieces of information including:
 * - A Subject graphPublisher to storing and managing all the current active graphs
 * - An AnalysisFactory to retrieve the result of an analysis chosen by the user
 * - An Analysis to store the result of AnalysisFactory after being calculated.
 *
 */
public class systemFacade {
	
	private Subject graphPublisher;
	private AnalysisFactory analysisCreator;
	private analyses analysis;
	
	/**
	 * The constructor of the facade class that create a new graphSubject to store graphs, and analysisFactory to retrieve data after calculated by a specific analysis
	 */
	public systemFacade() {
		graphPublisher = new graphSubject();
		analysisCreator = new AnalysisFactory();
	}
	
	/**
	 * Add graph into the current active graph. Avoid duplicate graph by checking if the chosen graph is exist, and notify
	 * the user for all the graph that has been chosen so far
	 * 
	 * @param graphType Target graph to be display after the analysis is calculated
	 */
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
	
	/**
	 * Remove graph from the list of chosen graph
	 * 
	 * @param graphType The target graph to be removed from being display
	 */
	public void removeGraph(String graphType) {
		graphPublisher.detach(graphType);
		String message = "Graph Removed: " + graphType + "\nGraphs Selected: " + graphPublisher.getGraphsAsString();
		JOptionPane.showMessageDialog(null, message, "Graph Selction", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Set the current active data set to be the result after calculated by the chosen analysis
	 * 
	 * @param startYear Target start year of the analysis chosen by the user
	 * @param endYear Target end year of the analysis chosen by the user
	 * @param countryCode Target country in code format chosen by the user
	 * @param analysisType Target analysis type chosen by the user
	 */
	public void setAnalysis(int startYear, int endYear, String countryCode, String analysisType) {
		this.analysis = analysisCreator.getAnalysis(startYear, endYear, countryCode, analysisType);
	}
	
	public analyses getAnalysis() {
		return this.analysis;
	}
	
	/**
	 * Main function to combine analysis with graph and prepare for displaying on JPanel in MainUI. Including 2 sub-functions
	 * - Detach all graph that is not supported by the chosen analysis
	 * - Show message notice of the detached if there is any
	 * - Notify all graphs that is chosen by the user
	 * After the the graph has been displayed, detached and reset all graph to it's original state
	 * 
	 * @param startYear target start year of the analysis inputed by user
	 * @param endYear target end year of the analysis inputed by user
	 * @param countryCode target Country in code format chosen by user
	 * @param analysisType the specific analysis type that user selected
	 * @param graphPanel the panel that will displaying graph on
	 */
	public void recalculate(int startYear, int endYear, String countryCode, String analysisType, JPanel graphPanel) {
		setAnalysis(startYear, endYear, countryCode, analysisType);
		if(this.analysis != null) {
			Set<String> AcceptedGraphs = this.analysis.getAcceptGraph();
			String detachedGraphs = "";
			int i = 0;
			for (Entry<String, Graph> entry : graphPublisher.getGraphs().entrySet()) {
			    if (!AcceptedGraphs.contains(entry.getKey()) && entry.getValue() != null) {
			    	graphPublisher.detach(entry.getKey());
					if (i >= 1) {
						detachedGraphs += ", ";
					}
					detachedGraphs += entry.getKey();
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
		graphPublisher.detachAll();
	}
	
	/**
	 * Returns the subject associated with the facade instance.
	 * 
	 * @return Subject object
	 */
	public Subject getSubject() {
		return this.graphPublisher;
	}
}
