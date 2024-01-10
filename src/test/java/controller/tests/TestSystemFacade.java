package test.java.controller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import main.java.controller.concrete.systemFacade;
import main.java.model.analyses.CO2EmissionVsGDP;
import main.java.model.analyses.CO2_Energy_PM25;
import main.java.model.analyses.HealthCareVsMortality;
import main.java.model.analyses.avgGovExpenditureOnEd;
import main.java.model.analyses.forestArea;
import main.java.model.analyses.healthVsBeds;
import main.java.model.analyses.infantMortality;
import main.java.model.analyses.problemsAccessingHealthCare;
import main.java.view.graphs.bar;
import main.java.view.graphs.lineGraph;
import main.java.view.graphs.pie;
import main.java.view.graphs.report;
import main.java.view.graphs.scatter;
import main.java.view.graphs.timeSeries;

public class TestSystemFacade {
	
	//PLEASE NOTE: Many tests will cause a popup message to be displayed. This is to notify the user that a graph was added/removed or given wrong input for some functions
	//PLEASE CLOSE these messages in order for the unit tests to proceed. When a message appears it pauses the tests until it is closed.
	
	@Test
	public void test_system_facade_0() {
		systemFacade test = new systemFacade();
		assertNotEquals(null, test);
	}
	
	@Test
	public void test_system_facade_1() {
		//testing facades interaction with Subject class. Testing if addGraph function is working 
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		assertEquals(pie.class, test.getSubject().getGraphs().get("Pie Chart").getClass());
	}
	
	@Test
	public void test_system_facade_2() {
		//testing facades interaction with Subject class. Testing if addGraph function is working 
		systemFacade test = new systemFacade();
		test.addGraph("Line Chart");
		assertEquals(lineGraph.class, test.getSubject().getGraphs().get("Line Chart").getClass());
	}
	
	@Test
	public void test_system_facade_3() {
		//testing facades interaction with Subject class. Testing if addGraph function is working 
		systemFacade test = new systemFacade();
		test.addGraph("Bar Chart");
		assertEquals(bar.class, test.getSubject().getGraphs().get("Bar Chart").getClass());
	}
	
	@Test
	public void test_system_facade_4() {
		//testing facades interaction with Subject class. Testing if addGraph function is working 
		systemFacade test = new systemFacade();
		test.addGraph("Scatter Chart");
		assertEquals(scatter.class, test.getSubject().getGraphs().get("Scatter Chart").getClass());
	}
	
	@Test
	public void test_system_facade_5() {
		//testing facades interaction with Subject class. Testing if addGraph function is working 
		systemFacade test = new systemFacade();
		test.addGraph("Report");
		assertEquals(report.class, test.getSubject().getGraphs().get("Report").getClass());
	}
	
	@Test
	public void test_system_facade_6() {
		//testing facades interaction with Subject class. Testing if addGraph function is working 
		systemFacade test = new systemFacade();
		test.addGraph("Time Series");
		assertEquals(timeSeries.class, test.getSubject().getGraphs().get("Time Series").getClass());
	}
	
	@Test
	public void test_system_facade_7() {
		//testing facades interaction with Subject class. Testing if addGraph if you add then remove the same graph
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		assertEquals(true, test.getSubject().isExist("Pie Chart"));
		test.removeGraph("Pie Chart");
		assertEquals(false, test.getSubject().isExist("Pie Chart"));
	}
	
	@Test
	public void test_system_facade_8() {
		//testing facades interaction with Subject class. Testing if addGraph function is working by adding two graphs
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		test.addGraph("Report");
		assertEquals("Pie Chart, Report", test.getSubject().getGraphsAsString());
	}
	
	@Test
	public void test_system_facade_9() {
		//testing facades interaction with Subject class. Testing if addGraph function is working by doing a series of add and removes
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
		//testing facades interaction with Subject class. Testing to make sure a graph that was not created doesn't exist
		systemFacade test = new systemFacade();
		test.addGraph("Pie Chart");
		assertEquals(true, test.getSubject().isExist("Pie Chart"));
		assertEquals(false, test.getSubject().isExist("Time Chart"));
	}
	
	@Test
	public void test_system_facade_11() {
		//testing facades interaction with analysisFactory class. Testing creation of forestArea class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Percent of Forest Area");
		assertEquals(forestArea.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_12() {
		//testing facades interaction with analysisFactory class when given invalid input
		systemFacade test = new systemFacade();
		test.setAnalysis(-1, 2004, "can", "Percent of Forest Area");
		assertEquals(null, test.getAnalysis());
	}
	
	@Test
	public void test_system_facade_13() {
		//testing facades interaction with analysisFactory class when given invalid input
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, -1, "can", "Percent of Forest Area");
		assertEquals(null, test.getAnalysis());
	}
	
	@Test
	public void test_system_facade_14() {
		//testing facades interaction with analysisFactory class when given invalid input
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, null, "Percent of Forest Area");
		assertEquals(null, test.getAnalysis());
	}
	
	@Test
	public void test_system_facade_15() {
		//testing facades interaction with analysisFactory class. Testing creation of infantMortality class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Infant Mortality");
		assertEquals(infantMortality.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_16() {
		//testing facades interaction with analysisFactory class. Testing creation of problemsAccessingHealthCare class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Problems Accessing Health Care");
		assertEquals(problemsAccessingHealthCare.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_17() {
		//testing facades interaction with analysisFactory class. Testing creation of avgGovExpenditureOnEd class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Average Government Expenditure on Education");
		assertEquals(avgGovExpenditureOnEd.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_18() {
		//testing facades interaction with analysisFactory class. Testing creation of CO2EmissionVsGDP class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "CO2 Emissions per unit of GDP");
		assertEquals(CO2EmissionVsGDP.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_19() {
		//testing facades interaction with analysisFactory class. Testing creation of HealthCareVsMortality class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Problems Accessing Health Care vs Infant Mortality");
		assertEquals(HealthCareVsMortality.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_20() {
		//testing facades interaction with analysisFactory class. Testing creation of CO2_Energy_PM25 class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Percentage change of CO2 Emissions + Energy Use + Air Pollution");
		assertEquals(CO2_Energy_PM25.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_21() {
		//testing facades interaction with analysisFactory class. Testing creation of healthVsBeds class
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Ratio of Health Expenditure to Hospital Beds");
		assertEquals(healthVsBeds.class, test.getAnalysis().getClass());
	}
	
	@Test
	public void test_system_facade_22() {
		//testing facades interaction with analysisFactory class when given invalid input
		systemFacade test = new systemFacade();
		test.setAnalysis(2000, 2004, "can", "Example");
		assertEquals(null, test.getAnalysis());
	}
	
}
