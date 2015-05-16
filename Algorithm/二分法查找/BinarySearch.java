public class BinarySearch {
	public static int search(int [] sz, int target) {
		int h = sz.length-1;
		int l = 0;
		
		while(l<=h){
			int mid = (h+l)/2;
			if(sz[mid]>target){
				h = mid-1;
			}else if(sz[mid]<target){
				l = mid+1;
			}else return mid;
			
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int [] sz = new int[]{1,3,4,5,8,9,33,56,88};
		for(int a:sz){
			System.out.println(search(sz,a));
		}
	}
}
