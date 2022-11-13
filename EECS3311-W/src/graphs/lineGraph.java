package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
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

import src.concrete.linkedList;
import src.interfaces.Iterator;

public class lineGraph implements Graph{

	@Override
	public void update(linkedList dataSets, JPanel west) {
		
		int i = 0; //need to consider naming for the data sets
		XYSeriesCollection datasetDisplay = new XYSeriesCollection();
		XYSeries series;
		
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			HashMap<?,?> dataSet = dataSets.getData();
			series = new XYSeries(i++); //replace with a proper name for the data set
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				series.add((int) temp.getKey(), (double)temp.getValue());
			}
			datasetDisplay.addSeries(series);
			dataSets = (linkedList) dataIterator.next();
		}
		
		JFreeChart chart = ChartFactory.createXYLineChart("Default Title", "Year", "", datasetDisplay,
				PlotOrientation.VERTICAL, true, true, false); //need to consider how to name the graphs, year is the x axis

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
				new TextTitle("Default Title", new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
		

	}

}
