package dataGenerator;

import java.util.Random;

public class Util {
	
	private static Random rand = new Random();
	public static void busySleep(final long nanoSec) {
        long elapsed;
        final long startTime = System.nanoTime();
        do {
            elapsed = System.nanoTime() - startTime;
        } while (elapsed < nanoSec);
    }
	
	 public static long getBusySleepTime(final long numOfPackets) {
	        float temp = 1f / numOfPackets;
	        temp *= Math.pow(10, 9);
	        return Math.round(temp);

	    }
	 
	 public static int getRandomValue(int min, int max) {
		 return rand.nextInt(max)+min;
	 }

}
