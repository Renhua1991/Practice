
public class ShellSort {
	public static void sort(Comparable[] a) {
		int N = a.length;

		int h = 1;
		while (h < N / 3) {
			h = h * 3 + 1;
		}

		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
					exch(a, j, j - h);
			}
			h = h / 3;
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

	public static void main(String[] args) {
		Comparable[] a = { 8, 7, 4, 3, 54, 1, 3, 56, 893, 63, 2, 6, 8, 35 };
		sort(a);
		for (int k = 0; k < a.length; k++) {
			System.out.println(a[k]);
		}
	}
}
