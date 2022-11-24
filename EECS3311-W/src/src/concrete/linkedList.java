package src.concrete;

import java.util.HashMap;
import java.util.LinkedHashMap;

import src.interfaces.Container;
import src.interfaces.Iterator;

public class linkedList implements Container {
	private LinkedHashMap<?,?> data;
	private linkedList next;
	private String name;
	
	public linkedList(LinkedHashMap<?,?> data, String name, linkedList next) {
		this.data = data;
		this.next = next;
		this.name = name;
	}
	
	public linkedList() {
		this.data = null;
		this.next = null;
	}
	
	@Override
	public Iterator getIterator() {
		return new linkedListIterator();
	}
	
	public LinkedHashMap<?,?> getData() {
		return data;
	}
	
	public void setdata(LinkedHashMap<?,?> data) {
		this.data = data;

	}
	public void setNext(linkedList next) {
		this.next = next;

	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;

	}
	
	private class linkedListIterator implements Iterator {
		@Override
		public boolean hasNext() {
			if (next != null) {
				return true;
			} 
			return false;
		}
		
		@Override
		public linkedList next() {
			if (this.hasNext()) {
				return next;
			}
			return null;
		}
		
		@Override
		public boolean hasCurrent() {
			if (data != null) {
				return true;
			}
			return false;
		}
		
		@Override
		public LinkedHashMap<?,?> current() {
			if (this.hasCurrent()) {
				return data;
			}
			return null;
		}
	}
	
}
