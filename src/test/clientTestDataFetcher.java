package test;

import com.google.gson.JsonArray;

import main.java.model.concrete.Adapter;
import main.java.model.concrete.dataFetcher;

public class clientTestDataFetcher {
	public static void main(String[] args) {
		String country = "can";
		int startDate = 2000;
		int endDate = 2002;
		dataFetcher test = new Adapter(startDate, endDate, country);
		String dataIndicator = "SP.POP.TOTL";
		JsonArray jsonObj = test.getData(dataIndicator);
		System.out.println(jsonObj.toString());
	}
}
