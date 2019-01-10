import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CountInversions {
	public static long result = 0;

	// Complete the countInversions function below.
	static long countInversions(int[] arr) {
		result = 0;
		sort(arr, arr.length);
		return result;
	}

	private static int[] merge(int[] src, int[] left, int[] right) {
		int i = 0, j = 0;
		for (int k = 0; k < src.length; k++) {
			if (i >= left.length) {
				src[k] = right[j++];
			} else if (j >= right.length) {
				src[k] = left[i++];
			} else if (left[i] > right[j]) {
				src[k] = right[j++];
				result += left.length - i;
			} else {
				src[k] = left[i++];
			}
		}
		return src;

	}

	private static int[] sort(int[] src, int n) {
		if (n < 2)
			return src;
		int mid = n / 2;
		int[] left = new int[mid];
		int[] right = new int[n - mid];
		for (int i = 0; i < left.length; i++) {
			left[i] = src[i];
		}
		for (int j = 0; j < right.length; j++) {
			right[j] = src[mid + j];
		}

		return merge(src, sort(left, mid), sort(right, n - mid));

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] arr = new int[n];

			String[] arrItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < n; i++) {
				int arrItem = Integer.parseInt(arrItems[i]);
				arr[i] = arrItem;
			}

			long result = countInversions(arr);

			bufferedWriter.write(String.valueOf(result));
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
