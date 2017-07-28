package probing;

public class LinearProbing implements ProbePolicy{
	
	@Override
	public int next(int hashValue, int index) {
		return hashValue + index;
	}

}
