package dataStructures;

import java.io.Serializable;

import dataStructures.DoubleList.DoubleListNode;

public class SortedDoubleList<E extends Comparable<E>> implements SortedList<E>, Serializable{

	static final long serialVersionUID = 0L;
	
	
	protected DoubleListNode<E> head;
	
	protected DoubleListNode<E> tail;
	
	protected int currentSize;
	
	public SortedDoubleList() {
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
	public Iterator<E> iterator() {
		return new DoubleListIterator<E>(head, tail);
	}

	@Override
	public E getMin() throws EmptyListException {
		if(this.isEmpty())
			throw new EmptyListException();
		return head.getElement();
	}

	@Override
	public E getMax() throws EmptyListException {
		if(this.isEmpty())
			throw new EmptyListException();
		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		return this.getNode(position).getElement();
	}

	@Override
	public E find(E element) {
		DoubleListNode<E> findNode = this.findNode(element);
		if(findNode == null) {
			return null;
		}else {
			return findNode.getElement();
		}
	}

	@Override
	public void add(E element) {
	    DoubleListNode<E> newNode = new DoubleListNode<E>(element);
	    
	    if (isEmpty()) {
	        head = newNode;
	        tail = newNode;
	    }
	    else if (element.compareTo(head.getElement()) <= 0) {
	        newNode.setNext(head);
	        head.setPrevious(newNode);
	        head = newNode;
	    }
	    else if (element.compareTo(tail.getElement()) >= 0) {
	        newNode.setPrevious(tail);
	        tail.setNext(newNode);
	        tail = newNode;
	    }
	    else {
	        DoubleListNode<E> nextNode = this.getAfter(element);
	        DoubleListNode<E> previousNode = nextNode.getPrevious();
	        nextNode.setPrevious(newNode);
	        newNode.setNext(nextNode);
	        previousNode.setNext(newNode);
	        newNode.setPrevious(previousNode);
	    }
	    
	    currentSize++;
	}


	@Override
	public E removeMin() throws EmptyListException {
		if(this.isEmpty()) {
			throw new EmptyListException();
		}
		return remove(head.getElement());
			
	}

	@Override
	public E removeMax() throws EmptyListException {
		if(this.isEmpty()) {
			throw new EmptyListException();
		}
		return remove(tail.getElement());
	}

	@Override
	public E remove(E element) {
		DoubleListNode<E> currentNode = this.findNode(element);
		if(currentNode == null) {
			return null;
		}else {
			DoubleListNode<E> beforeNode = currentNode.getPrevious();
			DoubleListNode<E> afterNode = currentNode.getNext();
			if(beforeNode == null && afterNode == null) {
				head = null;
				tail = null;
			}else if(beforeNode == null) {
				head = afterNode;
				head.setPrevious(null);
			}else if(afterNode == null) {
				tail = beforeNode;
				tail.setNext(null);
			}else {
				beforeNode.setNext(afterNode);
				afterNode.setPrevious(beforeNode);
			}
			currentSize--;
			return currentNode.getElement();
		}
	}
	
	protected DoubleListNode<E> getAfter(E element){
		DoubleListNode<E> currentNode = head;
		while(currentNode != null && currentNode.getElement().compareTo(element) < 0) {
			currentNode = currentNode.getNext();
		}
		if(currentNode == null) {
			return null;
		}else {
			return currentNode;
		}
	}
	
	protected DoubleListNode<E> getNode(int position) throws InvalidPositionException {
		if(position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		if(position < currentSize / 2) {
			int i = currentSize - 1;
			DoubleListNode<E> currentNode = tail;
			while(i != position) {
				currentNode = currentNode.getPrevious();
				i--;
			}
			return currentNode;
		} else {
			int i = 0;
			DoubleListNode<E> currentNode = head;
			while(i != position) {
				currentNode = currentNode.getNext();
				i++;
			}
			return currentNode;
		} 
	}
	
	protected DoubleListNode<E> findNode(E element){
		DoubleListNode<E> currentNode = head;
		while(currentNode != null && currentNode.getElement().compareTo(element) < 0) {
			currentNode = currentNode.getNext();
		}
		if(currentNode != null && currentNode.getElement().compareTo(element) == 0) {
			return currentNode;
		}else {
			return null;
		}
	}

}
