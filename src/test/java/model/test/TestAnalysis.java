package test.java.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.model.analyses.CO2EmissionVsGDP;
import main.java.model.analyses.CO2_Energy_PM25;
import main.java.model.analyses.HealthCareVsMortality;
import main.java.model.analyses.avgGovExpenditureOnEd;
import main.java.model.analyses.forestArea;
import main.java.model.analyses.healthVsBeds;
import main.java.model.analyses.infantMortality;
import main.java.model.analyses.problemsAccessingHealthCare;
import main.java.model.concrete.linkedList;

public class TestAnalysis {
	
	//PLEASE NOTE: Many tests will cause a popup message to be displayed. This is to notify the user that a function was given an invalid input
	//PLEASE CLOSE these messages in order for the unit tests to proceed. When a message appears it pauses the tests until it is closed.
	
	@Test
	public void test_avgGovExpenditureOnEd_1() {
		//tests if the data structure associated with the analysis has the right name
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getName(), "Government Expenditure");
	}
	
	@Test
	public void test_avgGovExpenditureOnEd_2() {
		//testing analyzers are successfully storing data in the linkedList
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get("Avg government expenditure on education"), Double.valueOf(4.99308013916016));
	}
	
	@Test
	public void test_avgGovExpenditureOnEd_3() {
		//testing isvalid function within the analysis
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_avgGovExpenditureOnEd_4() {
		//testing to make sure the analysis has a list of appropriate graphs
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_avgGovExpenditureOnEd_5() {
		//testing analyzing behaviour if given invalid input
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
	
	@Test
	public void test_CO2_Energy_PM25_1() {
		//testing analyzers are successfully storing data in the linkedList
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2014, 2021, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2021), Double.valueOf(0));
	}
	
	@Test
	public void test_CO2_Energy_PM25_2() {
		//testing analyzers are successfully storing data in the linkedList
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2014, 2021, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2014), Double.valueOf(-4.661617341617734));
	}
	
	@Test
	public void test_CO2_Energy_PM25_3() {
		//testing isvalid function within the analysis
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_CO2_Energy_PM25_4() {
		//testing to make sure the analysis has a list of appropriate graphs
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_CO2_Energy_PM25_5() {
		//testing when the user selects an excluded year
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_CO2_Energy_PM25_6() {
		//testing analyzing behaviour if given invalid input
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
	
	@Test
	public void test_CO2EmissionVsGDP_1() {
		//testing when the user selects an excluded year
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_CO2EmissionVsGDP_2() {
		//testing analyzers are successfully storing data in the linkedList
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(6.893452108358482E-4));
	}
	
	@Test
	public void test_CO2EmissionVsGDP_3() {
		//testing isvalid function within the analysis
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_CO2EmissionVsGDP_4() {
		//testing to make sure the analysis has a list of appropriate graphs
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_CO2EmissionVsGDP_5() {
		//testing analyzing behaviour if given invalid input
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
	
	@Test
	public void test_forestArea_1() {
		//testing when the user selects an excluded year
		forestArea test = new forestArea(2000, 2004, "CAN");;
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2001), Double.valueOf(0));
	}
	
	@Test
	public void test_forestArea_2() {
		//testing analyzers are successfully storing data in the linkedList
		forestArea test = new forestArea(2000, 2004, "CAN");;
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2003), Double.valueOf(38.7769284564652));
	}
	
	@Test
	public void test_forestArea_3() {
		//testing isvalid function within the analysis
		forestArea test = new forestArea(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
		
	}
	
	@Test
	public void test_forestArea_4() {
		//testing to make sure the analysis has a list of appropriate graphs
		forestArea test = new forestArea(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_forestArea_5() {
		//testing analyzing behaviour if given invalid input
		forestArea test = new forestArea(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
	
	@Test
	public void test_HealthCareVsMortality_1() {
		//testing analyzers are successfully storing data in the linkedList
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2003), Double.valueOf(0));
	}
	
	@Test
	public void test_HealthCareVsMortality_2() {
		//testing when the user selects an excluded year
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2001), Double.valueOf(0));
	}
	
	@Test
	public void test_HealthCareVsMortality_3() {
		//testing isvalid function within the analysis
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_HealthCareVsMortality_4() {
		//testing to make sure the analysis has a list of appropriate graphs
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_HealthCareVsMortality_5() {
		//testing analyzing behaviour if given invalid input
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
	
	@Test
	public void test_healthVsBeds_1() {
		//testing when the user selects an excluded year
		healthVsBeds test = new healthVsBeds(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_healthVsBeds_2() {
		//testing analyzers are successfully storing data in the linkedList
		healthVsBeds test = new healthVsBeds(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2004), Double.valueOf(850310.2147296512));
	}
	
	@Test
	public void test_healthVsBeds_3() {
		//testing isvalid function within the analysis
		healthVsBeds test = new healthVsBeds(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_healthVsBeds_4() {
		//testing to make sure the analysis has a list of appropriate graphs
		healthVsBeds test = new healthVsBeds(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_healthVsBeds_5() {
		//testing analyzing behaviour if given invalid input
		healthVsBeds test = new healthVsBeds(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
	
	@Test
	public void test_infantMortality_1() {
		//testing when the user selects an excluded year
		infantMortality test = new infantMortality(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_infantMortality_2() {
		//testing analyzers are successfully storing data in the linkedList
		infantMortality test = new infantMortality(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(5.3));
	}
	
	@Test
	public void test_infantMortality_3() {
		//testing analyzers are successfully storing data in the linkedList
		infantMortality test = new infantMortality(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2020), null);
	}
	
	@Test
	public void test_infantMortality_4() {
		//testing isvalid function within the analysis
		infantMortality test = new infantMortality(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_infantMortality_5() {
		//testing to make sure the analysis has a list of appropriate graphs
		infantMortality test = new infantMortality(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_infantMortality_6() {
		//testing analyzing behaviour if given invalid input
		infantMortality test = new infantMortality(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
	
	@Test
	public void test_problemsAccessingHealthCare_1() {
		//testing when the user selects an excluded year
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_problemsAccessingHealthCare_2() {
		//testing analyzers are successfully storing data in the linkedList
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(0));
	}
	
	@Test
	public void test_problemsAccessingHealthCarey_3() {
		//testing analyzers are successfully storing data in the linkedList
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2020), null);
	}
	
	@Test
	public void test_problemsAccessingHealthCare_4() {
		//testing isvalid function within the analysis
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_problemsAccessingHealthCare_5() {
		//testing to make sure the analysis has a list of appropriate graphs
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_problemsAccessingHealthCare_6() {
		//testing analyzing behaviour if given invalid input
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(2000, 2004, null);
		assertEquals(test.analyzeData(), null);
	}
}
