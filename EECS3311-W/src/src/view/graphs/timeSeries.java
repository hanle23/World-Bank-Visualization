package src.view.graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import src.backend.concrete.linkedList;
import src.backend.interfaces.Iterator;
import src.view.interfaces.Graph;

/**
 * A class responsible for the creation of a time series
 */
public class timeSeries implements Graph{

	/**
	 * Method that creates a graph and populates the data with the analyzed data.
	 * Contains time series creation algorithm
	 * 
	 * @param dataSets 	a linkedList that contains the data to be displayed
	 * @param west		the JPanel where the graphs are displayed
	 * @param graphTitle	a string for the title of the graph
	 */
	@Override
	public void update(linkedList dataSets, JPanel west, String graphTitle) {
		XYPlot plot = new XYPlot();
		TimeSeriesCollection dataSetDisplay;
		XYSplineRenderer splinerenderer;
		int i = 0;
		
		dataSetDisplay = new TimeSeriesCollection();
		splinerenderer = new XYSplineRenderer();
		
		//populating time series with data from linkedList
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			LinkedHashMap<?,?> dataSet = dataSets.getData();
			TimeSeries series = new TimeSeries(dataSets.getName());
			
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				
				series.add(new Year((int) temp.getKey()), (double) temp.getValue());
			}
			
			dataSetDisplay.addSeries(series);
			i++;
			dataSets = (linkedList) dataIterator.next();
			
		}
		
		plot.setDataset(0, dataSetDisplay);
		plot.setRenderer(0, splinerenderer);
		plot.setRangeAxis(0, new NumberAxis(""));
		
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		for(int j = 0; j < i ; j++) {
			plot.mapDatasetToRangeAxis(j, 0);
		}
		
		JFreeChart chart = new JFreeChart(graphTitle,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

}
