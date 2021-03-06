import java.util.Arrays;
import java.util.Random;


public class MediansV5 {
	public static int n = 50;// Controls size of array
	public static int numComps;
	public static int numSwaps;

	public static void main(String[] args) {
		int A[] = new int[n];

		Random rand = new Random();
		for (int i = 0; i < A.length; i++) {
			A[i] = rand.nextInt(200);
		}
		// System.out.println("\n\n unsorted array:");
		// System.out.println("\n\t" + Arrays.toString(arr));
		// System.out.println();
		// int[] B = arr;
		// for (int j = 0; j < arr.length; j += 5) {
		// B[i] = arr[5 * ((i / 5) + 1) - i % 5 - 1];

		// Arrays.sort(B, j, j+5);
		// }
		// Arrays.sort(B, 0, 4);
		// System.out.println("\nB[]= " + Arrays.toString(B));

		// medianPivot(arr, 0, n - 1);
		// System.out.println("\n\n sorted array:");
		// System.out.println("\n\t" + Arrays.toString(arr));

		//int[] A = { 7, 35, -2, 4, 16, 1, 0, 9, -10, 3, 45, 11, 10, 100, -5,6 };
		// int[] A = { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20, 16, 17,
		// 19, 18, 88, 22, -1, 16, 14 };
		//almostSort(A,n);
		System.out.println("A.length= " + A.length);
		
		System.out.println("\n**The median is " + median(A));
		System.out.println("\n unsorted array:");
		System.out.println("\n\t" + Arrays.toString(A));
		medianPivot(A, 0, A.length - 1);
		System.out.println("\nsorted array:");
		System.out.println("\n\t" + Arrays.toString(A));

		// System.out.println("\n\t case for n%5!=0 ");
		// System.out.println(Arrays.toString(B));
		// System.out.println("the median is " + median2(B));
		// // System.out.println(Arrays.toString(B));
		// median2(B);
		// // medianPivot(B, 0, B.length - 1);
		System.out.println("\n\tSwaps: " + numSwaps);
		System.out.println("\tComparisons: " + numComps);
	}

	public static int median2(int[] A, int n) {
		int[] lastSubA = A;// Arrays.copyOfRange(A, A.length - n, A.length);

		Arrays.sort(lastSubA);
		int medianOfLastSubA = lastSubA[0];
		System.out.println(" " + Arrays.toString(lastSubA));
		if (lastSubA.length == 3 || lastSubA.length == 4) {
			medianOfLastSubA = lastSubA[1];
			numComps++;
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
			numComps++;
			// A = Arrays.copyOfRange(A, 0, A.length - offSet);
		}
		A = Arrays.copyOfRange(A, 0, A.length - offSet);

		// System.out.println(Arrays.toString(lastSubA));
		int[] mediansOfSubAs = new int[numSubArrays];
		int count = numSubArrays;
		System.out.println("\nnumSubarrays = " + numSubArrays);

		System.out.println("\nA[]= " + Arrays.toString(A));
		// System.out.println("Count: ");
		// System.out.println("mediansOfSubAs[]= " + Arrays.toString(mediansOfSubAs));
		int[] B = A;
		for (int j = 0; j < A.length; j += 5) {
			numComps++;
			Arrays.sort(B, j, j + 5);
		}
		System.out.print("\nB[]= " + Arrays.toString(B));
		if (offSet > 0) {
			mediansOfSubAs[0] = median2(lastSubA, lastSubA.length);
			// offSet = 0;
			numComps++;
			int i = 0;

			for (int k = 1; k < numSubArrays; k++) {
				mediansOfSubAs[k] = A[(5 * i) + 2];// Problem indexes are from original A[] and should be taken from a
													// sorted subarray
				numComps++;
				i++;

			}
		} else if (offSet == 0) {
			for (int k = 0; k < numSubArrays; k++) {
				numComps++;
				mediansOfSubAs[k] = A[(5 * k) + 2];// Problem
			}
		}

		System.out.println("\nmediansOfSubAs[]: " + Arrays.toString(mediansOfSubAs));

		insertionSort(mediansOfSubAs);

		System.out.println("\nmediansOfSubAs[]: " + Arrays.toString(mediansOfSubAs));
		int medianOfMedians;

		if (numSubArrays % 2 == 0) {
			medianOfMedians = mediansOfSubAs[mediansOfSubAs.length / 2 - 1];

		} else
			medianOfMedians = mediansOfSubAs[mediansOfSubAs.length / 2];

		numComps++;

		return medianOfMedians;
	}

	public static void medianPivot(int arr[], int low, int high) {
		/*
		 * swap median with the last to serve as pivot, binary search to locate index of
		 * median, swap arr[high] and then hand off to QuickSort
		 */
		
		
		int mid = median(arr);
		int temp = arr[high];
		for (int i = 0; i < arr.length; i++) {
			numComps++;
			if (arr[i] == mid) {
				arr[i] = temp;
				arr[high] = mid;
				numSwaps++;
				break;
			}
		}

		System.out.println("\nswap " + mid + " with arr[high] \n" + Arrays.toString(arr));

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
		numComps++;
	}

	public static void insertionSort(int A[]) {

		int key, i;
		for (int j = 1; j < A.length; j++) {
			key = A[j];
			i = j - 1;

			// compare key to all index positions from A[0] to A[key-1]
			numComps++;
			while (i > -1 && A[i] > key) {
				numComps++;
				A[i + 1] = A[i];
				i = i - 1;
				numSwaps++;
				
			}

			A[i + 1] = key; // reposition key after comparing with A[0] to A[key-1]
			numSwaps++;
		}

	}
	public static int[] almostSort(int arr[], int n) {
		System.out.print("\n Almost sorted:");
		numComps = 0;
		numSwaps = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		Random r = new Random();

		for (int k = n / 50; k > 0; k--) {

			// Pick a random index from 0 to n
			int i = r.nextInt(n);
			int j = r.nextInt(n);

			// Swap arr[i] with the element at arr[j]
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		// Prints the random array
		System.out.println("\n\t" + Arrays.toString(arr));

		return arr;
	}

	public static int partition(int arr[], int low, int high) {
		int pivot = arr[high];

		int i = (low - 1); // index of smaller element

		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			numComps++;
			if (arr[j] <= pivot) {
				i++;
				numComps++;
				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				numSwaps++;
			}

		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		numSwaps++;
		return i + 1;

	}
}