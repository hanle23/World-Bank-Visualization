package src.backend.interfaces;

import java.util.LinkedHashMap;

import src.backend.concrete.linkedList;

public interface Iterator {
	public boolean hasNext();
	public linkedList next();
	public boolean hasCurrent();
	public LinkedHashMap<?,?> current();
}
