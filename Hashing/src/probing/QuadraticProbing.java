package probing;

public class QuadraticProbing implements ProbePolicy{

	@Override
	public int next(int hashValue, int index) {
		return hashValue + index*index;
	}

}
