package dataStructures;

public interface SearchableList<E> extends List<E>{
	
	/**
     * Returns the first occurrence of the specified element
     * in the list, if the list contains the element.
     * Otherwise, returns null.
     * @param element - element to be searched
     * @return element in the list, null if not found
     */
    E findElement(E element);  
}
