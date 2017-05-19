import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {
	public static final int POPULATION_SIZE = 40;
	//public static final int CHROMOSOME_SIZE = 40;
	public static final int NUMBER_OF_TASKS = 4;
	public static final int NUMBER_OF_GA_ITERATIONS = 500;
	public static final String FILE_PATH = "C:\\data_for_binpacking\\1\\improve_results\\!result";
	public static final int NUMBER_OF_FILES = 750;
	public static final String DATA_PATH = "C:\\data_for_binpacking\\new_data\\";
	
	public static int size;
	List<Chromosome> population;
	List<Chromosome> childrens;
	
	public int[][] readFile(String fileName){
		BufferedReader br = null;
		FileReader fr = null;
		int n = 0;
		int[][] elements = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			int i = 0;
			String str = null;
			
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("Bin size:"))
				{
					str = sCurrentLine.substring(10, sCurrentLine.length());
					size = Integer.parseInt(str);
				}
				else if (sCurrentLine.contains("Number of elements:"))
				{
					str = sCurrentLine.substring(20, sCurrentLine.length());
					n = Integer.parseInt(str);
					elements = new int[n][2];
				}
				else
				{
					elements[i][0] = Integer.parseInt(sCurrentLine);
					elements[i][1] = 0;
					i++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (br != null)
					br.close();
				
				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println("size = " + size);
		System.out.println("n = " + n);
		int sum = 0;
		for (int i = 0; i < elements.length; i++)
		{
			sum = sum + elements[i][0];
		}
		System.out.println("sum = " + sum);
		return elements;
	}
	
	public void writeFile(int num) throws IOException
	{
		String s = "";
	    for (int i = 0; i < population.size(); i++)
	    {
	    	s = s + "population " + i + " fitness " + population.get(i).fitness + " years " +  population.get(i).years + " length " + population.get(i).chromosome.size() + "\n"; 
	    }
	    s = s + "\n";
	    for (int i = 0; i < population.size(); i++)
	    {
	    	for (int j = 0; j < population.get(i).chromosome.size(); j++)
	    	{
	    		s = s + population.get(i).chromosome.get(j).hugeItems + " " + population.get(i).chromosome.get(j).largeItems + " " + population.get(i).chromosome.get(j).mediumItems + " " + population.get(i).chromosome.get(j).smallItems + " " + population.get(i).chromosome.get(j).remainingItems + " " + population.get(i).chromosome.get(j).prevAlgorithm + " " + population.get(i).chromosome.get(j).algorithmNumber + "\n"; 
	    	}
	    	System.out.println("years - " + population.get(i).years);
	    }
	    
	    //System.out.println("s - " + s);
	    String path = FILE_PATH + num + ".txt";
	    FileWriter writer = new FileWriter(path); 
	    writer.write(s); 
	    writer.flush();
	    writer.close();
	}
	
	public void go(int num) throws IOException{
		initializePopulation();
		childrens = new ArrayList<Chromosome>();
		for (int i = 0; i < NUMBER_OF_GA_ITERATIONS; i++)
		{
			System.out.println("!!!Generation is - " + i);
			int[] flag = new int[population.size()];
			for (int j = 0; j < population.size(); j++)
			{
				flag[j] = 0;
			}
			for (int j = 0; j < population.size()/2; j++)
			{
				int tmp = (int)(Math.random() * 2);
				if (tmp == 0)
				{
					first_crossover(findTheBest(population, flag), findRandom(population, flag));
				}
				else
				{
					second_crossover(findTheBest(population, flag), findRandom(population, flag));
				}
			}
			for (int j = 0; j < population.size(); j++)
			{
				flag[j] = 0;
			}
			for (int j = 0; j < childrens.size(); j++)
			{
				for (int k = 0; k < NUMBER_OF_TASKS; k++)
				{
					int tmp = (int)(Math.random() * NUMBER_OF_FILES);
					String fileName = DATA_PATH + (tmp+1) + ".txt";
					int[][]elements = readFile(fileName);
					childrens.get(j).solveProblem(elements, size,0);
					System.out.println("Children " + j + " solves problem " + fileName + " bins - " + childrens.get(j).fitness);
				}
				System.out.println("Children " + j + " fitness - " + childrens.get(j).fitness);
			}
			int[] flagss = new int[population.size()];
			for (int j = 0; j < population.size(); j++)
			{
				flagss[j] = 0;
			}
			int[] flagsss = new int[childrens.size()];
			for (int j = 0; j < childrens.size(); j++)
			{
				flagsss[j] = 0;
			}
			Chromosome theworst1 = findTheWorst(population, flagss);
			Chromosome theworst2 = findTheWorst(population, flagss);
			Chromosome thebest1 = findTheBest(childrens, flagsss);
			Chromosome thebest2 = findTheBest(childrens, flagsss);
			if (theworst1.fitness > thebest1.fitness && theworst2.fitness > thebest2.fitness
					|| theworst1.fitness > thebest2.fitness && theworst2.fitness > thebest1.fitness)
			{
				population.remove(theworst1);
				population.add(thebest1);
				population.remove(theworst2);
				population.add(thebest2);
			}
			else if (theworst1.fitness > thebest1.fitness)
			{
				population.remove(theworst1);
				population.add(thebest1);
			}
			else if (theworst1.fitness > thebest2.fitness)
			{
				population.remove(theworst1);
				population.add(thebest2);
			}
			else if (theworst2.fitness > thebest2.fitness)
			{
				population.remove(theworst2);
				population.add(thebest2);
			}
			else if (theworst2.fitness > thebest1.fitness)
			{
				population.remove(theworst2);
				population.add(thebest1);
			}
			/*population.remove(findTheWorst(population, flagss));
			population.remove(findTheWorst(population, flagss));
			population.add(findTheBest(childrens, flagsss));
			population.add(findTheBest(childrens, flagsss));*/
			childrens.clear();
			int x = (int)(Math.random() * NUMBER_OF_FILES);
			String fileName = DATA_PATH + (x+1) + ".txt";
			int[][]elements = readFile(fileName);
			for (int j = 0; j < population.size(); j ++)
			{
				population.get(j).solveProblem(elements, size,0);
				System.out.println("Population " + j + " solves problem " + fileName + " bins - " + population.get(j).fitness);
				for (int k = 0; k < elements.length; k++)
				{
					elements[k][1] = 0;				
				}
			}
		}
		int[] flags = new int[population.size()];
		for (int j = 0; j < population.size(); j++)
		{
			flags[j] = 0;
		}

		System.out.println("The best result is " + findTheLastTheBest(population, flags));
		writeFile(num);
	}
	
	public Chromosome findTheBest(List<Chromosome> popul, int[] flag)
	{
		Chromosome tmp = new Chromosome(0);
		tmp.fitness = 1000000000;
		int x = 0;
		for (int i = 0; i < popul.size(); i++)
		{
			if ((flag[i] != 1) && (popul.get(i).fitness < tmp.fitness))
			{
				tmp = popul.get(i);
				x = i;
			}
			if (popul.get(i).fitness == tmp.fitness)
			{
				int t = (int)(Math.random() * 2);
				if (t == 1)
				{
					tmp = popul.get(i);
					x = i;
				}
			}
		}
		flag[x] = 1;
		return popul.get(x);
	}
	
	public int findTheLastTheBest(List<Chromosome> popul, int[] flag)
	{
		Chromosome tmp = new Chromosome(0);
		tmp.fitness = 1000000000;
		int x = 0;
		for (int i = 0; i < popul.size(); i++)
		{
			if ((flag[i] != 1) && (popul.get(i).fitness < tmp.fitness))
			{
				tmp = popul.get(i);
				x = i;
			}
			if (popul.get(i).fitness == tmp.fitness)
			{
				int t = (int)(Math.random() * 2);
				if (t == 1)
				{
					tmp = popul.get(i);
					x = i;
				}
			}
			System.out.println("i = " + i + " size = " + popul.get(i).chromosome.size());
		}
		flag[x] = 1;
		System.out.println("The best years " + popul.get(x).years);
		return x;
	}
	
	public Chromosome findTheWorst(List<Chromosome> popul, int[] flag)
	{
		Chromosome tmp = new Chromosome(0);
		tmp.fitness = 0;
		int x = 0;
		for (int i = 0; i < popul.size(); i++)
		{
			if ((flag[i] != 1) && (popul.get(i).fitness > tmp.fitness))
			{
				tmp = popul.get(i);
				x = i;
			}
			if (popul.get(i).fitness == tmp.fitness)
			{
				int t = (int)(Math.random() * 2);
				if (t == 1)
				{
					tmp = popul.get(i);
					x = i;
				}
			}
		}
		flag[x] = 1;
		return popul.get(x);
	}
	
	public Chromosome findRandom(List<Chromosome> popul, int[] flag)
	{
		int tmp = 0;
		while (true)
		{
			tmp = (int)(Math.random() * popul.size());
			if (flag[tmp] != 1)
			{
				flag[tmp] = 1;
				return popul.get(tmp);
			}
		}
	}
	
	public void initializePopulation(){
		String fileName = "C:\\data_for_binpacking\\new_data\\1.txt";
		//String fileName = "D:\\test.txt";
		int[][]elements = readFile(fileName);
		population = new ArrayList<Chromosome>();
		
		for (int i = 0; i < POPULATION_SIZE; i++)
		{
			Chromosome tmp = new Chromosome(1);
			tmp.solveProblem(elements, size,0);
			System.out.println("chromosome - " + i +" uses number of bins = " + tmp.fitness);
			population.add(tmp);
			for (int j = 0; j < elements.length; j++)
			{
				elements[j][1] = 0;				
			}
		}
	}
	
	void first_crossover(Chromosome parent1, Chromosome parent2) {
		Chromosome children1 = new Chromosome(0);
		Chromosome children2 = new Chromosome(0);
		int div = (int)(Math.random() * parent1.chromosome.size());
		while (div >= parent1.chromosome.size()-1 || div >= parent2.chromosome.size()-1)
		{
			div = (int)(Math.random() * parent1.chromosome.size());
		}
        int count = (int)(Math.random() * parent1.chromosome.size());
		while (count >= parent1.chromosome.size()-1 || count >= parent2.chromosome.size()-1)
		{
			count = (int)(Math.random() * parent1.chromosome.size());
		}
        for (int i = 0; i < parent1.chromosome.size(); i++)
        {
        	children1.chromosome.add(parent1.chromosome.get(i));
        }
        for (int i = 0; i < parent2.chromosome.size(); i++)
        {
        	children2.chromosome.add(parent2.chromosome.get(i));
        }
        for (int i = 0; i < count; i++)
        {
        	if ((div + i >= children1.chromosome.size()) && (div + i >= parent2.chromosome.size()))
        	{
        		//System.out.println("children1.chromosome.size() " + children1.chromosome.size());
        		//System.out.println("parent2.chromosome.size() " + parent2.chromosome.size());
        		//System.out.println("div + i " + div + i);
        		children1.chromosome.set((div + i) - children1.chromosome.size(), parent2.chromosome.get((div + i) - parent2.chromosome.size()));
        	}
        	else if (div + i >= children1.chromosome.size())
        	{
        		//System.out.println("2 ");
        		children1.chromosome.set((div + i) - children1.chromosome.size(), parent2.chromosome.get(div + i));
        	}
        	else if (div + i >= parent2.chromosome.size())
        	{
        		//System.out.println("3 ");
        		children1.chromosome.set((div + i), parent2.chromosome.get((div + i) - parent2.chromosome.size()));
        	}
        	else
        	{
        		//System.out.println("4 ");
        		children1.chromosome.set((div + i), parent2.chromosome.get(div + i));
        	}
        }
        for (int i = 0; i < count; i++)
        {
        	if ((div + i >= children2.chromosome.size()) && (div + i >= parent1.chromosome.size()))
        	{
        		//System.out.println("1 ");
        		children2.chromosome.set((div + i) - children2.chromosome.size(), parent1.chromosome.get((div + i) - parent1.chromosome.size()));
        	}
        	else if (div + i >= children2.chromosome.size())
        	{
        		//System.out.println("2 ");
        		children2.chromosome.set((div + i) - children2.chromosome.size(), parent1.chromosome.get(div + i));
        	}
        	else if (div + i >= parent1.chromosome.size())
        	{
        		//System.out.println("3 ");
        		children2.chromosome.set((div + i), parent1.chromosome.get((div + i) - parent1.chromosome.size()));
        	}
        	else
        	{
        		//System.out.println("4 ");
        		children2.chromosome.set((div + i), parent1.chromosome.get(div + i));
        	}
        }
		int tmp = (int)(Math.random() * 10);
		if (tmp == 0)
		{
			tmp = (int)(Math.random() * 20);
			if (tmp < 5)
			{
				first_mutate(children1);
			}
			else if (tmp < 10)
			{
				second_mutate(children1);
			}
			else
			{
				third_mutate(children1);
			}
		}
		tmp = (int)(Math.random() * 10);
		if (tmp == 0)
		{
			tmp = (int)(Math.random() * 20);
			if (tmp < 5)
			{
				first_mutate(children2);
			}
			else if (tmp < 10)
			{
				second_mutate(children2);
			}
			else
			{
				third_mutate(children2);
			}
		}
		childrens.add(children1);
		childrens.add(children2);
    }
	
	void second_crossover(Chromosome parent1, Chromosome parent2) {
		Chromosome children1 = new Chromosome(0);
		Chromosome children2 = new Chromosome(0);
		int tmp = 0;
		if (parent1.chromosome.size() > parent2.chromosome.size())
		{
			tmp = parent1.chromosome.size();
		}
		else
		{
			tmp = parent2.chromosome.size();
		}
		for (int i = 0; i < tmp; i ++)
		{
			int var = (int)(Math.random() * 10);
	        if (var == 9)
	        {
	        	if ((parent1.chromosome.size() > i) && (parent2.chromosome.size() > i))
	        	{
	        		//System.out.println("1 ");
		        	children1.chromosome.add(parent2.chromosome.get(i));
		        	children2.chromosome.add(parent1.chromosome.get(i));
	        	}
	        	else if (parent1.chromosome.size() > i)
	        	{
	        		//System.out.println("2 ");
	        		children2.chromosome.add(parent1.chromosome.get(i));
	        	}
	        	else if (parent2.chromosome.size() > i)
	        	{
	        		//System.out.println("3 ");
	        		children1.chromosome.add(parent2.chromosome.get(i));
	        	}
	        }
	        else
	        {
	        	if ((parent1.chromosome.size() > i) && (parent2.chromosome.size() > i))
	        	{
	        		//System.out.println("11 ");
		        	children1.chromosome.add(parent1.chromosome.get(i));
		        	children2.chromosome.add(parent2.chromosome.get(i));
	        	}
	        	else if (parent1.chromosome.size() > i)
	        	{
	        		//System.out.println("22 ");
	        		children1.chromosome.add(parent1.chromosome.get(i));
	        	}
	        	else if (parent2.chromosome.size() > i)
	        	{
	        		//System.out.println("33 ");
	        		children2.chromosome.add(parent2.chromosome.get(i));
	        	}
	        }
		}
		tmp = (int)(Math.random() * 10);
		if (tmp == 0)
		{
			tmp = (int)(Math.random() * 20);
			if (tmp < 5)
			{
				first_mutate(children1);
			}
			else if (tmp < 10)
			{
				second_mutate(children1);
			}
			else
			{
				third_mutate(children1);
			}
		}
		tmp = (int)(Math.random() * 10);
		if (tmp == 0)
		{
			tmp = (int)(Math.random() * 20);
			if (tmp < 5)
			{
				first_mutate(children2);
			}
			else if (tmp < 10)
			{
				second_mutate(children2);
			}
			else
			{
				third_mutate(children2);
			}
		}
		childrens.add(children1);
		childrens.add(children2);
    }
	
	public Chromosome first_mutate(Chromosome parent)
	{
		Genome gen = new Genome(1);
		parent.chromosome.add(gen);
		return parent;
	}
	public Chromosome second_mutate(Chromosome parent)
	{
		int tmp = (int)(Math.random() * parent.chromosome.size());
		parent.chromosome.remove(tmp);
		return parent;
	}
	
	public Chromosome third_mutate(Chromosome parent)
	{
		int tmp = (int)(Math.random() * parent.chromosome.size());
		parent.chromosome.remove(tmp);
		Genome gen = new Genome(1);
		parent.chromosome.add(gen);
		return parent;
	}
}