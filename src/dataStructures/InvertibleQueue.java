package dataStructures;

import java.io.Serializable;

/**
 * InvertibleQueue Abstract Data Type 
 * Includes description of general methods for InvertibleQueue with FIFO discipline.
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public interface InvertibleQueue<E> extends Queue<E>, Serializable{
	
	/*Puts all elements in the queue in the opposite order.
	 *Complexity: BestCase - O(1) WorstCase - O(1) ExpectedCase - O(1)
	 */
	void invert();
}
