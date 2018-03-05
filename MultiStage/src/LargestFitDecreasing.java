import java.util.ArrayList;

//This is a java program to implement largest fit decreasing for 1D objects using N bins

public class LargestFitDecreasing {
	
	public static int startPacking(int[][] a, int[] bins, int size, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		//System.out.println("BIN - PACKING Algorithm 1D Objects(Largest Fit Decreasing)");
		//bin packing
		return binPacking(a, bins, size, a.length, solel, solbin);
	}
	
	//a - array of elements, size - size of baskets, n - number of elements
	public static int binPacking(int[][] a, int[] bins, int size, int n, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < bins.length; j++)
			{
				if (a[i][1] != 1)
				{
					if (bins[j] - a[i][0] >= 0)
					{
						bins[j] -= a[i][0];
						a[i][1] = 1;
						solel.add(a[i][0]);
						solbin.add(j);
						//System.out.println("Element " + a[i][0] + " is packed to " + j);
						return j;
					}
					if (a[i][0] > size)
					{
						//System.out.println("Element " + a[i][0] + " is greater than size " + size + " of basket!");
						System.exit(1);
					}
				}
			}
		return -1;
	}

	
}
