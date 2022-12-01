package src.view.graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import src.backend.concrete.linkedList;
import src.backend.interfaces.Iterator;
import src.view.interfaces.Graph;

/**
 * A class responsible for the creation of a report
 */
public class report implements Graph{

	/**
	 * Method that creates a graph and populates the data with the analyzed data.
	 * Contains report creation algorithm
	 * 
	 * @param dataSets 	a linkedList that contains the data to be displayed
	 * @param west		the JPanel where the graphs are displayed
	 * @param graphTitle	a string for the title of the graph
	 */
	@Override
	public void update(linkedList dataSets, JPanel west, String graphTitle) {
		
		if(dataSets == null || west == null || graphTitle == null)
			return;
		
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage = graphTitle + "\n" + "==============================\n";
		//populating the report with data from the linkedList
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			LinkedHashMap<?,?> dataSet = dataSets.getData();
			reportMessage = reportMessage + dataSets.getName() + ":\n";
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				reportMessage = reportMessage + "\t" + temp.getKey() + " => " + temp.getValue() + "\n";
			}
			reportMessage = reportMessage + "\n";
			dataSets = (linkedList) dataIterator.next();
		}
		
		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

}
