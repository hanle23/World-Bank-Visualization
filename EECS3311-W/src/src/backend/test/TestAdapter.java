package src.backend.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;

import src.backend.concrete.Adapter;

public class TestAdapter {
	// Testing object creation 1 
	@Test
	public void test_Data_Fetcher_1() {
		Adapter test = new Adapter(2000, 2023, "can");
		assertNotEquals(test, null);
	}
	// Testing object creation 2
	@Test
	public void test_Data_Fetcher_2() {
		Adapter test = new Adapter();
		assertNotEquals(test, null);
	}
	// Testing fetching valid data from Adapter directly
	@Test
	public void test_Data_Fetcher_3() {
		Adapter test = new Adapter(2000, 2023, "can");
		JsonArray data = test.getData("AG.LND.AGRI.ZS");
		assertNotEquals(data, null);
	}
	// Testing with Adapter with null attributes
	@Test
	public void test_Data_Fetcher_4() {
		Adapter test = new Adapter();
		JsonArray data = test.getData("AG.LND.AGRI.ZS");
		assertEquals(data, null);
	}
	// Testing with invalid parameters 1
	@Test
	public void test_Data_Fetcher_5() {
		Adapter test = new Adapter(-1, 2004, "can");
		JsonArray data = test.getData("AG.LND.AGRI.ZS");
		assertEquals(data, null);
	}
	// Testing with invalid parameters 2
	@Test
	public void test_Data_Fetcher_6() {
		Adapter test = new Adapter(2000, -1, "can");
		JsonArray data = test.getData("AG.LND.AGRI.ZS");
		assertEquals(data, null);
	}
	// Testing with invalid parameters 3
	@Test
	public void test_Data_Fetcher_7() {
		Adapter test = new Adapter(2000, 2004, null);
		JsonArray data = test.getData("AG.LND.AGRI.ZS");
		assertEquals(data, null);
	}
	// Testing for getting countries code
	@Test
	public void test_Data_Fetcher_8() {
		Adapter test = new Adapter();
		LinkedHashMap<String, String> code = test.getCountriesCode();
		assertNotEquals(code, null);
	}
}
