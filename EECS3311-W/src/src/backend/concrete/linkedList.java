package src.backend.concrete;

import java.util.LinkedHashMap;

import src.backend.interfaces.Container;
import src.backend.interfaces.Iterator;

/**
 * Implementation of a linkedList with 3 main variables:
 * - LinkedHashMap data to store data for the current node
 * - linkedList next to store a reference to the next node
 * - String name to store the name for the current node
 *
 */
public class linkedList implements Container {
	private LinkedHashMap<?,?> data;
	private linkedList next;
	private String name;
	
	/**
	 * A general constructor that includes current data, current node name and next node reference to be created.	
	 * 
	 * @param data The current node data
	 * @param name Name of the current node
	 * @param next The reference to the next node
	 */
	public linkedList(LinkedHashMap<?,?> data, String name, linkedList next) {
		this.data = data;
		this.next = next;
		this.name = name;
	}
	
	/**
	 * A constructor that makes an empty linkedList to be used later on
	 */
	public linkedList() {
		this.data = null;
		this.next = null;
	}
	
	/**
	 *	Retrieve Iterator for the linkedList type
	 */
	@Override
	public Iterator getIterator() {
		return new linkedListIterator();
	}
	
	/**
	 * Retrieve data of the current node
	 * 
	 * @return LinkedHashMap data of the current node
	 */
	public LinkedHashMap<?,?> getData() {
		return data;
	}
	
	/**
	 * Change the current node data if not null to the new data from argument
	 * 
	 * @param data set data of current node
	 */
	public void setdata(LinkedHashMap<?,?> data) {
		this.data = data;

	}
	/**
	 * Set the reference of the next node of the current node
	 * 
	 * @param next reference to next linkedList node
	 */
	public void setNext(linkedList next) {
		this.next = next;

	}
	
	/**
	 * Retrieve name of the current node
	 * 
	 * @return name of the current node
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set new name for the current node
	 * 
	 * @param name name to be set for the current node
	 */
	public void setName(String name) {
		this.name = name;

	}
	
	/**
	 * An implementation of Iterator specifically for linkedList that will control the flow of the nodes
	 *
	 */
	private class linkedListIterator implements Iterator {
		/**
		 *	Return true if there is a reference to the next node
		 */
		@Override
		public boolean hasNext() {
			if (next != null) {
				return true;
			} 
			return false;
		}
		
		/**
		 *	Return the node reference of the next node
		 */
		@Override
		public linkedList next() {
			if (this.hasNext()) {
				return next;
			}
			return null;
		}
		
		/**
		 *	Return true if the current node has data
		 */
		@Override
		public boolean hasCurrent() {
			if (data != null) {
				return true;
			}
			return false;
		}
		
		/**
		 *	Return data of the current node
		 */
		@Override
		public LinkedHashMap<?,?> current() {
			if (this.hasCurrent()) {
				return data;
			}
			return null;
		}
	}
	
}
