package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import src.*;
import src.backend.analyses.CO2EmissionVsGDP;
import src.backend.analyses.CO2_Energy_PM25;
import src.backend.analyses.HealthCareVsMortality;
import src.backend.analyses.avgGovExpenditureOnEd;
import src.backend.analyses.forestArea;
import src.backend.analyses.healthVsBeds;
import src.backend.analyses.infantMortality;
import src.backend.concrete.Adapter;
import src.backend.concrete.GeneralGraphTemplate;
import src.backend.concrete.dataExtractor;
import src.backend.concrete.dataFetcher;
import src.backend.concrete.linkedList;
import src.backend.interfaces.Iterator;
import src.backend.interfaces.analyses;

public class TestProject {
	@Test
	public void testLogin1(){
		loginTester test = new loginTester();
		test.loginTest("xyz","123");
		assertEquals(test.isAuthenticated, false);
		
	}
	
	@Test
	public void testLogin2(){
		loginTester test = new loginTester();
		test.loginTest("admin","adminPassword");
		assertEquals(test.isAuthenticated, true);
		
	}
	
	@Test
	public void testLogin3(){
		loginTester test = new loginTester();
		test.loginTest("   admin ","   adminPassword ");
		assertEquals(test.isAuthenticated, true);
		
	}
	
	@Test
	public void testDataFetcher1() {
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("Canada"), "CAN");
	}
	
	@Test
	public void testDataFetcher2() {
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("Bolivia"), "BOL");
	}
	
	@Test
	public void testDataFetcher3() {	
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("xyz"), null);
	}
	
	@Test
	public void testDataExtractor1() {
		dataFetcher test = new Adapter(2000, 2023, "can");
		HashMap<Integer, Double> test1 = dataExtractor.filter(test.getData("AG.LND.AGRI.ZS"));
		
		assertEquals(test1.get(2002),Double.valueOf(6.92547841246365));
	}
	
	@Test
	public void testDataExtractor2() {
		try {
			dataFetcher test = new Adapter(2000, 2023, "xyz");
			HashMap<Integer, Double> test1 = dataExtractor.filter(test.getData("AG.LND.AGRI.ZS"));
			fail();
		}
		
		catch(Exception e){
			assertNotNull(e);
		}
		
	}
	
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
