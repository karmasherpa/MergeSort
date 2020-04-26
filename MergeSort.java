
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSort<V extends Comparable<? super V>> {
	
	V[] arr;
	public V[] sort() {
		return sort(arr);
	}

	@SuppressWarnings("unchecked")
	public V[] sort(V[] arr) {
		
		int size = arr.length;

		if (1 == size) {
			return arr;
		} else if (2 == size) {
			if (0 < arr[0].compareTo(arr[1])) {
				V temp = arr[0];
				arr[0] = arr[1];
				arr[1] = temp;
				return arr;
			}
		} else {
			V[] arr1 = (V[]) Array.newInstance(arr.getClass().getComponentType(), size / 2);
			int i = 0;
			for (; i < arr1.length; i++) {
				arr1[i] = arr[i];
			}

			V[] arr2 = (V[]) Array.newInstance(arr.getClass().getComponentType(), size - i);
			for (int j = 0; i < arr.length; j++, i++) {
				arr2[j] = arr[i];
			}
			ExecutorService executorService = null;
			executorService = Executors.newFixedThreadPool(2);

			V[] a1 = arr1;
			Future<V[]> future1 = executorService.submit(new Callable<V[]>() {
				public V[] call() throws Exception {
					return sort(a1);
				}
			});

			V[] a2 = arr2;
			Future<V[]> future2 = executorService.submit(new Callable<V[]>() {
				public V[] call() throws Exception {
					return sort(a2);
				}
			});

			try {
				arr1 = future1.get();
				arr2 = future2.get();
				arr = merge(arr1, arr2);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}finally{
			executorService.shutdown();
		}}

		return arr;
	}

	@SuppressWarnings("unchecked")
	private V[] merge(V[] arr1, V[] arr2) {
		int i = 0, j = 0, k = 0;
		V[] arrMerged = (V[]) Array.newInstance(arr.getClass().getComponentType(), arr1.length + arr2.length);
		while (i < arr1.length && j < arr2.length) {
			if (0 > (arr1[i]).compareTo(arr2[j])) {
				arrMerged[k] = arr1[i];
				i++;
			} else {
				arrMerged[k] = arr2[j];
				j++;
			}
			k++;
		}
		while (i < arr1.length) {
			arrMerged[k++] = arr1[i++];
		}
		while (j < arr2.length) {
			arrMerged[k++] = arr2[j++];
		}
		return arrMerged;
	}

	public MergeSort(V[] array) {
		this.arr = array;
	}
}
