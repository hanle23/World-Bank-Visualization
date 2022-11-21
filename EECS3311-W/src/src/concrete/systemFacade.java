package src.concrete;

import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.fetcher.Adapter;
import src.fetcher.AnalysisFactory;
import src.fetcher.dataFetcher;
import src.graphs.graphSubject;
import src.interfaces.Subject;
import src.interfaces.analyses;

public class systemFacade {
	
	private Subject graphPublisher;
	private AnalysisFactory analysisCreator;
	
	public systemFacade() {
		graphPublisher = new graphSubject();
		analysisCreator = new AnalysisFactory();
	}
	
	public void addGraph(String graphType) {
		graphPublisher.attach(graphType);
	}
	
	public void removeGraph(String graphType) {
		graphPublisher.detach(graphType);
	}
	
	public void recalculate(int startYear, int endYear, String countryCode, String analysisType, JPanel graphPanel) {
		//System.out.println("Recieved - startYear:" + startYear + " endYear:" + endYear + " country:"+ country + " analysis:"+analysis);
		
		analyses chosenAnalysis = analysisCreator.getAnalysis(startYear, endYear, countryCode, analysisType);
		if(chosenAnalysis != null) {
			linkedList analysisData = chosenAnalysis.analyzeData();
			if(analysisData != null)
				graphPublisher.notifyGraphs(analysisData, graphPanel, analysisType);
		}
		graphPublisher.detachAll();//should we detachAll everytime or only when analysis is sucessfull?
	}
}
