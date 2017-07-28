package hashing;

import java.util.Arrays;

public class HashTable<V> {
	private static final int DEFAULT_SIZE = 13;
	protected final double loadfactor;
	
	
	private V[] table;
	protected int size;
	protected int capacity;
	private boolean[] full;
	
	private int numProbes;
	
	@SuppressWarnings("unchecked")
	protected HashTable(double loadfactor, int size) {
		this.loadfactor = loadfactor;
		
		table = (V[]) new Object[size];
		full =       new boolean[size];
		capacity = table.length;
		numProbes = -1;
		size      =  0;
	}
	public HashTable(double loadfactor) {
		this(loadfactor,DEFAULT_SIZE);
	}
	
	
	public boolean insert(V val) {
		if(size > maxSize()) {
			resize();
		}
		
		int initial = hash(val,0);
		
		if (!full[initial]) {
			insert(initial,val);
			numProbes = 1;
			return true;
		}
		else {
			numProbes = 1;
			while(numProbes <= bound()) {
				int location = hash(val,numProbes);
				if(!full[location]) {
					insert(location,val);
					numProbes++;
					return true;
				}
				else {
					numProbes++;
				}
			}
			return false;
		}
	}
	
	
	public boolean search(V val) {
		int initial = hash(val,0);
		
		if (!full[initial]) {
			numProbes = 1;
			return false;
		}
		else if (table[initial].equals(val)) {
			numProbes = 1;
			return true;
		}
		else {
			numProbes = 1;
			while(numProbes <= bound()) {
				int location = hash(val,numProbes);
				if(!full[location]) {
					numProbes++;
					return false;
				}
				else if(table[location].equals(val)) {
					numProbes++;
					return true;
				}
				else {
					numProbes++;
				}
			}
			return false;
		}
	}
	
	protected int hash(V val, int probe) {
		throw new RuntimeException("Has not been implemented here");
	}
	
	private void insert(int location, V val) {
		table[location] = val;
		full[location] = true;
		size++;
	}
	
	protected int bound() {
		throw new RuntimeException("Has not been implemented here");
	}
	
	protected int maxSize() {
		throw new RuntimeException("Has not been implemented here");
	}
	
	private void resize() {
		int newsize = nextPrime(table.length * 2);
		HashTable<V> copy = copy(loadfactor,newsize);
		for(int i = 0; i < table.length; i++) {
			if(full[i])
				copy.insert(table[i]);
		}
		table = copy.table;
		full = copy.full;
		capacity = table.length;
		
		
	}
	
	protected HashTable<V> copy(double loadfactor2, int newsize) {
		throw new RuntimeException("implemented in child");
	}
	public int numProbesOfLastCall() {
		if(numProbes == -1) {
			throw new IllegalStateException("Tried to get numProbes without a call");
		}
		else {
			int temp = numProbes;
			numProbes = -1;
			return temp;
		}
	}
	
	public int size() {
		return size;
	}
	
	private int nextPrime(int currentVal) {
		while(!isPrime(currentVal)) {
			currentVal++;
		}
		
		return currentVal;
	}
	
	private boolean isPrime(int val) {
		if (val <  2) return false;
		if (val == 2) return true;
		if (val % 2 == 0) return false;
		
		int bound = (int)Math.sqrt(val);
		for(int i = 3; i <= bound; i+= 2) {
			if(val % i == 0) return false;
		}
		
		return true;
	}
	
	public String toString() {
		return Arrays.toString(table);
	}
	
	public HashTable<V> clear(double loadfactor) {
		throw new RuntimeException("not implemented yet");
	}
}
