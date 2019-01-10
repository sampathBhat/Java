package scu.coen283.assign2;

/*
 * Monitor Class where the pick fork, put fork and test is synchronized
 * */
public class Monitor {
	private static int numOfPhilosphers;
	private static String[] state;
	private static final String syncLock = "MonitorLock";
	private static int[] stateCount;

	public static void init() { // Init method to initialize the monitor
		numOfPhilosphers = DiningPhilMonitor.getNumOfPhilospher();
		state = new String[numOfPhilosphers];
		stateCount = new int[numOfPhilosphers];
		for (int i = 0; i < numOfPhilosphers; i++) {
			state[i] = Philosphers.THINKING;
		}
	}

	public static void pickFork(int whichPhilospher) { // multi Thread safe pick fork method
		synchronized (syncLock) { // called when phil is hungry
			state[whichPhilospher] = Philosphers.HUNGRY;
			testAvailibiltyOfForks(whichPhilospher);
		}
	}

	public static void putFork(int whichPhilospher) { // multi Thread safe put fork method
		synchronized (syncLock) { // called when phil finished eating or didn't get chance t eat
			state[whichPhilospher] = Philosphers.THINKING;
			int left = (whichPhilospher - 1) % numOfPhilosphers;
			if (left < 0)
				left += numOfPhilosphers;
			testAvailibiltyOfForks(left);
			testAvailibiltyOfForks((whichPhilospher + 1) % numOfPhilosphers);
		}
	}

	private static void testAvailibiltyOfForks(int whichPhilospher) { // Test method to check if can take two forks
		synchronized (syncLock) {
			int left = (whichPhilospher - 1) % numOfPhilosphers;
			if (left < 0) {
				left = left + numOfPhilosphers;
			}
			int right = (whichPhilospher + 1) % numOfPhilosphers;
			if (state[whichPhilospher] == Philosphers.HUNGRY && state[left] != Philosphers.EATING
					&& state[right] != Philosphers.EATING) {
				state[whichPhilospher] = Philosphers.EATING;
				stateCount[whichPhilospher]++;
			}
		}
	}

	public static String getThreadState(int index) {
		return state[index];
	}

	public static int[] getStateCount() {
		return stateCount;
	}
}