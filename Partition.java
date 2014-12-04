
public class Partition {
	public static void Partition(Comparable[] a, int i, int j){
		if(j <= i) return;
		int lt = i;
		int gt = j;
		int k = i + 1;
		Comparable value = a[lt];
		while(k <= gt){
			if(less(a[k],value)){
				exchange(a, lt++, k++);
			
			}
			else if(less(value, a[k])){
				exchange(a, gt--, k);
			
			}
			else{
				k++;
			}
		}
		
		Partition(a, i, lt-1);
		Partition(a, gt+1, j);
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
		Comparable[] a = {3,5,3,1,4,6,3,2,3,5,8,9,0,6,7,6,5,4,6,3,3,2,8,0,3};
		Partition(a, 0, a.length-1);
		
		for(int i=0; i< a.length;i++){
			System.out.println(a[i]);	
		}
	}
}
