import java.util.Arrays;

public class MediansV2 {

	public static void main(String[] args) {

		// int[] A = { 7, 35, -2, 4, 16, 1, 0, 9, -10, 3, 45, 11, 10, 100, -5 };
		int[] A = { 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		System.out.println("A.length= " + A.length);
		// System.out.println("the median is " + median(A));
		System.out.println("\n unsorted array:");
		System.out.println("\n\t" + Arrays.toString(A));
		// swap(A, 5, 0);
		// QuickSort(A,0,A.length-1);
		medianPivot(A, 0, A.length - 1);
		System.out.println("\nsorted array:");
		System.out.println("\n\t" + Arrays.toString(A));

		// System.out.println("\n\t case for n%5!=0 ");
		// System.out.println(Arrays.toString(B));
		// System.out.println("the median is " + median2(B));
		// // System.out.println(Arrays.toString(B));
		// median2(B);
		// // medianPivot(B, 0, B.length - 1);
	}

	public static int median2(int[] A, int n) {
		n = A.length;

		int offSet = A.length % 5;

		int[] lastSubA = A;// Arrays.copyOfRange(A, A.length - n, A.length);
		int medianOfLastSubA = lastSubA[0];
		System.out.println(" " + Arrays.toString(lastSubA));
		Arrays.sort(lastSubA);

		if (lastSubA.length == 1 || lastSubA.length == 2) {
			medianOfLastSubA = lastSubA[0];
		} else if (lastSubA.length == 3) {
			medianOfLastSubA = lastSubA[1];
		} else if (lastSubA.length == 4) {
			medianOfLastSubA = lastSubA[1];
		}
		// A = Arrays.copyOfRange(A, 0, A.length - offSet);
		// System.out.println(Arrays.toString(A));
		// System.out.println(medianOfLastSubA);
		return medianOfLastSubA;
	}

	public static int median(int[] A) {
		int numSubArrays = A.length / 5;
		int offSet = A.length % 5;
		int[] lastSubA = Arrays.copyOfRange(A, A.length - offSet, A.length);
		if (A.length % 5 != 0) {
			numSubArrays++;

			// A = Arrays.copyOfRange(A, 0, A.length - offSet);
		}
		A = Arrays.copyOfRange(A, 0, A.length - offSet);
		// System.out.println(Arrays.toString(lastSubA));
		int[] mediansOfSubAs = new int[numSubArrays];
		int count = numSubArrays;
		System.out.println("\nnumSubarrays = " + numSubArrays);

		for (int j = 0; j < A.length; j++) {

			int[] temp = Arrays.copyOfRange(A, j, j + 5);

			insertionSort(temp);
			// System.out.println(Arrays.toString(temp));
		}
		System.out.print("A[]= " + Arrays.toString(A));
		// System.out.println("Count: ");
		// System.out.println("mediansOfSubAs[]= " + Arrays.toString(mediansOfSubAs));

		if (offSet > 0) {
			mediansOfSubAs[0] = median2(lastSubA, lastSubA.length);
			//offSet = 0;
			count--;

			int i = 0;
			for (int k = 1; k < numSubArrays; k++) {
				mediansOfSubAs[k] = A[(5 * i) + 2];
				i++;
			}
		}
		if (offSet == 0) {
			for (int k = 0; k < numSubArrays; k++) {
				mediansOfSubAs[k] = A[(5 * k) + 2];
			}
		}
		// System.out.println(Arrays.toString(mediansOfSubAs));

		insertionSort(mediansOfSubAs);

		System.out.println("\nmediansOfSubAs: " + Arrays.toString(mediansOfSubAs));
		int medianOfMedians;

		if (numSubArrays % 2 == 0) {
			medianOfMedians = mediansOfSubAs[mediansOfSubAs.length / 2 - 1];

		} else
			medianOfMedians = mediansOfSubAs[mediansOfSubAs.length / 2];

		// swap with the last to serve as pivot

		return medianOfMedians;
	}

	public static void medianPivot(int arr[], int low, int high) {
		/*
		 * create subarray with low, high, and middle elements in the array sort the
		 * subarray and use index 1 as the median of 3
		 */

		int first = arr[low];
		int last = arr[arr.length - 1];
		int mid = median(arr);
		int temp = arr[high];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == mid) {
				arr[i] = temp;
				arr[high] = mid;
				break;
			}
		}

		System.out.println("swap " + mid + " with arr[high] \n" + Arrays.toString(arr));

		QuickSort(arr, low, high);

	}

	public static void QuickSort(int arr[], int low, int high) {

		if (low < high) {
			int pi = partition(arr, low, high);

			// Recursively sort elements before
			// partition and after partition
			QuickSort(arr, low, pi - 1);
			QuickSort(arr, pi + 1, high);
		}
	}

	public static int[] swap(int[] A, int a, int b) {
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
		return A;
	}

	public static void insertionSort(int A[]) {

		int key, i;
		for (int j = 1; j < A.length; j++) {
			key = A[j];
			i = j - 1;

			// compare key to all index positions from A[0] to A[key-1]
			// num_comparisons++;
			while (i > -1 && A[i] > key) {
				// num_comparisons++;
				A[i + 1] = A[i];
				i = i - 1;
				// num_swaps++;
			}

			A[i + 1] = key; // reposition key after comparing with A[0] to A[key-1]
			// num_swaps++;
		} // end for statement
	}

	public static int partition(int arr[], int low, int high) {
		int pivot = arr[high];

		int i = (low - 1); // index of smaller element

		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot

			if (arr[j] <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;

			}

		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;

	}
}