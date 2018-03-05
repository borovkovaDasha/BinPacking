import java.util.ArrayList;

//This is a java program to implement largest fit decreasing with filter for 1D objects using N bins

public class LargestFitDecreasingFilter {
	
	public static int startPacking(int[][] a, int[] bins, int currentBin, int size, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		//System.out.println("BIN - PACKING Algorithm 1D Objects(Largest Fit Decreasing with Filter)");
		//bin packing
		return binPacking(a, bins, size, a.length, solel, solbin);
	}
	
	//a - array of elements, size - size of baskets, n - number of elements
	public static int binPacking(int[][] a, int[] bins, int size, int n, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		for (int i = 0; i < n; i++)
			for (int j = 0; j < bins.length; j++)
			{
				if (a[i][0] > size)
				{
					//System.out.println("Element " + a[i][0] + " is greater than size " + size + " of basket!");
					System.exit(1);
				}
				if (a[i][1] != 1)
				{
					if (bins[j] - a[i][0] >= 0)
					{
						//System.out.println("Element " + a[i][0] + " is packed to " + j);
						bins[j] -= a[i][0];
						a[i][1] = 1;
						solel.add(a[i][0]);
						solbin.add(j);
						if (j > 0)
						{
							//System.out.println("fill more");
							bins[j-1] = fillMore(a, bins[j-1], j, solel, solbin);
						}
						return j;
					}
				}
			}
		return -1;
	}
	
	public static int fillMore(int[][] a, int size, int currentBin, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		if(size == 0)
		{
			//System.out.println("no space");
			return 0;
		}
		
		for (int i = 0; i < a.length; i++)
		{
			if ((a[i][1] != 1) && (size - a[i][0] >= 0))
			{
				//System.out.println("Element " + a[i][0] + " is also packed ");
				size -= a[i][0];
				a[i][1] = 1;
				solel.add(a[i][0]);
				solbin.add(currentBin);
			}
		}
		return size;	
	}
	
	/*public static int fillMore(int[][] a, int size)
	{
		if(size == 0)
		{
			//System.out.println("no space");
			return 0;
		}
		//System.out.println("size " + size);
		for (int i = a.length - 1; i >= 0; i--)
		{
			if (a[i][0] > size)
			{
				//System.out.println("Element " + a[i][0] + " > size " + size);
				return size;
			}
			if ((a[i][1] != 1) && (size - a[i][0] >= 0))
			{
				//System.out.println("Element " + a[i][0] + " is also packed ");
				size -= a[i][0];
				a[i][1] = 1;
			}
		}
		return size;	
	}*/
}