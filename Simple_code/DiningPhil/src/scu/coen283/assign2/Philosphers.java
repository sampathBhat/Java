package scu.coen283.assign2;

/*
 * Philosopher class where each philosopher will either think or eat. 
 * */
public class Philosphers implements Runnable {
	public static final String THINKING = "THINKING";
	public static final String EATING = "EATING";
	public static final String HUNGRY = "HUNGRY";
	private boolean runFlag = true;

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		String name = Thread.currentThread().getName();
		int index = getPhilospherIndex(name);
		while (runFlag) { // Make sure program runs for 5 seconds
			Monitor.pickFork(index);
			getPhilospherState(name, index);
			Monitor.putFork(index);
			try {
				Thread.sleep(500); // To emulate that philospher is in a particular state for 0.5s.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			runTimeCalculator(startTime); // Make sure program runs for 5 seconds
		}
	}

	private void getPhilospherState(String name, int index) {
		System.out.println(name + " is " + Monitor.getThreadState(index));
	}

	private int getPhilospherIndex(String name) {
		int index = Integer.valueOf(name.substring(name.indexOf('-') + 1));
		return index;
	}

	private void runTimeCalculator(long startTime) { // Make sure program runs for 5 seconds
		if ((System.currentTimeMillis() - startTime) >= DiningPhilMonitor.TIMELIMIT)
			this.runFlag = false;
	}
}
