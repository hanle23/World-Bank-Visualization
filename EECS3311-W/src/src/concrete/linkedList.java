package src.concrete;

import java.util.HashMap;

import src.interfaces.Container;
import src.interfaces.Iterator;

public class linkedList implements Container {
	private HashMap<?,?> data;
	private HashMap<?,?> next;
	@Override
	public Iterator getIterator() {
		return new hashMapIterator();
	}
	
	public HashMap<?,?> getData() {
		return data;
	}
	
	public void setdata(HashMap<?,?> data) {
		if (data == null) {
			this.data = data;
		}
	}
	public void setNext(HashMap<?,?> next) {
		if (next == null) {
			this.next = next;
		}
	}
	
	private class hashMapIterator implements Iterator {
		@Override
		public boolean hasNext() {
			if (next != null) {
				return true;
			} 
			return false;
		}
		
		@Override
		public Object next() {
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
		public Object current() {
			if (this.hasCurrent()) {
				return data;
			}
			return null;
		}
	}
	
}
