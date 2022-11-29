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
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import src.backend.concrete.linkedList;
import src.backend.interfaces.Iterator;
import src.view.interfaces.Graph;

public class scatter implements Graph{

	@Override
	public void update(linkedList dataSets, JPanel west, String graphTitle) {
		XYPlot plot = new XYPlot();
		TimeSeriesCollection dataSetDisplay;
		XYItemRenderer itemrenderer;
		int i = 0;
		String year;
		dataSetDisplay = new TimeSeriesCollection();
		itemrenderer =  new XYLineAndShapeRenderer(false, true);
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			LinkedHashMap<?,?> dataSet = dataSets.getData();
		//	dataSetDisplay = new TimeSeriesCollection();
		//	itemrenderer =  new XYLineAndShapeRenderer(false, true);
			TimeSeries series = new TimeSeries(dataSets.getName());
			
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				
				series.add(new Year((int) temp.getKey()), (double) temp.getValue());
			}
			
			dataSetDisplay.addSeries(series);
		//	plot.setDataset(i, dataSetDisplay);
		//	plot.setRenderer(i, itemrenderer);
		//	plot.setRangeAxis(i, new NumberAxis("temp label")); //put whatever to match the data
			i++;
			dataSets = (linkedList) dataIterator.next();
			
		}
		
		//dataSetDisplay.addSeries(series);
		plot.setDataset(0, dataSetDisplay);
		plot.setRenderer(0, itemrenderer);
		plot.setRangeAxis(0, new NumberAxis(""));
		
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		for(int j = 0; j < i ; j++) {
			//plot.mapDatasetToRangeAxis(j, j);
			plot.mapDatasetToRangeAxis(j, 0);
		}
		
		JFreeChart scatterChart = new JFreeChart(graphTitle,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

}
