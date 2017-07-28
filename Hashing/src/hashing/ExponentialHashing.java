package hashing;
public class ExponentialHashing<V> extends HashTable<V> {

	public ExponentialHashing(double loadfactor) {
		super(loadfactor);
	}
	
	public ExponentialHashing(double loadfactor, int size) {
		super(loadfactor,size);
	}
	
	
	protected int hash(V val, int probe) {
		if(val.hashCode() % capacity == 0) {
			return (int)Math.pow(Math.abs(val.hashCode()) + 2, probe) % capacity;
		}
		if(val.hashCode() % capacity == 1) {
			return (int)Math.pow(Math.abs(val.hashCode()) + 1, probe) % capacity;
		}
		return (int)Math.pow(Math.abs(val.hashCode()),probe) % capacity;
	}
	
	
	protected int bound() {
		return capacity - 1;
	}
	
	protected int maxSize() {
		return (int) (capacity * loadfactor);
	}
	
	
	public HashTable<V> clear(double loadfactor) {
		return new ExponentialHashing<V>(loadfactor);
	}
	
	protected HashTable<V> copy(double loadfactor, int size) {
		return new ExponentialHashing<V>(loadfactor,size);
	}
}
