import java.util.Arrays;
import java.util.Random;


public class Assignment1 {
	public static void main(String args[]) {

		System.out.println("Creating an array of 100 Integer objects...");
		Integer[] iArr = new Integer[100];
		System.out.println("Created an array of 100 Integer objects.");

		Random random = new Random();
		for (int i = 0; i < iArr.length; i++) {
			iArr[i] = 1 + random.nextInt(10_000);
		}
		System.out.println("Integer array before sorting:");
		System.out.println(Arrays.toString(iArr));

		MergeSort<Integer> mergeSort = new MergeSort<>(iArr);
		iArr = mergeSort.sort();

		System.out.println("Integer array after sorting:");
		System.out.println(Arrays.toString(iArr));
	}
}
