package scu.coen283.assign4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {
	private static final int PAGE_REFERENCE_LIMIT = 1000;
	private static final int shift=1<<31;

	public static void createPageReferenceFile() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		Random rn = new Random();
		try {
			File inputFile= new File("resource/pageReference.txt");
			if(inputFile.exists()) {
				inputFile.delete();
			}
			fw = new FileWriter(inputFile);
			bw = new BufferedWriter(fw);
			int count=0;
			while(count<PAGE_REFERENCE_LIMIT) {
				int pageRefer=rn.nextInt(PAGE_REFERENCE_LIMIT)+0;
				bw.write(String.valueOf(pageRefer));
				bw.newLine();
				count++;
			}
			System.out.println("The input page reference is created in location"+inputFile.getAbsolutePath());
			fw.flush();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
				bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public static List<Integer> readPageReferenceFile(){
		File inputFile = new File("resource/pageReference.txt");
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<Integer> pageReferenceList = new ArrayList<>();
		try {
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				pageReferenceList.add(Integer.parseInt(sCurrentLine));
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
		return pageReferenceList;
	}
	public static int setMSB(int i) {
			 i = 1|shift;
			 return i;
	}
	public static int decCounter(int i) {
		if(i==0) {
			return i |= 1 << 31;
			 }
			else {
				i = i/2;
			return i;
			}
				
	}
}
