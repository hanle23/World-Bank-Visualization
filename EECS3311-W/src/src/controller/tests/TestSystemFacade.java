package src.controller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import src.backend.analyses.CO2EmissionVsGDP;
import src.backend.analyses.CO2_Energy_PM25;
import src.backend.analyses.HealthCareVsMortality;
import src.backend.analyses.avgGovExpenditureOnEd;
import src.backend.analyses.forestArea;
import src.backend.analyses.healthVsBeds;
import src.backend.analyses.infantMortality;
import src.backend.analyses.problemsAccessingHealthCare;
import src.controller.concrete.systemFacade;
import src.view.graphs.bar;
import src.view.graphs.lineGraph;
import src.view.graphs.pie;
import src.view.graphs.report;
import src.view.graphs.scatter;
import src.view.graphs.timeSeries;

public class TestSystemFacade {
	@Test
	public void test_system_facade_0() {
		systemFacade test = new systemFacade();
		assertNotEquals(null, test);
	}
	
	@Test
	public void test_system_facade_1() {
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		assertEquals(pie.class, test.getSubject().getGraphs().get("Pie Chart").getClass());
	}
	
	@Test
	public void test_system_facade_2() {
		systemFacade test = new systemFacade();
		test.addGraph("Line Chart");
		assertEquals(lineGraph.class, test.getSubject().getGraphs().get("Line Chart").getClass());
	}
	
	@Test
	public void test_system_facade_3() {
		systemFacade test = new systemFacade();
		test.addGraph("Bar Chart");
		assertEquals(bar.class, test.getSubject().getGraphs().get("Bar Chart").getClass());
	}
	
	@Test
	public void test_system_facade_4() {
		systemFacade test = new systemFacade();
		test.addGraph("Scatter Chart");
		assertEquals(scatter.class, test.getSubject().getGraphs().get("Scatter Chart").getClass());
	}
	
	@Test
	public void test_system_facade_5() {
		systemFacade test = new systemFacade();
		test.addGraph("Report");
		assertEquals(report.class, test.getSubject().getGraphs().get("Report").getClass());
	}
	
	@Test
	public void test_system_facade_6() {
		systemFacade test = new systemFacade();
		test.addGraph("Time Series");
		assertEquals(timeSeries.class, test.getSubject().getGraphs().get("Time Series").getClass());
	}
	
	@Test
	public void test_system_facade_7() {
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		assertEquals(true, test.getSubject().isExist("Pie Chart"));
		test.removeGraph("Pie Chart");
		assertEquals(false, test.getSubject().isExist("Pie Chart"));
	}
	
	@Test
	public void test_system_facade_8() {
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		test.addGraph("Report");
		assertEquals("Pie Chart, Report", test.getSubject().getGraphsAsString());
	}
	
	@Test
	public void test_system_facade_9() {
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		test.addGraph("Report");
		test.addGraph("Line Chart");
		test.addGraph("Bar Chart");
		test.addGraph("Scatter Chart");
		test.addGraph("Time Series");
		assertEquals("Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report", test.getSubject().getGraphsAsString());
		test.addGraph("Bar Chart");
		assertEquals("Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report", test.getSubject().getGraphsAsString());
		test.addGraph(null);
		assertEquals("Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report", test.getSubject().getGraphsAsString());
		test.addGraph("Pie Chart");
		assertEquals("Pie Chart, Line Chart, Bar Chart, Scatter Chart, Time Series, Report", test.getSubject().getGraphsAsString());
		test.removeGraph("Pie Chart");
		assertEquals("Line Chart, Bar Chart, Scatter Chart, Time Series, Report", test.getSubject().getGraphsAsString());
		test.removeGraph("Bar Chart");
		assertEquals("Line Chart, Scatter Chart, Time Series, Report", test.getSubject().getGraphsAsString());
	}
	
	@Test
	public void test_system_facade_10() {
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		assertEquals(true, test.getSubject().isExist("Pie Chart"));
		assertEquals(false, test.getSubject().isExist("Time Chart"));
	}
	
	@Test
	public void test_system_facade_11() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Percent of Forest Area");
		assertEquals(forestArea.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_12() {
		systemFacade test = new systemFacade();
		test.setAnalysis(-1, 2004, "can", "Percent of Forest Area");
		assertEquals(null, test.getAnalysis());
	}
	
	@Test
	public void test_system_facade_13() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, -1, "can", "Percent of Forest Area");
		assertEquals(null, test.getAnalysis());
	}
	
	@Test
	public void test_system_facade_14() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, null, "Percent of Forest Area");
		assertEquals(null, test.getAnalysis());
	}
	
	@Test
	public void test_system_facade_15() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Infant Mortality");
		assertEquals(infantMortality.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_16() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Problems Accessing Health Care");
		assertEquals(problemsAccessingHealthCare.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_17() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Average Government Expenditure on Education");
		assertEquals(avgGovExpenditureOnEd.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_18() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "CO2 Emissions per unit of GDP");
		assertEquals(CO2EmissionVsGDP.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_19() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Problems Accessing Health Care vs Infant Mortality");
		assertEquals(HealthCareVsMortality.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_20() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Percentage change of CO2 Emissions + Energy Use + Air Pollution");
		assertEquals(CO2_Energy_PM25.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_21() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Ratio of Health Expenditure to Hospital Beds");
		assertEquals(healthVsBeds.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_22() {
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Example");
		assertEquals(null, test.getAnalysis());
	}
	
}
