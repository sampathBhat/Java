package scu.coen283.assign1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author sampath shreekantha
 *  Reads the file and segments it into N parts
 */
public class FileReadThread extends Thread {

	private List<ConcurrentLinkedQueue<String>> lines;
	private BufferedReader br;
	private static boolean isFileRead = false;

	public FileReadThread(List<ConcurrentLinkedQueue<String>> lines, BufferedReader br) {
		super();
		this.lines = lines;
		this.br = br;
	}

	@Override
	public void run() {
		try {
			int numOfThreads = WordcountMain.getNumofThreads();
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				lines.get(i % numOfThreads).offer(line);
				i++;
			}
			isFileRead = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isFileRead() {
		return isFileRead;
	}

}
