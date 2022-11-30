package src.backend.interfaces;


import java.util.Set;

import src.backend.concrete.linkedList;

/**
 * An interface that is used to implement a specific analysis, usually is created through constructor with 3 parameters similar to 
 * isValid function. Usually included a Set of acceptGraph, dataFetcher for the fetched jsonObject. 
 * The interface use analyzeData function mainly to retrieve the result of the data after being fetched, where
 * each analyzeData is implemented differently
 *
 */
public interface analyses {
	/**
	 * Return the linkedList format of the data after being fetched, transformed, and organized by year. Each node of the linkedList is a set of data in HashMap format
	 * 
	 * @return linkedList format of the data
	 */
	public linkedList analyzeData();
	
	/**
	 * Return a set of graph in String format that includes type of graph that could be used for this certain analysis. 
	 * 
	 * @return a set of graph that support this analysis
	 */
	public Set<String> getAcceptGraph();
	
	/**
	 * Return the result of checking parameters before fetching the data for this particular analysis
	 * 
	 * @param startYear the start year of the target analysis that user want to retrieve
	 * @param endYear the end year of the target analysis that user want to retrieve
	 * @param countryCode The country name in code format of the target country
	 * @return True if every parameters is valid for this particular analysis
	 */
	public boolean isValid(int startYear, int endYear, String countryCode);
}
