package src.graphs;

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

import src.concrete.linkedList;
import src.interfaces.Iterator;

public class timeSeries implements Graph{

	@Override
	public void update(linkedList dataSets, JPanel west, String graphTitle) {
		XYPlot plot = new XYPlot();
		TimeSeriesCollection dataSetDisplay;
		XYSplineRenderer splinerenderer;
		int i = 0;
		String year;
		
		dataSetDisplay = new TimeSeriesCollection();
		splinerenderer = new XYSplineRenderer();
		
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			LinkedHashMap<?,?> dataSet = dataSets.getData();
			//dataSetDisplay = new TimeSeriesCollection();
			//splinerenderer = new XYSplineRenderer();
			TimeSeries series = new TimeSeries(i);
			
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				
				series.add(new Year((int) temp.getKey()), (double) temp.getValue());
			}
			
			dataSetDisplay.addSeries(series);
			//plot.setDataset(i, dataSetDisplay);
			//plot.setRenderer(i, splinerenderer);
			//plot.setRangeAxis(i, new NumberAxis("temp label")); //put whatever to match the data
			i++;
			dataSets = (linkedList) dataIterator.next();
			
		}
		
		plot.setDataset(0, dataSetDisplay);
		plot.setRenderer(0, splinerenderer);
		plot.setRangeAxis(0, new NumberAxis("temp label")); //put whatever to match the data
		
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
