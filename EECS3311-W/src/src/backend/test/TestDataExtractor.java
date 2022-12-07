package src.backend.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import src.backend.concrete.Adapter;
import src.backend.concrete.dataExtractor;
import src.backend.concrete.dataFetcher;

public class TestDataExtractor {
	// Testing for specific case of filtering data
	@Test
	public void test_Data_Extractor_1() {
		dataFetcher test = new Adapter(2000, 2023, "can");
		HashMap<Integer, Double> test1 = dataExtractor.filter(test.getData("AG.LND.AGRI.ZS"));
		
		assertEquals(test1.get(2002),Double.valueOf(6.92547841246365));
	}
	// Testing with invalid object to filter
	@Test
	public void test_Data_Extractor_2() {
		HashMap<Integer, Double> test1 = dataExtractor.filter(null);
		assertEquals(test1,null);
	}
	
	
}
