package src.view.test;
import src.view.concrete.*;
import static org.junit.Assert.*;
import src.view.graphs.*;
import org.junit.Test;


public class TestGraphFactory {
	// Testing main class object creation
	@Test
	public void test_GraphFactory_1(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph(null), null);
	}
	// Testing object creation on Pie Chart
	@Test
	public void testGraphFactory_2(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Pie Chart").getClass(), pie.class);
	}
	// Testing object creation on Line Chart
	@Test
	public void testGraphFactory_3(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Line Chart").getClass(), lineGraph.class);
	}
	// Testing object creation on Bar Chart
	@Test
	public void testGraphFactory_4(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Bar Chart").getClass(), bar.class);
	}
	//  Testing object creation on Scatter Chart
	@Test
	public void testGraphFactory_5(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Scatter Chart").getClass(), scatter.class);
	}
	
	// Testing object creation on Time Series
	@Test
	public void testGraphFactory_6(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Time Series").getClass(), timeSeries.class);
	}
	
	// Testing object creation on Report
	@Test
	public void testGraphFactory_7(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Report").getClass(), report.class);
	}
	
	// Testing object creation on invalid graph
	@Test
	public void testGraphFactory_8(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Non Exist"), null);
	}

}
