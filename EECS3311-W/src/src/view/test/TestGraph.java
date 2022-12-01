package src.view.test;
import org.junit.jupiter.api.Test;
import src.view.concrete.*;
import static org.junit.Assert.assertEquals;
import src.view.graphs.pie;
import java.util.HashMap;


public class TestGraph {
	@Test
	public void testGraphFactory1(){
		GraphFactory test = new GraphFactory();
		
		assertEquals(test.getGraph(null), null);
	}
	
	@Test
	public void testGraphFactory2(){
		GraphFactory test = new GraphFactory();
		assertEquals(test.getGraph("Pie Chart").getClass(), pie.class);
	}
	
	@Test
	public void testGraphSubject1() {
		graphSubject test = new graphSubject();
		assertEquals(test.getGraphs().size(), 6);
	}
	
	@Test
	public void testGraphSubject2() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		assertEquals(test.getGraphs().get("Pie Chart").getClass(), pie.class);
	}
	
	@Test
	public void testGraphSubject3() {
		graphSubject test = new graphSubject();
		test.attach("Pie Chart");
		test.detachAll();
		assertEquals(test.getGraphs().get("Pie Chart"), null);
	}
}
