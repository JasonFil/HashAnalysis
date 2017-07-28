package hashing;

public class QuadraticHashing<V> extends HashTable<V>{

	public QuadraticHashing(double loadfactor) {
		super(loadfactor);
	}
	
	public QuadraticHashing(double loadfactor, int size) {
		super(loadfactor,size);
	}
	
	protected int hash(V val, int probe) {
		return (Math.abs(val.hashCode()) + probe*probe) % capacity;
	}
	
	
	protected int bound() {
		return capacity / 2;
	}
	
	protected int maxSize() {
		return (int) (capacity * loadfactor);
	}
	
	public HashTable<V> clear(double loadfactor) {
		return new QuadraticHashing<V>(loadfactor);
	}
	
	protected HashTable<V> copy(double loadfactor, int size) {
		return new QuadraticHashing<V>(loadfactor,size);
	}

}
