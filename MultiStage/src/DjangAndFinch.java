import java.util.ArrayList;

//This is a java program to implement Djang and Finch for 1D objects using N bins

public class DjangAndFinch {
		
	public static int startPacking(int[][] a, int[] bins, int currentBin, int size, ArrayList<Integer> solel, ArrayList<Integer> solbin)
	{
		//System.out.println("BIN - PACKING Algorithm 1D Objects(Djang and Finch)");
		//bin packing
		return binPacking(a, bins, currentBin, size, a.length, solel, solbin);
	}
	
	//a - array of elements, size - size of baskets, n - number of elements
	public static int binPacking(int[][] a, int[] bins, int currentBin, int size, int n, ArrayList<Integer> solel, ArrayList<Integer> solbin)
	{
		//���������� �� ���� ���������
		for (int i = 0; i < n; i++)
		{
			if (a[i][1] != 1)
			{
				//���� ������� ������� � �������
				if (bins[currentBin] - a[i][0] >= 0)
				{
					bins[currentBin] -= a[i][0];
					solel.add(a[i][0]);
					solel.add(currentBin);
					a[i][1] = 1;
					////System.out.println("Element " + a[i][0] + " is packed to " + currentBin);
					// � �������� ������ 1/3 ������� � �� ���������, ��
					if ((bins[currentBin] <= size * 2 / 3) && (bins[currentBin] != 0))
					{
						////System.out.println("Try");
						//���� ���������� �� 1, 2 ��� 3 ���������, ����� ����������� ��������� �������
						bins[currentBin] = findTrinom(a, bins[currentBin], currentBin, solel, solbin);
						// ���� ��������� �������
						currentBin = currentBin + 1;
						//return currentBin;
					}
					else if (bins[currentBin] == 0)
					{
						currentBin = currentBin + 1;
						//return currentBin;
					}
					return currentBin;
				}
				//���� ������� ������, ��� ������ �������
				else if (a[i][0] > size)
				{
					////System.out.println("Element " + a[i][0] + " is greater than size " + size + " of basket!");
					System.exit(1);
				}
				//���� ������� �� ������� � ������� �������, ���� �����
				else
				{
					//System.out.println("New bin");
					currentBin = currentBin + 1;
					if (bins[currentBin] - a[i][0] >= 0)
					{
						bins[currentBin] -= a[i][0];	
						a[i][1] = 1;
						solel.add(a[i][0]);
						solel.add(currentBin);
					}
					//System.out.println("Element " + a[i][0] + " is packed to " + currentBin);
					if ((bins[currentBin] <= size * 2 / 3) && (bins[currentBin] != 0))
					{
						//System.out.println("Try");
						//���� ���������� �� 1, 2 ��� 3 ���������, ����� ����������� ��������� �������
						bins[currentBin] = findTrinom(a, bins[currentBin], currentBin, solel, solbin);
						// ���� ��������� �������
						currentBin = currentBin + 1;
						return currentBin;
					}
					if (bins[currentBin] == 0)
					{
						currentBin = currentBin + 1;	
						return currentBin;
					}
					
				}
			}
		
		}
		return currentBin;
		
	}
	
	public static int findTrinom(int[][] a, int size, int currentBin, ArrayList<Integer> solel, ArrayList<Integer> solbin)
	{
		for (int j = 0; j < size; j++)
		{
			//System.out.println("j = " + j);
			//find 1 element to complete bin
			int flag = 0;
			for (int i = a.length - 1; i >= 0; i--)
			{
				//System.out.println("pack 1");
				if ((size - j - a[i][0] < 0))
				{
					//System.out.println("stop 1 pack: el - " + a[i][0]);
					break;
				}
				if ((a[i][1] == 0) && (size - a[i][0] == j))
				{
					a[i][1] = 1;
					solel.add(a[i][0]);
					solel.add(currentBin);
					//System.out.println("el 1 = " + a[i][0]);
					return size - a[i][0];
				}
			}
			//find 2 element to complete bin
			for (int i = a.length -1; i >= 0 && flag == 0; i--)
			{
				for (int k = a.length -1; k >= 0 && flag == 0; k--)
				{
					//System.out.println("pack 2");
					//if ((a[i][1] == 0) && (a[k][1] == 0) && (size - j - a[i][0] - a[k][0] < 0))
					if (size - j - a[i][0] - a[k][0] < 0)
					{
						//System.out.println("stop 2 pack: el - " + a[i][0] + " el - " + a[k][0]);
						flag = 1;
						break;
					}
					if ((a[i][1] == 0) && (a[k][1] == 0) && (i != k) && (size - a[i][0] - a[k][0] == j))
					{
						a[i][1] = 1;
						a[k][1] = 1;
						solel.add(a[i][0]);
						solel.add(currentBin);
						solel.add(a[k][0]);
						solel.add(currentBin);
						//System.out.println("el 1 = " + a[i][0]);
						//System.out.println("el 2 = " + a[k][0]);
						return size - a[i][0] - a[k][0];
					}
				}
			}
			//find 3 element to complete bin
			//int x = 0;
			flag = 0;
			for (int i = a.length -1; i >= 0 && flag == 0; i--)
			{
				for (int k = a.length -1; k >= 0 && flag == 0; k--)
				{
					for (int l = a.length -1; l >= 0 && flag == 0; l--)
					{
						//x++;
						////System.out.println("x = " + x);
						//System.out.println("pack 3");
						//if ((a[i][1] == 0) && (a[k][1] == 0) && (a[l][1] == 0) && (size - j - a[i][0] - a[k][0] - a[l][0] < 0))
						if (size - j - a[i][0] - a[k][0] - a[l][0] < 0)
						{
							flag = 1;
							//System.out.println("stop 3 pack: el - " + a[i][0] + " el - " + a[k][0] + " el - " + a[l][0]);
							break;
						}
						if ((a[i][1] == 0) && (a[k][1] == 0) && (a[l][1] == 0) &&
								(i != k) && (l != k) && (i != l)
								&& (size - a[i][0] - a[k][0] - a[l][0] == j))
						{
							a[i][1] = 1;
							a[k][1] = 1;
							a[l][1] = 1;
							solel.add(a[i][0]);
							solel.add(currentBin);
							solel.add(a[k][0]);
							solel.add(currentBin);
							solel.add(a[l][0]);
							solel.add(currentBin);
							//System.out.println("el 1 = " + a[i][0]);
							//System.out.println("el 2 = " + a[k][0]);
							//System.out.println("el 3 = " + a[l][0]);
							return size - a[i][0] - a[k][0] - a[l][0];
						}
					}
				}
			}
		}
		return size;
	}
}
