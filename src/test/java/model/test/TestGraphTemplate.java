package test.java.model.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import main.java.model.concrete.GeneralGraphTemplate;
import main.java.model.concrete.ScaleTo100GraphTemplate;
import main.java.model.interfaces.GenerateGraphTemplate;



public class TestGraphTemplate {
	// Testing result from General Graph Template
	@Test
	public void test_Graph_Template_1() {
		GenerateGraphTemplate template = new GeneralGraphTemplate();
		Set<String> result = template.getTemplate();
		assertNotEquals(result, null);
		assertEquals(result.size(), 5);
		assertEquals(result.contains("Line Chart"), true);
		assertEquals(result.contains("Bar Chart"), true);
		assertEquals(result.contains("Scatter Chart"), true);
		assertEquals(result.contains("Time Series"), true);
		assertEquals(result.contains("Report"), true);
		assertNotEquals(result.contains("Pie Chart"), true);
	}
	// Testing result from Scale to 100 graph template
	@Test
	public void test_Graph_Template_2() {
		GenerateGraphTemplate template = new ScaleTo100GraphTemplate();
		Set<String> result = template.getTemplate();
		assertNotEquals(result, null);
		assertEquals(result.size(), 3);
		assertEquals(result.contains("Pie Chart"), true);
		assertEquals(result.contains("Bar Chart"), true);
		assertEquals(result.contains("Report"), true);
		assertNotEquals(result.contains("Time Series"), true);
	}
}
