package src.backend.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import src.backend.concrete.GeneralGraphTemplate;
import src.backend.concrete.ScaleTo100GraphTemplate;
import src.backend.interfaces.GenerateGraphTemplate;



public class TestGraphTemplate {
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
