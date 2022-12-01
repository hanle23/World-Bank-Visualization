package src.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import src.backend.analyses.CO2EmissionVsGDP;
import src.backend.analyses.CO2_Energy_PM25;
import src.backend.analyses.HealthCareVsMortality;
import src.backend.analyses.avgGovExpenditureOnEd;
import src.backend.analyses.forestArea;
import src.backend.analyses.healthVsBeds;
import src.backend.analyses.infantMortality;
import src.backend.concrete.linkedList;

public class TestAnalysis {
	@Test
	public void testAnalyses1() {
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getName(), "Government Expenditure");
	}
	
	@Test
	public void testAnalyses2() {
		avgGovExpenditureOnEd test = new avgGovExpenditureOnEd(2000, 2004, "can");
		linkedList data = test.analyzeData();
		
		assertEquals(data.getData().get("Avg government expenditure on education"), Double.valueOf(4.99308013916016));
	}
	
	@Test
	public void testAnalyses3() {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2014, 2021, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2021), Double.valueOf(0));
	}
	
	@Test
	public void testAnalyses4() {
		CO2_Energy_PM25 test = new CO2_Energy_PM25(2014, 2021, "CAN");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2014), Double.valueOf(-4.661617341617734));
	}
	
	@Test
	public void testAnalyses5() {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void testAnalyses6() {
		CO2EmissionVsGDP test = new CO2EmissionVsGDP(2000, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(6.893452108358482E-4));
	}
	
	@Test
	public void testAnalyses7() {
		forestArea test = new forestArea(2000, 2004, "can");;
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2001), Double.valueOf(0));
	}
	
	@Test
	public void testAnalyses8() {
		forestArea test = new forestArea(2000, 2004, "can");;
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2003), Double.valueOf(38.7769284564652));
	}
	
	@Test
	public void testAnalyses9() {
		HealthCareVsMortality test = new HealthCareVsMortality(2000, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2003), Double.valueOf(0));
	}
	
	@Test
	public void testAnalyses10() {
		healthVsBeds test = new healthVsBeds(1999, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void testAnalyses11() {
		healthVsBeds test = new healthVsBeds(1999, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2004), Double.valueOf(850310.2147296512));
	}
	
	@Test
	public void testAnalyses12() {
		infantMortality test = new infantMortality(1999, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2000), Double.valueOf(0));
	}
	
	@Test
	public void testAnalyses13() {
		infantMortality test = new infantMortality(1999, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2002), Double.valueOf(5.3));
	}
	
	@Test
	public void testAnalyses14() {
		infantMortality test = new infantMortality(1999, 2004, "can");
		linkedList data = test.analyzeData();
		assertEquals(data.getData().get(2020), null);
	}
}
