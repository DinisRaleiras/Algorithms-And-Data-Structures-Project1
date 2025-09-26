package dataStructures;

import java.io.Serializable;

public class SearchableListClass<E> extends DoubleList<E> implements SearchableList<E>, Serializable {

	private static final long serialVersionUID = 0L;

	
	public SearchableListClass() {
		super();
	}
	
	@Override
	public E findElement( E element )
    {
        DoubleListNode<E> node = head;
        while ( node != null && !node.getElement().equals(element) )
        {
            node = node.getNext();
        }
        if ( node == null )
            return null;
        else
            return node.getElement();
    }
}
