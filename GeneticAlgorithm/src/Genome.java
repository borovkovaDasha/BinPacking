
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Genome {
	public static final int ALGORITHM_NUMBER = 8;
	double hugeItems, largeItems, mediumItems, smallItems, remainingItems;
	int algorithmNumber;

	public Genome(int rand) {
		// create random genome
		if (rand == 1)
		{
			hugeItems = Math.random();
		    largeItems = Math.random();
		    mediumItems = Math.random();
		    smallItems = Math.random();
		    remainingItems = Math.random();
		    algorithmNumber = (int)(Math.random()*ALGORITHM_NUMBER) + 1;
		}
		// create genome initialized by null
		else
		{
			hugeItems = 0;
		    largeItems = 0;
		    mediumItems = 0;
		    smallItems = 0;
		    remainingItems = 0;
		    algorithmNumber = 0;
		}
    }
}
