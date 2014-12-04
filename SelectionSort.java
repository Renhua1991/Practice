
public class SelectionSort {

	public static void sort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int k = i + 1; k < a.length; k++) {
				if (less(a[k], a[k + 1])) {
					min = k;
				}
			}
			exch(a, i, min);
		}
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

}
