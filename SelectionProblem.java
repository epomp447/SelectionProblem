package SelectionProblem;

/*this class implements the simultaneous min and max 
 * and it also implements the randomized selection algorithm
 */

public class SelectionProblem {
	public static int min;
	public static int max;
	public static int numComps;
	public static int numSwaps;

	public static void getMinMax(int arr[], int n) {
		int i;
		if (n % 2 == 0) {
			numComps++;
			if (arr[0] > arr[1]) {
				max = arr[0];
				min = arr[1];
				numComps++;
			} else {
				min = arr[0];
				max = arr[1];
				numComps++;
			}
			i = 2; /* set the starting index for loop */
		}
		/*
		 * If array has odd number of elements then initialize the first element as
		 * minimum and maximum
		 */
		else {
			min = arr[0];
			max = arr[0];
			i = 1; /* set the starting index for loop */
		}
		/*
		 * In the while loop, pick elements in pair and compare the pair with max and
		 * min so far
		 */
		while (i < n - 1) {
			
			if (arr[i] > arr[i + 1]) {
				if (arr[i] > max)
					max = arr[i];
				if (arr[i + 1] < min)
					min = arr[i + 1];
			} else {
				if (arr[i + 1] > max)
					max = arr[i + 1];
				if (arr[i] < min)
					min = arr[i];
			}
			i += 2; // Increment the index by 2, two elements are processed in loop

		}
		System.out.println("\nMinimum element is " + min);
		System.out.println("\nMaximum element is " + max);
	}

	/*
	 * This function returns i'th smallest element in arr[p..r] using QuickSort
	 */
	public static int randomizedSelect(int arr[], int p, int r, int i) {

		// If i is equal to number of elements in arr
		if (p == r) {
			return arr[p];
		}

		if (i > 0 && i <= r - p + 1) {
			int q = partition(arr, p, r);
			int k = q - p + 1;// number of elements in the low side of partition + pivot

			if (i == k) { // the pivot value is the answer
				return arr[q];
			}
			if (i < k)
				return randomizedSelect(arr, p, q - 1, i);

			return randomizedSelect(arr, q + 1, r, i - k);
		}

		return Integer.MAX_VALUE;// if i<0 then i is invalid
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

	public static void printArray(int arr[]) {
		int n = arr.length;
		System.out.print("\tarr[] = { ");
		for (int i = 0; i < n; ++i) {

			System.out.print(arr[i] + " ");

		}
		System.out.print("}\n");
	}

	public static void main(String[] args) {
		int arr[] = { 1000, 11, 445, 1, 330, 3000 };

		getMinMax(arr, arr.length);

		int arr1[] = { 12, 3, 5, 7, 4, 19, 26 };
		int i = 3;

		System.out.print("\ni = " + i + "\ni'th smallest element is " + randomizedSelect(arr1, 0, arr1.length - 1, i));

	}

}

/*
 * Output: Minimum element is 1
 * 
 * Maximum element is 3000
 * 
 * i = 3 i'th smallest element is 5
 * 
 */
