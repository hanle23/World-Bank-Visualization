package src.backend.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import src.backend.concrete.Adapter;

public class TestDataFetcher {
	// Testing with getting country code from country full name 1
	@Test
	public void test_Data_Fetcher_1() {
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("Canada"), "CAN");
	}
	// Testing with getting country code from country full name 1
	@Test
	public void test_Data_Fetcher_2() {
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("Bolivia"), "BOL");
	}
	// Testing with invalid country name
	@Test
	public void test_Data_Fetcher_3() {	
		Adapter test = new Adapter(2000, 2023, "can");
		LinkedHashMap<String, String> countries = test.getCountriesCode();
		assertEquals(countries.get("xyz"), null);
	}
	// Testing on invalid end date
	@Test
	public void test_Data_Fetcher_4() {	
		Adapter test = new Adapter(2000, 1999, "can");
		assertEquals(test.getEndDate(), -1);
	}
	// Testing object without parameters
	@Test
	public void test_Data_Fetcher_5() {	
		Adapter test = new Adapter();
		assertEquals(test.getEndDate(), -1);
		assertEquals(test.getStartDate(), -1);
		assertEquals(test.getCountry(), null);
	}
	// Testing with set start and end date with valid parameters
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
	// Testing with set end date with invalid parameters
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
	// Testing set country with valid parameters
	@Test
	public void test_Data_Fetcher_8() {	
		Adapter test = new Adapter();
		test.setCountry("can");
		assertEquals(test.getCountry(), "can");
	}
}
