package src.view.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Test;

import src.view.concrete.graphSubject;
import src.view.graphs.*;
import src.view.interfaces.Graph;

public class TestGraphSubject {
	
	@Test
	public void test_Graph_Subject_0() {
		graphSubject test = new graphSubject();
		assertNotEquals(test, null);
	}
	@Test
	public void test_Graph_Subject_1() {
		graphSubject test = new graphSubject();
		assertEquals(test.getGraphs().size(), 6);
	}
	
	@Test
	public void test_Graph_Subject_2() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		assertEquals(test.getGraphs().get("Pie Chart").getClass(), pie.class);
	}
	
	@Test
	public void test_Graph_Subject_3() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		test.detachAll();
		assertEquals(test.getGraphs().get("Pie Chart"), null);
	}
	
	@Test
	public void test_Graph_Subject_4() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		test.attach("Report");
		assertEquals(test.getGraphsAsString(), "Pie Chart, Report");
	}
	
	@Test
	public void test_Graph_Subject_5() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		test.attach("Report");
		test.attach("Line Chart");
		test.attach("Bar Chart");
		test.attach("Scatter Chart");
		test.attach("Time Series");
		assertEquals(test.getGraphsAsString(), "Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report");
		test.attach("Ball Chart");
		assertEquals(test.getGraphsAsString(), "Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report");
		test.attach(null);
		assertEquals(test.getGraphsAsString(), "Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report");
		test.attach("Pie Chart");
		assertEquals(test.getGraphsAsString(), "Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report");
		test.detach("Pie Chart");
		assertEquals(test.getGraphsAsString(), "Line Chart, Bar Chart, Scatter Chart, Time Series, Report");
		test.detach("Bar Chart");
		assertEquals(test.getGraphsAsString(), "Line Chart, Scatter Chart, Time Series, Report");
	}
	
	@Test
	public void test_Graph_Subject_6() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		assertEquals(test.isExist("Pie Chart"), true);
		assertEquals(test.isExist("Time Chart"), false);
	}
	
	@Test
	public void test_Graph_Subject_7() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		assertEquals(test.isExist("Pie Chart"), true);
		test.detach("Pie Chart");
		assertEquals(test.isExist("Pie Chart"), false);
	}
	
	@Test
	public void test_Graph_Subject_8() {
		graphSubject test = new graphSubject();
		Graph pie = new pie();
		Graph line = new lineGraph();
		Graph bar = new bar();
		LinkedHashMap<String, Graph> temp = new LinkedHashMap<>();
		temp.put("Pie Chart", pie);
		temp.put("Line Chart", line);
		temp.put("Bar Chart", bar);
		test.setGraphs(temp);
		assertEquals(test.getGraphs(), temp);
	}
}
