import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {
		int r = (int) (Math.random() * 100000000);
		int[] sz = new int[r];
		for (int i = 0; i < sz.length-1; i++) {
			sz[i] = (int)(Math.random() * 100000000);
		}
		long l =System.currentTimeMillis();
		new QuickSort().qsort(sz, 0, r-1);
		System.out.println(System.currentTimeMillis()-l);
		
		
	}

	void qsort(int[] sz, int start, int end) {
		if (start<end) {
			int mid = parrtion(sz, start, end);
			qsort(sz, start, mid - 1);
			qsort(sz, mid + 1, end);

		}
	}

	int parrtion(int[] sz, int start, int end) {
		int i = start;
		int j = end;
		int temp;
		while (i < j) {
			while (i < j && sz[i] < sz[j])
				j--;
			if (i < j) {
				temp = sz[j];
				sz[j] = sz[i];
				sz[i] = temp;
				i++;
			}

			while (i < j && sz[i] < sz[j])
				i++;
			if (i < j) {
				temp = sz[j];
				sz[j] = sz[i];
				sz[i] = temp;
				j--;
			}
		}

		return j;

	}

}
