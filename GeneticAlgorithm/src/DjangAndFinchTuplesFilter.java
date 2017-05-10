//This is a java program to implement Djang and Finch Tuples with Filter for 1D objects using N bins

public class DjangAndFinchTuplesFilter {

	public static int startPacking(int[][] a, int[] bins, int currentBin, int size)
	{
		//System.out.println("BIN - PACKING Algorithm 1D Objects(Djang and Finch Tuples with Filter)");
		//bin packing
		return binPacking(a, bins, currentBin, size, a.length);
	}
	
	//a - array of elements, size - size of baskets, n - number of elements
	public static int binPacking(int[][] a, int[] bins, int currentBin, int size, int n)
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
					a[i][1] = 1;
					//System.out.println("Element " + a[i][0] + " is packed to " + currentBin);
					// � �������� ������ 1/3 ������� � �� ���������, ��
					if ((bins[currentBin] <= size * 2 / 3) && (bins[currentBin] != 0))
					{
						//System.out.println("Try");
						//���� ���������� �� 1, 2 ��� 3 ���������, ����� ����������� ��������� �������
						bins[currentBin] = findFive(a, bins[currentBin]);
						//��������� ������� �� �����, ������, ��� ������� ���������
						bins[currentBin] = fillMore(a, bins[currentBin]);
						// ���� ��������� �������
						currentBin = currentBin + 1;
					}
					else if (bins[currentBin] == 0)
					{
						currentBin = currentBin + 1;						
					}
					return currentBin;
				}
				//���� ������� ������, ��� ������ �������
				else if (a[i][0] > size)
				{
					//System.out.println("Element " + a[i] + " is greater than size " + size + " of basket!");
					System.exit(1);
				}
				//���� ������� �� ������� � ������� �������, ���� �����
				else
				{
					//System.out.println("New bin");
					currentBin = currentBin + 1;
					bins[currentBin] -= a[i][0];	
					a[i][1] = 1;
					//System.out.println("Element " + a[i][0] + " is packed to " + currentBin);
					if ((bins[currentBin] <= size * 2 / 3) && (bins[currentBin] != 0))
					{
						//System.out.println("Try");
						//���� ���������� �� 1, 2 ��� 3 ���������, ����� ����������� ��������� �������
						bins[currentBin] = findFive(a, bins[currentBin]);
						bins[currentBin] = fillMore(a, bins[currentBin]);
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
			}
		
		}
		return currentBin;
	}
	
	public static int fillMore(int[][] a, int size)
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
	
	public static int findFive(int[][] a, int size)
	{
		//System.out.println("size = " + size);
		
		for (int j = 0; j < size; j++)
		{
			//System.out.println("j = " + j);
			//find 1 element to complete bin
			int flag = 0;
			for (int i = a.length - 1; i >= 0; i--)
			{
				////System.out.println("pack 1");
				if ((a[i][1] == 0) && (size - j - a[i][0] < 0))
				{
					//System.out.println("stop 1 pack: el - " + a[i][0]);
					break;
				}
				if ((a[i][1] == 0) && (size - a[i][0] == j))
				{
					a[i][1] = 1;
					//System.out.println("el 1 = " + a[i][0]);
					return size - a[i][0];
				}
			}
			//find 2 element to complete bin
			for (int i = a.length -1; i >= 0 && flag == 0; i--)
			{
				for (int k = a.length -1; k >= 0 && flag == 0; k--)
				{
					////System.out.println("pack 2");
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
							//System.out.println("el 1 = " + a[i][0]);
							//System.out.println("el 2 = " + a[k][0]);
							//System.out.println("el 3 = " + a[l][0]);
							return size - a[i][0] - a[k][0] - a[l][0];
						}
					}
				}
			}
			//find 4 element to complete bin
			flag = 0;
			for (int i = a.length -1; i >= 0 && flag == 0; i--)
			{
				if (a[i][1] == 0)
				{
					for (int k = i; k >= 0 && flag == 0; k--)
					{
						if (a[k][1] == 0)
						{
							for (int l = k; l >= 0 && flag == 0; l--)
							{
								if (a[l][1] == 0)
								{
									for (int p = l; p >= 0 && flag == 0; p--)
									{
										if (a[p][1] == 0)
										{
											////System.out.println("pack 4");
											//if ((i != k) && (l != k) && (i != l) && (i != p) && (p != l) && (p != k)
											//	&& (size - j - a[i][0] - a[k][0] - a[l][0] - a[p][0] < 0))
											if (size - j - a[i][0] - a[k][0] - a[l][0] - a[p][0] < 0)
											{
												flag = 1;
												//System.out.println("stop 4 pack: el - " + a[i][0] + " el - " + a[k][0] + " el - " + a[l][0] + " el - " + a[p][0]);
												break;
											}
											if ((i != k) && (l != k) && (i != l) && (i != p) && (p != l) && (p != k)
												&& (size - a[i][0] - a[k][0] - a[l][0] - a[p][0] == j))
											{
												a[i][1] = 1;
												a[k][1] = 1;
												a[l][1] = 1;
												a[p][1] = 1;
												//System.out.println("el 1 = " + a[i][0]);
												//System.out.println("el 2 = " + a[k][0]);
												//System.out.println("el 3 = " + a[l][0]);
												//System.out.println("el 4 = " + a[p][0]);
												return size - a[i][0] - a[k][0] - a[l][0] - a[p][0];
											}
										}
										else
										{
											continue;
										}
									}
								}
								else
								{
									continue;
								}
							}
						}
						else 
						{
							continue;
						}
					}
				}
				else
				{
					continue;
				}
			}
			//find 5 element to complete bin
			flag = 0;
			for (int i = a.length -1; i >= 0 && flag == 0; i--)
			{
				if ((a[i][1] == 0))
				{
					for (int k = i; k >= 0 && flag == 0; k--)
					{
						if ((a[k][1] == 0))
						{
							for (int l = k; l >= 0 && flag == 0; l--)
							{
								if ((a[l][1] == 0))
								{
									for (int p = l; p >= 0 && flag == 0; p--)
									{
										if (a[p][1] == 0)
										{
											for (int t = p; t >= 0 && flag == 0; t--)
											{
												if (a[t][1] == 0)
												{
												////System.out.println("pack 5");
												/*//System.out.println("size - j - a[i][0] - a[k][0] - a[l][0] - a[p][0] - a[t][0] = " + (size - j - a[i][0] - a[k][0] - a[l][0] - a[p][0] - a[t][0]));
												//System.out.println("a[i][1] " + a[i][1]);
												//System.out.println("a[k][1] " + a[k][1]);
												//System.out.println("a[l][1] " + a[l][1]);
												//System.out.println("a[p][1] " + a[p][1]);
												//System.out.println("a[t][1] " + a[t][1]);*/
													//if ((size - j - a[i][0] - a[k][0] - a[l][0] - a[p][0] - a[t][0] < 0) &&
													//		(i != k) && (l != k) && (i != l) && (i != p) && (p != l) && (p != k)
													//		&& (t != i) && (t != k) && (t != l) && (t != p))
													if (size - j - a[i][0] - a[k][0] - a[l][0] - a[p][0] - a[t][0] < 0)
													{
														flag = 1;
														//System.out.println("stop 5 pack: el - " + a[i][0] + " el - " + a[k][0] + " el - " + a[l][0] + " el - " + a[p][0] + " el - " + a[t][0]);
														break;
													}
													if ((i != k) && (l != k) && (i != l) && (i != p) && (p != l) && (p != k)
															&& (t != i) && (t != k) && (t != l) && (t != p)
															&& (size - a[i][0] - a[k][0] - a[l][0] - a[p][0] - a[t][0] == j))
													{
														a[i][1] = 1;
														a[k][1] = 1;
														a[l][1] = 1;
														a[p][1] = 1;
														a[t][1] = 1;
														//System.out.println("el 1 = " + a[i][0]);
														//System.out.println("el 2 = " + a[k][0]);
														//System.out.println("el 3 = " + a[l][0]);
														//System.out.println("el 4 = " + a[p][0]);
														//System.out.println("el 5 = " + a[t][0]);
														return size - a[i][0] - a[k][0] - a[l][0] - a[p][0] - a[t][0];
													}
												}
												else
												{
													continue;
												}
											}
										}
										else
										{
											continue;
										}
									}
								}
								else
								{
									continue;
								}
							}
						}
						else
						{
							continue;
						}
					}
				}
				else
				{
					continue;
				}
			}
		}
		return size;
	}
}
