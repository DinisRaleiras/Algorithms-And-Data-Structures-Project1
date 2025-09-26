package dataStructures;

import dataStructures.DoubleList.DoubleListNode;

public class OrderedDictionaryInList<K extends Comparable<K>,V> implements OrderedDictionary<K,V> {

	static final long serialVersionUID = 0L;
	
	protected DoubleListNode<Entry<K,V>> head;
	
	protected DoubleListNode<Entry<K,V>> tail;
	
	protected int currentSize;
	
	public OrderedDictionaryInList() {
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
	    DoubleListNode<Entry<K, V>> currentNode = head;
	    Entry<K, V> newEntry = new EntryClass<>(key, value);

	    if (isEmpty()) {
	        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<>(newEntry, null, null);
	        head = tail = newNode;
	        currentSize++;
	        return null;
	    }

	    while (currentNode != null && currentNode.getElement().getKey().compareTo(key) < 0) {
	        currentNode = currentNode.getNext();
	    }

	    if (currentNode != null && currentNode.getElement().getKey().compareTo(key) == 0) {
	        V oldValue = currentNode.getElement().getValue();
	        DoubleListNode<Entry<K, V>> replacementNode = new DoubleListNode<>(newEntry, currentNode.getPrevious(), currentNode.getNext());
	        
	        if (currentNode.getPrevious() != null) {
	            currentNode.getPrevious().setNext(replacementNode);
	        } else {
	            head = replacementNode;
	        }

	        if (currentNode.getNext() != null) {
	            currentNode.getNext().setPrevious(replacementNode);
	        } else {
	            tail = replacementNode;
	        }
	        return oldValue;
	    }

	    if (currentNode == head) {
	        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<>(newEntry, null, head);
	        head.setPrevious(newNode);
	        head = newNode;
	    } 
	    
	    else if (currentNode == null) {
	        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<>(newEntry, tail, null);
	        tail.setNext(newNode);
	        tail = newNode;
	    } 
	    else {
	        DoubleListNode<Entry<K, V>> previousNode = currentNode.getPrevious();
	        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<>(newEntry, previousNode, currentNode);
	        previousNode.setNext(newNode);
	        currentNode.setPrevious(newNode);
	    }

	    currentSize++;
	    return null;
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

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if(this.isEmpty())
			throw new EmptyDictionaryException();
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if(this.isEmpty())
			throw new EmptyDictionaryException();
		return tail.getElement();
	}
	
	protected DoubleListNode<Entry<K,V>> findNode(K key) {
		DoubleListNode<Entry<K,V>> currentNode = head;
		while(currentNode != null && currentNode.getElement().getKey().compareTo(key) < 0) {
			currentNode = currentNode.getNext();
		}
		if(currentNode == null || currentNode.getElement().getKey().compareTo(key) > 0) {
			return null;
		}else {
			return currentNode;
		}
	}
	
}
