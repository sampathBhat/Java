package scu.coen283.assign2;

public class DiningPhilMonitor {
	private static int numOfPhilospher = 5; // Default value is 5. Could be modified from commnad line argument.
	public static final long TIMELIMIT = 5000; // Default value for run time

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			numOfPhilospher = Integer.valueOf(args[0]);
		}
		Philosphers[] philosphers = new Philosphers[numOfPhilospher];
		Thread[] threads = new Thread[numOfPhilospher];
		Monitor.init();
		for (int i = 0; i < numOfPhilospher; i++) { // Create Philosopher Threads
			philosphers[i] = new Philosphers();
			Thread t = new Thread(philosphers[i], "Philospher-" + i);
			threads[i] = t;
			threads[i].start();
		}
		for (Thread t : threads) { // main thread wait until the philosopher thread complete their job
			t.join();
		}
		int[] statecount = Monitor.getStateCount(); // Print out statistics
		for (int i = 0; i < statecount.length; i++) {
			System.out.println("Philospher-" + i + " spent " + statecount[i] / 2 + " seconds eating out of 5s");
		}

	}

	public static int getNumOfPhilospher() {
		return numOfPhilospher;
	}

}
