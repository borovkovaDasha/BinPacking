
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Genome {
	    //final String str;
	    //final int fitness;
	public static final int ALGORITHM_NUMBER = 8;
	double hugeItems, largeItems, mediumItems, smallItems, remainingItems;
	int algorithmNumber;

	public Genome(int rand) {
		if (rand == 1)
		{
			hugeItems = Math.random();
		    largeItems = Math.random();
		    mediumItems = Math.random();
		    smallItems = Math.random();
		    remainingItems = Math.random();
		    algorithmNumber = (int)(Math.random()*ALGORITHM_NUMBER) + 1;
		}
		else
		{
			hugeItems = 0;
		    largeItems = 0;
		    mediumItems = 0;
		    smallItems = 0;
		    remainingItems = 0;
		    algorithmNumber = 0;
		}

	        /*int fitness = 0;
	        for (int i = 0; i < a.length; i++)
	        {
	        	if (a[i] > size/2)
	        	{
	        		hugeItems++;
	        	}
	        	else if (a[i]>size/3)
	        	{
	        		largeItems++;
	        	}
	        	else if (a[i]>size/4)
	        	{
	        		mediumItems++;
	        	}
	        	else
	        	{
	        		smalItems++;
	        	}
	        	if (b[i] != 1)
	        	{
	        		remainingItems++;
	        	}
	        }
	        hugeItems = hugeItems/a.length;
	        largeItems = largeItems/a.length;
	        mediumItems = mediumItems/a.length;
	        smalItems = smalItems/a.length;
	        remainingItems = remainingItems/a.length;
	        /*for (int j = 0; j < str.length(); j++) {
	            fitness += Math.abs(str.charAt(j) - GeneticAlgorithm.TARGET.charAt(j));
	        }*/
	        //this.fitness = fitness;
	    }

	    /*public int compareTo(Genome o) {
	        return fitness - o.fitness;
	    }*/

	}
