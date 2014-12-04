
public class MergeSort {
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
		for(int k = lo; k <= hi; k++){
			aux[k] = a[k];
		}
		
		int i =lo, j= mid + 1;
		for(int k =lo; k<=hi; k++){
			if(i>mid) a[k] = aux[j++];
			else if (j>hi) a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
		
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		if(!less(a[mid+1],a[mid])) return;      //improvement: stop if already sorted.
		merge(a, aux, lo, mid, hi);
	}
	
	public static void sort(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
	}

	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) <0;
	}
	
	public static void main(String[] args){
		Comparable[] a = {3,6,4,9,1,0,5,2,3,4,7,4,2,8};
		Comparable[] aux = new Comparable[a.length];
		sort(a,aux,0,a.length-1);
		
		for(int i=0; i< a.length;i++){
			System.out.println(a[i]);
			
		}
	}
}
