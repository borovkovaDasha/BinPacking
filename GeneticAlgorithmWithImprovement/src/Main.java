public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("GA");
		for (int i = 1; i <= 720; i++)
		{
			System.out.println("!!!!!GA " + i);
			GeneticAlgorithm GA = new GeneticAlgorithm();
			GA.go(i);
		}
		/*String[] result_files = new String[6];
		result_files[0] = "D:\\data_for_binpacking\\GAIsolution500\\test1.txt";
		result_files[1] = "D:\\data_for_binpacking\\GAIsolution500\\test2.txt";
		result_files[2] = "D:\\data_for_binpacking\\GAIsolution500\\test3.txt";
		result_files[3] = "D:\\data_for_binpacking\\GAIsolution500\\test4.txt";
		result_files[4] = "D:\\data_for_binpacking\\GAIsolution500\\test5.txt";
		result_files[5] = "D:\\data_for_binpacking\\GAIsolution500\\test6.txt";
		String[] chromosome_files = new String[6];
		chromosome_files[0] = "D:\\data_for_binpacking\\GAIresults500\\testing1bin2.txt";
		chromosome_files[1] = "D:\\data_for_binpacking\\GAIresults500\\testing2bin2.txt";
		chromosome_files[2] = "D:\\data_for_binpacking\\GAIresults500\\testing3bin2.txt";
		chromosome_files[3] = "D:\\data_for_binpacking\\GAIresults500\\testing4bin2.txt";
		chromosome_files[4] = "D:\\data_for_binpacking\\GAIresults500\\testing5bin2.txt";
		chromosome_files[5] = "D:\\data_for_binpacking\\GAIresults500\\testing6bin2.txt";
		
		for (int i = 0; i < result_files.length; i++) {
			TestChromosome TC = new TestChromosome();
			TC.start(result_files[i],chromosome_files[i]);
		}
		String[] result_files = new String[12];
		result_files[0] = "D:\\data_for_binpacking\\GAIsolution1000\\test1.txt";
		result_files[1] = "D:\\data_for_binpacking\\GAIsolution1000\\test2.txt";
		result_files[2] = "D:\\data_for_binpacking\\GAIsolution1000\\test3.txt";
		result_files[3] = "D:\\data_for_binpacking\\GAIsolution1000\\test4.txt";
		result_files[4] = "D:\\data_for_binpacking\\GAIsolution1000\\test5.txt";
		result_files[5] = "D:\\data_for_binpacking\\GAIsolution1000\\test6.txt";
		result_files[6] = "D:\\data_for_binpacking\\GAIsolution1000\\test7.txt";
		result_files[7] = "D:\\data_for_binpacking\\GAIsolution1000\\test8.txt";
		result_files[8] = "D:\\data_for_binpacking\\GAIsolution1000\\test9.txt";
		result_files[9] = "D:\\data_for_binpacking\\GAIsolution1000\\test10.txt";
		result_files[10] = "D:\\data_for_binpacking\\GAIsolution1000\\test11.txt";
		result_files[11] = "D:\\data_for_binpacking\\GAIsolution1000\\test12.txt";
		String[] chromosome_files = new String[12];
		chromosome_files[0] = "D:\\data_for_binpacking\\GAIresults1000\\testing1bin1.txt";
		chromosome_files[1] = "D:\\data_for_binpacking\\GAIresults1000\\testing2bin1.txt";
		chromosome_files[2] = "D:\\data_for_binpacking\\GAIresults1000\\testing3bin1.txt";
		chromosome_files[3] = "D:\\data_for_binpacking\\GAIresults1000\\testing4bin1.txt";
		chromosome_files[4] = "D:\\data_for_binpacking\\GAIresults1000\\testing5bin1.txt";
		chromosome_files[5] = "D:\\data_for_binpacking\\GAIresults1000\\testing6bin1.txt";
		chromosome_files[6] = "D:\\data_for_binpacking\\GAIresults1000\\testing7bin1.txt";
		chromosome_files[7] = "D:\\data_for_binpacking\\GAIresults1000\\testing8bin1.txt";
		chromosome_files[8] = "D:\\data_for_binpacking\\GAIresults1000\\testing9bin1.txt";
		chromosome_files[9] = "D:\\data_for_binpacking\\GAIresults1000\\testing10bin1.txt";
		chromosome_files[10] = "D:\\data_for_binpacking\\GAIresults1000\\testing11bin1.txt";
		chromosome_files[11] = "D:\\data_for_binpacking\\GAIresults1000\\testing12bin1.txt";
		for (int i = 0; i < result_files.length; i++) {
			TestChromosome TC = new TestChromosome();
			TC.start(result_files[i],chromosome_files[i]);
		}*/
	}
}

