package src.graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import src.concrete.linkedList;
import src.interfaces.Iterator;

public class report implements Graph{

	@Override
	public void update(linkedList dataSets, JPanel west) {
		int i = 0;
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage = "Default Title\n" + "==============================\n";
		
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			HashMap<?,?> dataSet = dataSets.getData();
			reportMessage = reportMessage + i + ":\n"; // i is just a place holder for the name of the data set
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				reportMessage = reportMessage + "\t" + temp.getKey() + " => " + temp.getValue() + "\n";
			}
			reportMessage = reportMessage + "\n";
			i++;
			dataSets = (linkedList) dataIterator.next();
		}
		
		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

}
