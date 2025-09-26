package dataStructures;

import dataStructures.DoubleList.DoubleListNode;

public class DictionaryInList<K,V> implements Dictionary<K,V>{
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
    
    /**
     * Stores the entry head of the dictionary 
     */
    protected DoubleListNode<Entry<K,V>> head;
    
    /**
     * Stores the entry tail of the dictionary 
     */
    protected DoubleListNode<Entry<K,V>> tail;
    
    /**
     * Stores the number of entries in the dictionary 
     */
    protected int currentSize;
    
    /**
     * Constructor create an empty Dictionary.
     */
    public DictionaryInList( ) {
        head = null;
        tail = null;
        currentSize = 0;
    }

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public V find(K key) {
		DoubleListNode<Entry<K,V>> node = this.findNode(key);
		if(node == null) {
			return null;
		}else {
			return node.getElement().getValue();
		}
	}

	@Override
	public V insert(K key, V value) {
		DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(new EntryClass<>(key, value));
		DoubleListNode<Entry<K,V>> oldNode = this.findNode(key);
		if(oldNode == null) {
			if(tail == null) {
				head = newNode;
				tail = newNode;
			}else {
				tail.setNext(newNode);
				newNode.setPrevious(tail);
				tail = newNode;
			}
			currentSize++;
			return null;
		}else {
			DoubleListNode<Entry<K,V>> previousNode = oldNode.getPrevious();
			DoubleListNode<Entry<K,V>> nextNode = oldNode.getNext();
			if(previousNode == null && nextNode == null) {
				head = newNode;
				tail = newNode;
			}else if(previousNode == null) {
				nextNode.setPrevious(newNode);
				newNode.setNext(nextNode);
				head = newNode;
			}else if(nextNode == null) {
				previousNode.setNext(newNode);
				newNode.setPrevious(previousNode);
				tail = newNode;
			}else {
				previousNode.setNext(newNode);
				newNode.setPrevious(previousNode);
				nextNode.setPrevious(newNode);
				newNode.setNext(nextNode);
			}
			return oldNode.getElement().getValue();
		}
	}

	@Override
	public V remove(K key) {
		DoubleListNode<Entry<K,V>> oldNode = this.findNode(key);
		if(oldNode == null) {
			return null;
		}else {
			DoubleListNode<Entry<K,V>> previousNode = oldNode.getPrevious();
			DoubleListNode<Entry<K,V>> nextNode = oldNode.getNext();
			if(previousNode == null && nextNode == null) {
				head = null;
				tail = null;
			}else if(previousNode == null) {
				nextNode.setPrevious(null);
				head = nextNode;
			}else if(nextNode == null) {
				previousNode.setNext(null);
				tail = previousNode;
			}else {
				previousNode.setNext(nextNode);
				nextNode.setPrevious(previousNode);
			}
			currentSize--;
			return oldNode.getElement().getValue();
		}
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoubleListIterator<>(head, tail);
	}
	
	protected DoubleListNode<Entry<K,V>> findNode(K key){
		DoubleListNode<Entry<K,V>> currentNode = head;
		while(currentNode != null && !currentNode.getElement().getKey().equals(key)) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}
}
