package sam.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TriplePairs {

	static long triplets(int[] a, int[] b, int[] c) {
		int[] a1 = Arrays.stream(a).sorted().distinct().toArray();
		int[] b1 = Arrays.stream(b).sorted().distinct().toArray();
		int[] c1 = Arrays.stream(c).sorted().distinct().toArray();

		int i = 0, j = 0, k = 0;
		long res = 0;
		while (j < b1.length) {
			while (i < a1.length && a1[i] <= b1[j]) {
				i++;
			}
			while (k < c1.length && c1[k] <= b1[j]) {
				k++;
			}
			res += (long) i * k;
			j++;
		}
		return res;
	}

	private static List<Integer> removeDuplicates(int[] array) {
		List<Integer> res = new ArrayList<>(array.length);
		res.add(array[0]);
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[i - 1])
				res.add(array[i]);
		}
		return res;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] lenaLenbLenc = scanner.nextLine().split(" ");

		int lena = Integer.parseInt(lenaLenbLenc[0]);

		int lenb = Integer.parseInt(lenaLenbLenc[1]);

		int lenc = Integer.parseInt(lenaLenbLenc[2]);

		int[] arra = new int[lena];

		String[] arraItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < lena; i++) {
			int arraItem = Integer.parseInt(arraItems[i]);
			arra[i] = arraItem;
		}

		int[] arrb = new int[lenb];

		String[] arrbItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < lenb; i++) {
			int arrbItem = Integer.parseInt(arrbItems[i]);
			arrb[i] = arrbItem;
		}

		int[] arrc = new int[lenc];

		String[] arrcItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < lenc; i++) {
			int arrcItem = Integer.parseInt(arrcItems[i]);
			arrc[i] = arrcItem;
		}

		long ans = triplets(arra, arrb, arrc);

		bufferedWriter.write(String.valueOf(ans));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
