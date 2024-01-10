package main.java.view.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import main.java.model.concrete.linkedList;
import main.java.model.interfaces.Iterator;
import main.java.view.interfaces.Graph;

/**
 * A class responsible for the creation of a line graph
 */
public class lineGraph implements Graph{
	private String title;
	private linkedList dataSet;
	
	/**
	 * Method that creates a graph and populates the data with the analyzed data.
	 * Contains line graph creation algorithm
	 * 
	 * @param dataSets 	a linkedList that contains the data to be displayed
	 * @param west		the JPanel where the graphs are displayed
	 * @param graphTitle	a string for the title of the graph
	 */
	@Override
	public void update(linkedList dataSets, JPanel west, String graphTitle) {
		
		if(dataSets == null || west == null || graphTitle == null)
			return;
		
		title = graphTitle;
		dataSet = dataSets;
		
		XYSeriesCollection datasetDisplay = new XYSeriesCollection();
		XYSeries series;
		
		//populating graph with data from linkedList
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			LinkedHashMap<?,?> dataSet = dataSets.getData();
			series = new XYSeries(dataSets.getName()); //creating a new series for each series of data in the linkedList
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				series.add((int) temp.getKey(), (double)temp.getValue());
			}
			datasetDisplay.addSeries(series);
			dataSets = (linkedList) dataIterator.next();
		}
		
		JFreeChart chart = ChartFactory.createXYLineChart(graphTitle, "Year", "", datasetDisplay,
				PlotOrientation.VERTICAL, true, true, false); 

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle(graphTitle, new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
		

	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public linkedList getDataSet() {
		return dataSet;
	}

}
