package src.backend.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;

import src.backend.concrete.adapteeDataFetcher;


public class TestAdapteeDataFetcher {
	@Test
	public void test_Adaptee_Data_Fetcher_1() {
		adapteeDataFetcher test = new adapteeDataFetcher();
		assertNotEquals(test, null);
	}
	
	@Test
	public void test_Adaptee_Data_Fetcher_2() {
		adapteeDataFetcher test = new adapteeDataFetcher();
		JsonArray data = test.specificGetData("can", "AG.LND.AGRI.ZS", 2000, 2004);
		assertNotEquals(data.size(), 0);
		assertNotEquals(data, null);
	}
	
	@Test
	public void test_Adaptee_Data_Fetcher_3() {
		adapteeDataFetcher test = new adapteeDataFetcher();
		JsonArray data = test.specificGetData("can", "AG.LND.AGRI.ZS", -1, 2004);
		assertEquals(data, null);
		data = test.specificGetData("can", "AG.LND.AGRI.ZS", 2000, -1);
		assertEquals(data, null);
		data = test.specificGetData("can", "AG.LND.AGRI.ZS", 1500, 1500);
		assertEquals(data, null);
	}
	
	@Test
	public void test_Adaptee_Data_Fetcher_4() {
		adapteeDataFetcher test = new adapteeDataFetcher();
		JsonArray data = test.specificGetData("can", "AG.LND.AGRI.ZS", 1, -200);
		assertEquals(data, null);
	}
	
	@Test
	public void test_Adaptee_Data_Fetcher_5() {
		adapteeDataFetcher test = new adapteeDataFetcher();
		LinkedHashMap<String, String> data = test.specificCountryCodeGetData();
		assertNotEquals(data, null);
		assertNotEquals(data.size(), 0);
	}
	
}
