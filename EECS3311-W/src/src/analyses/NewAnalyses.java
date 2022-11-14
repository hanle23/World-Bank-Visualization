package src.analyses;

import src.util;
import src.concrete.linkedList;
import src.fetcher.Adapter;
import src.interfaces.analyses;

public class NewAnalyses implements analyses {
	private Adapter jsonObject;
	public NewAnalyses(int startYear, int endYear, String countryCode) {
		if (isValid(startYear, endYear, countryCode)) {
			this.jsonObject = new Adapter(startYear, endYear, countryCode);
		}
	}
	@Override
	public linkedList analyzeData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isValid(int startYear, int endYear, String countryCode) {
		boolean result = true;
		if (endYear < startYear) {
			return false;
		}
		if (!util.COUNTRIES.contains(countryCode)) {
			return false;
		}
		return result;
	}

}
