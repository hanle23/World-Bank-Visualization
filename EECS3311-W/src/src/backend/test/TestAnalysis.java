package src.backend.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import src.backend.analyses.CO2EmissionVsGDP;
import src.backend.analyses.CO2_Energy_PM25;
import src.backend.analyses.HealthCareVsMortality;
import src.backend.analyses.avgGovExpenditureOnEd;
import src.backend.analyses.forestArea;
import src.backend.analyses.healthVsBeds;
import src.backend.analyses.infantMortality;
import src.backend.analyses.problemsAccessingHealthCare;
import src.backend.concrete.linkedList;

public class TestAnalysis {
	@Test
	public void test_avgGovExpenditureOnEd_1() {
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getName(), "Government Expenditure");
	}
	
	@Test
	public void test_avgGovExpenditureOnEd_2() {
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get("Avg government expenditure on education"), Double.valueOf(4.99308013916016));
	}
	
	@Test
	public void test_avgGovExpenditureOnEd_3() {
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_avgGovExpenditureOnEd_4() {
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_CO2_Energy_PM25_1() {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2014, 2021, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2021), Double.valueOf(0));
	}
	
	@Test
	public void test_CO2_Energy_PM25_2() {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2014, 2021, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2014), Double.valueOf(-4.661617341617734));
	}
	
	@Test
	public void test_CO2_Energy_PM25_3() {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_CO2_Energy_PM25_4() {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_CO2_Energy_PM25_5() {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_CO2EmissionVsGDP_1() {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_CO2EmissionVsGDP_2() {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(6.893452108358482E-4));
	}
	
	@Test
	public void test_CO2EmissionVsGDP_3() {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_CO2EmissionVsGDP_4() {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_forestArea_1() {
		forestArea test = new forestArea(2000, 2004, "CAN");;
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2001), Double.valueOf(0));
	}
	
	@Test
	public void test_forestArea_2() {
		forestArea test = new forestArea(2000, 2004, "CAN");;
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2003), Double.valueOf(38.7769284564652));
	}
	
	@Test
	public void test_forestArea_3() {
		forestArea test = new forestArea(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
		
	}
	
	@Test
	public void test_forestArea_4() {
		forestArea test = new forestArea(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_HealthCareVsMortality_1() {
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2003), Double.valueOf(0));
	}
	
	@Test
	public void test_HealthCareVsMortality_2() {
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2001), Double.valueOf(0));
	}
	
	@Test
	public void test_HealthCareVsMortality_3() {
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_HealthCareVsMortality_4() {
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_healthVsBeds_1() {
		healthVsBeds test = new healthVsBeds(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_healthVsBeds_2() {
		healthVsBeds test = new healthVsBeds(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2004), Double.valueOf(850310.2147296512));
	}
	
	@Test
	public void test_healthVsBeds_3() {
		healthVsBeds test = new healthVsBeds(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_healthVsBeds_4() {
		healthVsBeds test = new healthVsBeds(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_infantMortality_1() {
		infantMortality test = new infantMortality(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_infantMortality_2() {
		infantMortality test = new infantMortality(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(5.3));
	}
	
	@Test
	public void test_infantMortality_3() {
		infantMortality test = new infantMortality(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2020), null);
	}
	
	@Test
	public void test_infantMortality_4() {
		infantMortality test = new infantMortality(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_infantMortality_5() {
		infantMortality test = new infantMortality(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
	
	@Test
	public void test_problemsAccessingHealthCare_1() {
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void test_problemsAccessingHealthCare_2() {
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(0));
	}
	
	@Test
	public void test_problemsAccessingHealthCarey_3() {
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(1999, 2004, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2020), null);
	}
	
	@Test
	public void test_problemsAccessingHealthCare_4() {
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(2000, 2004, "CAN");
		assertEquals(test.isValid(2004, 2000, "can"), false);
	}
	
	@Test
	public void test_problemsAccessingHealthCare_5() {
		problemsAccessingHealthCare test = new problemsAccessingHealthCare(2000, 2004, "CAN");
		assertEquals(test.getAcceptGraph().size() > 0, true);
	}
}
