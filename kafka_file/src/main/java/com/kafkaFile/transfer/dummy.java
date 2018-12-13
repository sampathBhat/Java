package com.kafkaFile.transfer;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class dummy {

	private static final String FILENAME = "D:/userdata/sampaths/dummy.txt";

	public static void main(String[] args) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			
			String content = "This is the content to write into file\n";

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			int i=Integer.MAX_VALUE;
			while(true){
			bw.write(content);
			bw.flush();
			i--;
			if(i<0)
				break;
			}
			System.out.println("Done");

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
  
}




