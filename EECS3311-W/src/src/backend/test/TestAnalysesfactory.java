package src.backend.test;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import src.backend.concrete.*;
import src.backend.analyses.*;
import src.backend.interfaces.*;

public class TestAnalysesfactory {
	@Test
	public void testAnalysesFactory1() {
		AnalysisFactory test = new AnalysisFactory();
		
		assertEquals(test.getAnalysis(0, 0, null, null), null);
	}
	
	@Test
	public void testAnalysesFactory2() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Percent of Forest Area");
		
		assertEquals(a.getClass(), forestArea.class);
	}
	
}
