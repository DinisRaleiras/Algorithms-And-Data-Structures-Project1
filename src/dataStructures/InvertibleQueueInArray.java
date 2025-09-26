package dataStructures;

@SuppressWarnings("serial")
public class InvertibleQueueInArray<E> extends QueueInArray<E> implements InvertibleQueue<E>{

	protected boolean inverted;
	
	public InvertibleQueueInArray(int capacity) {
		super(capacity);
		inverted = false;
	}
	
	public InvertibleQueueInArray() {
		super();
		inverted = false;
	}
	
	@Override
	public void invert() {
		int temp = front;
		front = rear;
		rear = temp;
		inverted = !inverted;	
	}
	
	protected int nextIndex( int index )
    {
		if(inverted) {
			return Math.abs(( index - 1 ) % array.length);
		} else {
			return Math.abs(( index + 1 ) % array.length);
		}
    	
    }

}
