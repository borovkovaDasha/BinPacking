import java.util.ArrayList;

//This is a java program to implement next fit decreasing for 1D objects using N bins

public class NextFitDecreasingFilter {

	public static int startPacking(int[][] a, int[] bins, int currentBin, int size, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		//System.out.println("BIN - PACKING Algorithm 1D Objects(Next Fit Decreasing with filter)");

		//bin packing
		return binPacking(a, bins, currentBin, size, a.length, solel, solbin);
	}
	
	//a - array of elements, size - size of baskets, n - number of elements
	public static int binPacking(int[][] a, int[] bins, int currentBin, int size, int n, ArrayList <Integer> solel, ArrayList <Integer> solbin)
	{
		for (int i = 0; i < n; i++)
		{
			if (a[i][0] > size)
			{
				//System.out.println("Element " + a[i][0] + " is greater than size " + size + " of basket!");
				System.exit(1);
			}
			else if (a[i][1] != 1) 
			{
				if (bins[currentBin] - a[i][0] >= 0)
				{
					//System.out.println("1Element " + a[i][0] + " is packed to " + currentBin);
					bins[currentBin] -= a[i][0];
					a[i][1] = 1;
					solel.add(a[i][0]);
					solbin.add(currentBin);
					return currentBin;
				}
				else 
				{
					//System.out.println("fill more");
					bins[currentBin] = fillMore(a, bins[currentBin], currentBin, solel, solbin);
					currentBin = currentBin + 1;
					//bins[currentBin] -= a[i][0];
					//a[i][1] = 1;
					//System.out.println("2Element " + a[i][0] + " is packed to " + currentBin);
					//return currentBin;
				}
			}
		}
		return currentBin;
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
		
		for (int i = a.length -1; i >= 0; i--)
		{
			if ((a[i][1] != 1) && (size - a[i][0] >= 0))
			{
				//System.out.println("Element " + a[i][0] + " is also packed ");
				size -= a[i][0];
				a[i][1] = 1;
			}
			else if (a[i][0] > size)
			{
				//System.out.println("Element " + a[i][0] + " > size " + size);
				return size;
			}
		}
		return size;	
	}*/
}
