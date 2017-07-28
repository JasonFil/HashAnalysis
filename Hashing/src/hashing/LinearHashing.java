package hashing;

public class LinearHashing<V> extends HashTable<V> {

	public LinearHashing(double loadfactor) {
		super(loadfactor);
	}

	
	
	public LinearHashing(double loadfactor, int size) {
		super(loadfactor,size);
	}



	protected int hash(V val, int probe) {
		return (Math.abs(val.hashCode()) + probe) % capacity;
	}
	
	
	protected int bound() {
		return capacity;
	}
	
	protected int maxSize() {
		return (int) (capacity * loadfactor);
	}
	
	public HashTable<V> clear(double loadfactor) {
		return new LinearHashing<V>(loadfactor);
	}
	
	protected HashTable<V> copy(double loadfactor, int size) {
		return new LinearHashing<V>(loadfactor,size);
	}
}
