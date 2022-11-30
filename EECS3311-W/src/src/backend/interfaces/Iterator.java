package src.backend.interfaces;

import java.util.LinkedHashMap;

import src.backend.concrete.linkedList;

/**
 * Iterator class that loop through all the object that is in a certain data structure
 *
 */
public interface Iterator {
	/**
	 * Checking for the availability of next value
	 *
	 * @return true if next value is not null
	 */
	public boolean hasNext();
	/**
	 * Retrieve the next linkedList node
	 * 
	 * @return the value of the next linkedList node
	 */
	public linkedList next();
	/**
	 * Checking for the availability of the data of the current node
	 * 
	 * @return true if current data is not null
	 */
	public boolean hasCurrent();
	/**
	 * Retrieve the current LinkedHashMap of the current node
	 * 
	 * @return LinkedHashMap data of the current node
	 */
	public LinkedHashMap<?,?> current();
}
