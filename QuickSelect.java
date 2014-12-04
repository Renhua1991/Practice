
public class QuickSelect {
	public static Comparable select(Comparable[] a, int k){
		StdRandom.shuffle(a);
		int lo = 0, hi = a.length - 1 ;
		while(hi > lo){
			int i = partition(a, lo, hi);
			if(i < k) lo = i+1;
			else if(i > k) hi = i - 1;
			else break;
		}
		return a[k];
	}
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
	
	public static void exchange(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) <0;
	}

	public static void main(String[] args){
		Comparable[] a = {3,6,4,9,1,0,5,2,3,4,7,4,2,8};
		int k = 9;
		select(a, k);
		System.out.println(a[k]);
	}
}
