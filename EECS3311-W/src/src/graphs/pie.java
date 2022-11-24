package src.graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import src.concrete.linkedList;
import src.interfaces.Iterator;

public class pie implements Graph{

	@Override
	public void update(linkedList dataSets, JPanel west, String graphTitle) {
		DefaultCategoryDataset datasetCollection = new DefaultCategoryDataset();
		
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			LinkedHashMap<?,?> dataSet = dataSets.getData();
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				datasetCollection.addValue((double)temp.getValue(), (String) temp.getKey(), ""); 
			}
			dataSets = (linkedList) dataIterator.next();
		}
		
		JFreeChart pieChart = ChartFactory.createMultiplePieChart(graphTitle, datasetCollection,
				TableOrder.BY_COLUMN, true, true, false);

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

}
