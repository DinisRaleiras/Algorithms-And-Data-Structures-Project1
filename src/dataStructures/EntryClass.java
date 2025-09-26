package dataStructures;

public class EntryClass<K,V> implements Entry<K,V> {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
    
    /**
     * key stored in the entry 
     */
	protected K key;
	
	/**
     * value stored in the entry 
     */
	protected V value;
	
	/**
     * Constructor of an entry with key and value.
     * @param key - The key to be contained in the entry
     * @param value - The value to be contained in the entry
     */
	public EntryClass(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
}
