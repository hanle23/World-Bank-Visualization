package src.concrete;

import java.util.HashMap;
import java.util.LinkedHashMap;

import src.interfaces.Container;
import src.interfaces.Iterator;

public class linkedList implements Container {
	private LinkedHashMap<?,?> data;
	private linkedList next;
	
	public linkedList(LinkedHashMap<?,?> data, linkedList next) {
		this.data = data;
		this.next = next;
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
