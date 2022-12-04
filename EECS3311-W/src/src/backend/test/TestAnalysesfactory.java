package src.backend.test;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import src.backend.concrete.*;
import src.backend.analyses.*;
import src.backend.interfaces.*;

public class TestAnalysesfactory {
	@Test
	public void test_Analyses_Factory_1() {
		AnalysisFactory test = new AnalysisFactory();
		assertEquals(test.getAnalysis(0, 0, null, null), null);
	}
	
	@Test
	public void test_Analyses_Factory_2() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Percent of Forest Area");
		assertEquals(a.getClass(), forestArea.class);
	}
	
	@Test
	public void test_Analyses_Factory_3() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(-1, 2004, "can", "Percent of Forest Area");
		assertEquals(a, null);
	}
	
	@Test
	public void test_Analyses_Factory_4() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, -1, "can", "Percent of Forest Area");
		assertEquals(a, null);
	}
	
	@Test
	public void test_Analyses_Factory_5() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, null, "Percent of Forest Area");
		assertEquals(a, null);
	}
	
	@Test
	public void test_Analyses_Factory_6() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Infant Mortality");
		assertEquals(a.getClass(), infantMortality.class);
	}
	
	@Test
	public void test_Analyses_Factory_7() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Problems Accessing Health Care");
		assertEquals(a.getClass(), problemsAccessingHealthCare.class);
	}
	
	@Test
	public void test_Analyses_Factory_8() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Average Government Expenditure on Education");
		assertEquals(a.getClass(), avgGovExpenditureOnEd.class);
	}
	
	@Test
	public void test_Analyses_Factory_9() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "CO2 Emissions per unit of GDP");
		assertEquals(a.getClass(), CO2EmissionVsGDP.class);
	}
	
	@Test
	public void test_Analyses_Factory_10() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Problems Accessing Health Care vs Infant Mortality");
		assertEquals(a.getClass(), HealthCareVsMortality.class);
	}
	
	@Test
	public void test_Analyses_Factory_11() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Percentage change of CO2 Emissions + Energy Use + Air Pollution");
		assertEquals(a.getClass(), CO2_Energy_PM25.class);
	}
	
	@Test
	public void test_Analyses_Factory_12() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Ratio of Health Expenditure to Hospital Beds");
		assertEquals(a.getClass(), healthVsBeds.class);
	}
	
	@Test
	public void test_Analyses_Factory_13() {
		AnalysisFactory test = new AnalysisFactory();
		analyses a = test.getAnalysis(2000, 2004, "can", "Example");
		assertEquals(a, null);
	}
}
