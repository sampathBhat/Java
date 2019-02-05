package sam.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class MaximumSubArray {

	// Complete the maximumSum function below.
	static long maximumSum(long[] a, long k) {

		long[] s = new long[a.length];
		TreeSet<Long> tree = new TreeSet<>();

		s[0] = a[0] % k;
		tree.add(s[0]);
		long result = s[0];

		for (int i = 1; i < a.length; i++) {

			s[i] = (s[i - 1] + a[i]) % k;
			Long v = tree.higher(s[i]);

			if (v == null) {
				result = Math.max(s[i], result);
			} else {
				result = Math.max((s[i] - v + k) % k, result);
			}
			tree.add(s[i]);
		}
		return result;

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int qItr = 0; qItr < q; qItr++) {
			String[] nm = scanner.nextLine().split(" ");

			int n = Integer.parseInt(nm[0]);

			long m = Long.parseLong(nm[1]);

			long[] a = new long[n];

			String[] aItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < n; i++) {
				long aItem = Long.parseLong(aItems[i]);
				a[i] = aItem;
			}

			long result = maximumSum(a, m);

			bufferedWriter.write(String.valueOf(result));
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
