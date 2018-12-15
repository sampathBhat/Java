package scu.coen283.assign1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sampath Shreekantha 
 *         Starts file read thread and file process thread
 *         and waits for its completion. Outputs the total word frequency on
 *         Console
 */
public class WordcountMain {
	private static int numOfThreads = 3; // Default value of number of threads
	private static List<ConcurrentLinkedQueue<String>> fileLines = new ArrayList<ConcurrentLinkedQueue<String>>(
			numOfThreads);
	private static List<HashMap<String, Integer>> resultTotal = new ArrayList<HashMap<String, Integer>>(numOfThreads);

	public static void main(String[] args) {
		//numOfThreads = Integer.parseInt(args[1]);  
		//File file = new File(args[0]);          //Can uncomment these line to give file path as command line args
		 File file = new File("F:\\Workspace\\Wordcount\\wordcount.txt"); 
		//Can provide the absolute file path here to execute it.
		if (!file.exists()) {
			System.err.println(file.getAbsolutePath() + " does not exist!");
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for (int i = 0; i < numOfThreads; i++) {
				fileLines.add(i, new ConcurrentLinkedQueue<String>());
			}
			FileReadThread fileReadThread = new FileReadThread(fileLines, br); // File read object
			fileReadThread.start(); // Start the file segmenting
			LineConsumer[] lineConsumers = new LineConsumer[numOfThreads];
			for (int i = 0; i < numOfThreads; i++) {
				resultTotal.add(i, new HashMap<String, Integer>());
				LineConsumer lineConsumer = new LineConsumer(i, fileLines.get(i), resultTotal.get(i));
				lineConsumers[i] = lineConsumer;
				lineConsumer.start(); // Start all the file processing threads
			}
			fileReadThread.join(); // wait for file reading thread to complete
			for (LineConsumer lc : lineConsumers) {
				lc.join(); // Wait for file consuming thread to complete
			}
			Map<Object, Integer> finalResult = computeResult();
			System.out.println("The Word Frequency of file is ");
			printTotalResultConsole(finalResult.entrySet()); // output the total word frequency to console
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getNumofThreads() {
		return numOfThreads;
	}

	private static Map<Object, Integer> computeResult() {
		Map<Object, Integer> resultTemp = new HashMap<Object, Integer>();
		resultTemp = Stream.concat(resultTotal.get(0).entrySet().stream(), resultTotal.get(1).entrySet().stream())
				.collect(Collectors.toMap(entry -> (String) entry.getKey(), // The key
						entry -> entry.getValue(), // The value
						// The "merger" as a method reference
						Integer::sum));
		for (int i = 2; i < numOfThreads; i++) {
			resultTemp = Stream.concat(resultTemp.entrySet().stream(), resultTotal.get(i).entrySet().stream())
					.collect(Collectors.toMap(entry -> (String) entry.getKey(), // The key
							entry -> entry.getValue(), // The value
							// The "merger" as a method reference
							Integer::sum));
		}
		return resultTemp;
	}

	private static void printTotalResultConsole(Set<Entry<Object, Integer>> entrySet) {
		for (Entry<Object, Integer> e : entrySet) {
				System.out.println(e);
		}
	}
}
