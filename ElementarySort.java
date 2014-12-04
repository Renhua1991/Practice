
public class ElementarySort {
	private int[] array;

	public ElementarySort(int[] array) {
		this.array = array;
	}

	public static void sort(int[] a) {
		int size = a.length;

		for (int i = 0; i < size; i++) {
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[j - 1]))
					exchange(a, j, j - 1);
				else
					break;

			}
		}
	}

	public static void exchange(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static void main(String[] args) {
		int[] a = { 8, 7, 4, 3, 54, 1, 3, 56, 893, 63, 2, 6, 8, 35 };
		sort(a);
		for (int k = 0; k < a.length; k++) {
			System.out.println(a[k]);
		}

	}

}
