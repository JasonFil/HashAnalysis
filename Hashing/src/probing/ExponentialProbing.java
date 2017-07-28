package probing;

public class ExponentialProbing implements ProbePolicy{

	@Override
	public int next(int hashValue, int index) {
		return (int) (hashValue + Math.pow(2,index-1));
	}

}
