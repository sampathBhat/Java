import java.util.Scanner;

public class BubbleSort {

	// Complete the countSwaps function below.
	static int[] countSwaps(int[] a) {
		int n = a.length;
		int temp = 0;
		int[] res = new int[3];
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n - 1; j++) {
				// Swap adjacent elements if they are in decreasing order
				if (a[j] > a[j + 1]) {
					temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
					res[0]++;
				}
			}

		}
		res[1] = a[0];
		res[2] = a[n - 1];
		return res;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] a = new int[n];

		String[] aItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int aItem = Integer.parseInt(aItems[i]);
			a[i] = aItem;
		}

		int[] res = countSwaps(a);
		System.out.println("Array is sorted in " + res[0] + " swaps.");
		System.out.println("First Element: " + res[1]);
		System.out.println("Last Element: " + res[2]);
		scanner.close();
	}
}
