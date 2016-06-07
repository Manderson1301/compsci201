
public class BSTcount {
	public long[] F;
	
	public long howMany(int[] values) {
		F = new long[values.length+1];
		F[0] = 1;
		for (int k = 1 ; k < values.length + 1 ; k++){
			F[k] = 0;
		}
        return countTrees(values.length);
  }

	private long countTrees(int length) {
		if (F[length] == 0) {
			long count = 0;
			for (int leftsize = 0; leftsize < length; leftsize++ ) {
				int rightsize = length - leftsize - 1;
				count += countTrees(leftsize) * countTrees(rightsize);
			}
			F[length] = count;
		}
		return F[length];
	}
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		int[] g = {1,2,3};
		System.out.println(howMany(g));
	}
	*/
}