package scu.coen283.assign1;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author sampath Shreekantha
 * Process the segment of file and calculates the frequency of word in the particular segment.
 * Output the intermediate result to console
 *
 */
public class LineConsumer extends Thread {
	private ConcurrentLinkedQueue<String> linesPerThread;
	private StringBuilder sb = new StringBuilder();
	private HashMap<String, Integer> resultPerThread;
	private int myindex;
	public LineConsumer(final int index, final ConcurrentLinkedQueue<String> lines, final HashMap<String, Integer> result) {
		super();
		this.linesPerThread = lines;
		this.resultPerThread = result;
		this.myindex= index;
	}

	@Override
	public void run() {
		String line;
		while (!linesPerThread.isEmpty()) {
			line = linesPerThread.poll();
			wordFrequency(line);
		}
		printIndividualResult();
	}

	private void wordFrequency(String line) {
		char[] l = line.toCharArray();
		for (char c : l) {
			if (Character.isLetterOrDigit(c) || c == '\'' || c == '-') {
				sb.append(c);
			} else {
				if (sb.length() != 0) {
					addToMap(sb.toString());
				}
				sb.setLength(0);
			}
		}
	}

	private void addToMap(String s) {
		int count = 1;
		if (this.resultPerThread.containsKey(s)) {
			count = this.resultPerThread.get(s);
			count++;
			resultPerThread.put(s, count);
		} else {
			resultPerThread.put(s, count);
		}
	}
	private void printIndividualResult() {
			System.out.println("The word frequency result from thread " + this.myindex + " is \n" + resultPerThread.entrySet());
	}
}
