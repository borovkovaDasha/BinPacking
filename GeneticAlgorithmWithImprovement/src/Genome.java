
public class Genome {
	public static final int ALGORITHM_NUMBER = 8;
	double hugeItems, largeItems, mediumItems, smallItems, remainingItems;
	int algorithmNumber, prevAlgorithm;

	public Genome(int rand) {
		if (rand == 1)
		{
			hugeItems = Math.random();
		    largeItems = Math.random();
		    mediumItems = Math.random();
		    smallItems = Math.random();
		    remainingItems = Math.random();
		    algorithmNumber = (int)(Math.random()*ALGORITHM_NUMBER) + 1;
		    prevAlgorithm =(int)(Math.random()*ALGORITHM_NUMBER) + 1;
		}
		else
		{
			hugeItems = 0;
		    largeItems = 0;
		    mediumItems = 0;
		    smallItems = 0;
		    remainingItems = 0;
		    algorithmNumber = 0;
		    prevAlgorithm = 0;
		}
	}
	
	public String toString() {
		String debugLine = hugeItems + " " + largeItems + " " + mediumItems + " " + smallItems +
				" " + remainingItems + " " + algorithmNumber + " " + prevAlgorithm;
		return debugLine;
	}
	public String toStringAlg() {
		String debugLine = algorithmNumber + " " ;
		return debugLine;
	}
}
