import java.util.Arrays;
import java.util.Random;

/*this class implements the simultaneous min and max 
 * and it also implements the randomized selection algorithm
 */

public class SelectionProblem {
	public static int min;
	public static int max;
	public static int numComps;
	public static int numSwaps;
	public static int n = 50;// Controls size of Array input

	public static void getMinMax(int arr[], int n) {
		int i;
		if (n % 2 == 0) {
			numComps++;
			if (arr[0] > arr[1]) {
				max = arr[0];
				min = arr[1];

			} else {
				min = arr[0];
				max = arr[1];

			}
			numSwaps++;
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
			numComps++;
			if (arr[i] > arr[i + 1]) {
				if (arr[i] > max) {
					numComps++;
					max = arr[i];
					numSwaps++;
				}
				if (arr[i + 1] < min) {
					numComps++;
					min = arr[i + 1];
					numSwaps++;
				}

			} else {
				if (arr[i + 1] > max) {
					numComps++;
					max = arr[i + 1];
					numSwaps++;
				}
				if (arr[i] < min) {
					numComps++;
					min = arr[i];
					numSwaps++;
				}
			}
			i += 2; // Increment the index by 2, two elements are processed in loop

		}
		 System.out.print("\nMinimum element is " + min);
		 System.out.println("\nMaximum element is " + max);
	}

	/*
	 * This function returns i'th smallest element in arr[p..r] using QuickSort
	 */
	public static int randomizedSelect(int arr[], int p, int r, int i) {

		// If i is equal to number of elements in arr
		if (p == r) {
			numComps++;
			return arr[p];

		}

		if (i > 0 && i <= r - p + 1) {
			numComps++;
			int q = randomPartition(arr, p, r);
			int k = q - p + 1;// number of elements in the low side of partition + pivot

			if (i == k) { // the pivot value is the answer
				numComps++;
				return arr[q];
			} else if (i < k) {
				numComps++;
				return randomizedSelect(arr, p, q - 1, i);
			} else {
				return randomizedSelect(arr, q + 1, r, i - k);
			}
		}

		return Integer.MAX_VALUE;// if i<0 then i is invalid
	}
	// Picks a random pivot element between left and right and 
    // partitions arr[left..right] arount the randomly picked 
    // element using partition()
   public static int randomPartition(int arr[], int left, int right)
   {
	 // System.out.println(arr[arr.length-1] );
	  int n = right-left+1;
      int pivot = (int)(Math.random()) * (n-1);
      swap(arr, left + pivot, right);
      return partition(arr, left, right);
        
    }
   public static void swap(int arr[], int i, int j)
   {
       int temp = arr[i];
       arr[i] = arr[j];
       arr[j] = temp;
       numSwaps++;
       
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
				numSwaps++;
				
			}
			numComps++;
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		numSwaps++;
		return i + 1;
	}

	
		public static int[] almostSort(int arr[], int n) {
			System.out.print("\n Almost sorted:");
			
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

	public static void main(String[] args) {
		// int arr[] = { 1000, 11, 445, 1, 330, 3000 };
		for (int x = 0; x < 1; x++) {
			
			int[] arr = new int[n];
			Random rand = new Random();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = rand.nextInt(500);
			}
			//almostSort(arr, n);
			numComps = 0;
			numSwaps = 0;
			getMinMax(arr, arr.length);
			
			//int arr[] = { 12, 3, 4,5,5,5, 7, 4, 19, 26 };
			//System.out.println("\n" + Arrays.toString(arr));
			int i = 3;
			System.out.print("\ni = " + i + "\ni'th smallest element is " + randomizedSelect(arr, 0, arr.length - 1, i));
			// randomizedSelect(arr, 0, arr.length - 1, i);
			System.out.println("\n" + Arrays.toString(arr));
			System.out.println("\n\t\tSwaps: " + numSwaps);
			System.out.println("\t\tComparisons: " + numComps);

		}
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