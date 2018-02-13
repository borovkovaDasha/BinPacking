import java.util.ArrayList;
import java.util.HashMap;

//This is a java program to implement next fit decreasing for 1D objects using N bins

public class NextFitDecreasing {

	public int startPacking(int[][] a, int[] bins, int currentBin, int size, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		//System.out.println("BIN - PACKING Algorithm 1D Objects(Next Fit Decreasing)");

		//bin packing
		return binPacking(a, bins, currentBin, size, a.length, solel, solbin);
	}
	
	//a - array of elements, size - size of baskets, n - number of elements
	public int binPacking(int[][] a, int[] bins, int currentBin, int size, int n, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
	
		for (int i = 0; i < n; i++)
		{
			if (a[i][1] != 1)
			{
				if (bins[currentBin] - a[i][0] >= 0)
				{
					//System.out.println("Element " + a[i][0] + " is packed to " + currentBin);
					bins[currentBin] -= a[i][0];
					a[i][1] = 1;
					solel.add(a[i][0]);
					solbin.add(currentBin);
					return currentBin;
				}
				else if (a[i][0] > size)
				{
					//System.out.println("Element " + a[i] + " is greater than size " + size + " of basket!");
					System.exit(1);
				}
				else
				{
					currentBin = currentBin + 1;
					//bins[currentBin] -= a[i][0];	
					//a[i][1] = 1;
					//System.out.println("Element " + a[i][0] + " is packed to new " + currentBin);
					//return currentBin;
				}
			}
		}
		return currentBin;		
	}
}
