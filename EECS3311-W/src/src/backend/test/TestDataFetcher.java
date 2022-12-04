package src.backend.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import src.backend.concrete.Adapter;

public class TestDataFetcher {
	@Test
	public void test_Data_Fetcher_1() {
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("Canada"), "CAN");
	}
	
	@Test
	public void test_Data_Fetcher_2() {
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("Bolivia"), "BOL");
	}
	
	@Test
	public void test_Data_Fetcher_3() {	
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("xyz"), null);
	}
	
	@Test
	public void test_Data_Fetcher_4() {	
		Adapter test = new Adapter(2000, 1999, "can");
		assertEquals(test.getEndDate(), -1);
	}
	
	@Test
	public void test_Data_Fetcher_5() {	
		Adapter test = new Adapter();
		assertEquals(test.getEndDate(), -1);
		assertEquals(test.getStartDate(), -1);
		assertEquals(test.getCountry(), null);
	}
	
	@Test
	public void test_Data_Fetcher_6() {	
		Adapter test = new Adapter();
		assertEquals(test.getEndDate(), -1);
		assertEquals(test.getStartDate(), -1);
		test.setStartDate(2000);
		test.setEndDate(2004);
		assertEquals(test.getStartDate(), 2000);
		assertEquals(test.getEndDate(), 2004);
	}
	
	@Test
	public void test_Data_Fetcher_7() {	
		Adapter test = new Adapter();
		assertEquals(test.getEndDate(), -1);
		assertEquals(test.getStartDate(), -1);
		test.setStartDate(2000);
		test.setEndDate(1999);
		assertEquals(test.getStartDate(), 2000);
		assertEquals(test.getEndDate(), -1);
	}
	
	@Test
	public void test_Data_Fetcher_8() {	
		Adapter test = new Adapter();
		test.setCountry("can");
		assertEquals(test.getCountry(), "can");
	}
}
