package src.backend.interfaces;

import java.util.HashMap;

import src.backend.concrete.linkedList;

public interface Iterator {
	public boolean hasNext();
	public linkedList next();
	public boolean hasCurrent();
	public HashMap<?,?> current();
}
