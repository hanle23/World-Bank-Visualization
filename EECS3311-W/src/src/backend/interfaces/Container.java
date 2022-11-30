package src.backend.interfaces;

/**
 * A data structure implementation that includes a support function getIterator to iterator through all
 * value inside the said data structure.
 *
 */
public interface Container {
	/**
	 * Retrieve the Iterator of a specific data structure 
	 * 
	 * @return The Iterator object
	 */
	public Iterator getIterator();
}
