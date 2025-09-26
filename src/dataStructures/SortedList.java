package dataStructures;

/**
 * SortedList (sequence) Abstract Data Type 
 * Includes description of general methods to be implemented by Sortedlists.
 * @author Filipe Nobre - 67850
 * @author Dinis Raleiras - 67819
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public interface SortedList<E extends Comparable<E>>{
	
	/**
     * Returns true iff the list contains no elements.
     * @return true if list is empty
     */
    boolean isEmpty( );

    /**
     * Returns the number of elements in the list.
     * @return number of elements in the list
     */
    int size( );
    
    /**
     *  Returns an iterator of the elements in the list (in proper sequence).
     * @return Iterator of the elements in the list
     */
    Iterator<E> iterator( );
    
    /**
     * Returns the minimal element of the list.
     * @return first element in the list
     * @throws EmptyListException - if size() == 0
     */
    E getMin( ) throws EmptyListException;
    
    /**
     * Returns the last element of the list.
     * @return last element in the list
     * @throws EmptyListException - if size() == 0
     */
    E getMax( ) throws EmptyListException;
    
    /**
     * Returns the element at the specified position in the list.
     * Range of valid positions: 0, ..., size()-1.
     * If the specified position is 0, get corresponds to getFirst. 
     * If the specified position is size()-1, get corresponds to getLast.    
     * @param position - position of element to be returned
     * @return element at position
     * @throws InvalidPositionException if position is not valid in the list
     */
    E get( int position ) throws InvalidPositionException;
    
    /**
     * Returns the element of the first occurrence of the specified element
     * in the list, if the list contains the element.
     * Otherwise, returns null.
     * @param element - element to be searched in list
     * @return element of the first occurrence of the element in the list (or null)
     */
    E find( E element );
    
    /**
     * Inserts the specified element the list.
     * @param element - element to be inserted
     */
    void add( E element );
    
    /**
     * Removes and returns the minimal element in the list.
     * @return element removed from the first position of the list
     * @throws EmptyListException - if size() == 0
     */
    E removeMin( ) throws EmptyListException;
    
    /**
     * Removes and returns the maximal element in the list.
     * @return element removed from the last position of the list
     * @throws EmptyListException - if size() == 0
     */
    E removeMax( ) throws EmptyListException;
    
    /**
     * Removes the first occurrence of the specified element from the list
     * and returns true, if the list contains the element.
     * Otherwise, returns false.
     * @param element - element to be removed from list
     * @return true if the remove was successful, false otherwise
     */
    E remove( E element );
}
