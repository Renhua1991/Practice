
public class RefineMergeSort {
	private static Comparable[] aux;
	
	private static void merge(Comparable[] a, int lo, int mid, int hi){
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
	
	public static void sort(Comparable[] a){
		int N = a.length;
		aux = new Comparable[N];
		for (int sz=1; sz < N; sz = sz+sz){
			for(int lo=0; lo< N-sz; lo += sz+sz){
				merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
	
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) <0;
	}

	public static void main(String[] args){
		Comparable[] a = {3,6,4,9,1,0,5,2,3,4,7,4,2,8};
		sort(a);
		
		for(int i=0; i< a.length;i++){
			System.out.println(a[i]);
			
		}
	}
}
