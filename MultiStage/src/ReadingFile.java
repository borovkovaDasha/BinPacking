import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFile {
	
	public int binSize;
	public int bestResult;
	
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
					binSize = Integer.parseInt(str);
				}
				else if (sCurrentLine.contains("Number of elements:"))
				{
					str = sCurrentLine.substring(24, sCurrentLine.length());
					n = Integer.parseInt(str);
					elements = new int[n][2];
				}
				else if (sCurrentLine.contains("Num of Bins:"))
				{
					str = sCurrentLine.substring(23, sCurrentLine.length());
					if (str.contains(" "))
					{
						str = sCurrentLine.substring(23, sCurrentLine.length()-1);
					}
					else
						str = sCurrentLine.substring(23, sCurrentLine.length());
					bestResult = Integer.parseInt(str);
					//System.out.println("bestResult " + bestResult);
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
		//int sum = 0;
		//System.out.println("sum = " + sum);
		//System.out.println("read file " + fileName);
		//System.out.println("binSize = " + binSize);
		//System.out.println("n = " + n);
		return elements;
	}
	
	public int[][] sort(int[][] sequence)
	{
		// Bubble Sort descending order
		for (int i = 0; i < sequence.length; i++)
			for (int j = 0; j < sequence.length - 1; j++)
				if (sequence[j][0] < sequence[j + 1][0])
				{
					sequence[j][0] = sequence[j][0] + sequence[j + 1][0];
					sequence[j + 1][0] = sequence[j][0] - sequence[j + 1][0];
					sequence[j][0] = sequence[j][0] - sequence[j + 1][0];
					sequence[j][1] = sequence[j][1] + sequence[j + 1][1];
					sequence[j + 1][1] = sequence[j][1] - sequence[j + 1][1];
					sequence[j][1] = sequence[j][1] - sequence[j + 1][1];
				}

		return sequence;
	}
}
