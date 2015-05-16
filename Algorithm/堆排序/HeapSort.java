public class HeapSort {

	public static void main(String[] args) {
		HeapSort heapSort = new HeapSort();
		int[] sz = new int[] { 0, 2, 3, 5, 90, 22, 10, 13 ,23};
		heapSort.merge(sz, sz.length-1);
		for (int i : sz) {
			System.out.print(i + " ");
		}
	}

	public void shift(int sz[], int k, int m) {
		int i = k;
		int j = 2 * k;
		while (j <= m) {
			if (j < m && sz[j] < sz[j + 1])
				j++;

			if (sz[i] > sz[j])
				break;
			else {
				sz[0] = sz[i];
				sz[i] = sz[j];
				sz[j] = sz[0];
				i = j;
				j = 2 * i;
			}

		}
	}

	public void merge(int sz[], int n) {
		for (int i = n / 2; i >= 1; i--)
			shift(sz, i, n);

		for (int i = 1; i < n; i++) {
			sz[0] = sz[1];
			sz[1] = sz[n - i + 1];
			sz[n - i + 1] = sz[0];
			shift(sz, 1,n - i);
		}

	}
}
