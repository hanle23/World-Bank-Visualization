package src.interfaces;

import java.util.HashMap;

import src.concrete.linkedList;

public interface Iterator {
	public boolean hasNext();
	public linkedList next();
	public boolean hasCurrent();
	public HashMap<?,?> current();
}
