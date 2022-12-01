package src.backend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import src.backend.concrete.Adapter;
import src.backend.concrete.dataExtractor;
import src.backend.concrete.dataFetcher;

public class TestDataFetcher {
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
}
