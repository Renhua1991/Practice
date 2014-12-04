
public class QuickSort {
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j = hi + 1;
		while(true){
			while(less(a[++i], a[lo])){
				if(i == hi) break;
			}
			
			while(less(a[lo], a[--j])){
				if(j == lo) break;
			}
		
			if(i >= j) break;
			exchange(a, i, j);
			
		}
		exchange(a, lo, j);
		return j;	
	}
	
	public static void sort(Comparable[] a){
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}

	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) <0;
	}
	
	public static void exchange(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public static void main(String[] args){
		Comparable[] a = {3,6,4,9,1,0,5,2,3,4,7,4,2,8};
		sort(a);
		
		for(int i=0; i< a.length;i++){
			System.out.println(a[i]);
	
		}
	}
}
