import java.util.Arrays;

public class MedianOfMedians {

	private static void qSorter(int[] arr, int low, int high) {

		if (low >= high)
			return;

		if (high - low <= 5) {
			insertionSort(arr);
		}

		if (low < high) {
			/*use the median of three elements
			 * low, (mid), and high and set it as the element to compare with.
			 */


			int a1[] = new int[5];
			int a2[] = new int[5];
			int a3[] = new int[5];

			int gap = (high - low + 1) / 15;
			if (((high - low + 1) + gap) % 15 < gap)
				gap++;

			// System.out.println("Gap = " + gap);

			// Indexes
			int a1I = 0, a2I = 0, a3I = 0, arrayNo = 0;
			for (int i = low; arrayNo < 15; i += gap) {
				if (arrayNo < 5)
					a1[a1I++] = i;
				else if (arrayNo < 10)
					a2[a2I++] = i;
				else
					a3[a3I++] = i;

				arrayNo++;
			}

			System.out.println("Median of " + Arrays.toString(a1));
			System.out.println("Median of " + Arrays.toString(a2));
			System.out.println("Median of " + Arrays.toString(a3));

			int median = median(arr, median(a1,a1[0],a1[2],a1[4]), median(a2,a2[0],a2[2],a2[4]),
					median(arr,a3[0],a3[2],a3[4]));
			
			
			swap(arr, median, low);
			// System.out.println("Ninther = "+a[lo]);
		}

		int lt = low, gt = high, i = low;
		int v = arr[low];

		while (i <= gt) {
			if (arr[i] < v) {
				swap(arr, i++, lt++);
			} else if (arr[i] > v) {
				swap(arr, i, gt--);
			} else
				i++;
			// System.out.println(Arrays.toString(a));
		}

		qSorter(arr, low, lt - 1);
		qSorter(arr, gt + 1, high);
	}

	public static int median(int[] x, int a, int b, int c) {
		int[] median = x;
		insertionSort(median);

		return median[2];
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static void insertionSort(int A[]) {
		System.out.println("Entered Insertion Sort");
		int i, key;
		// Increment Sorted Subarray
		for (int j = 1; j < A.length; j++) {
			key = A[j];
			i = j - 1;
			// Find position for element in subarray
			while (i >= 0 && A[i] > key) {
				A[i + 1] = A[i];
				i = i - 1;
				A[i + 1] = key;
			}
		}
	}
	public static void main(String[] args) {
		// Two arrays to test
		// int a[] = {2,3,5,7,8,3,5,6,8,4,3,2,5,6,7,8};
		int a[] = { 2, 3, 4, 1, 5, 6, 7, 8, 9, 10, 11, 2, 3, 4, 5, 2, 3, 14, 19, 20, 23, 22, 1, 4, 3, 2, 5 };
		qSorter(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		// int lo=0;
		// int hi=9;
		// int gap = (hi - lo + 1) / 9;
		// System.out.println("Gap = " + gap);
		// System.out.println((hi - lo + 1) + gap % 9 );
	}
}