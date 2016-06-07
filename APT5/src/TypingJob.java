import java.util.ArrayList;
import java.util.Arrays;



public class TypingJob {

	private int mean;
	private int[] T = new int[3];


	public int bestTime(int[] pages) {
		T[0] = 0;
		T[1] = 0;
		T[2] = 0;
		Arrays.sort(pages);
		mean = calcMean(pages);
		/*
		if (pages.length < 7){
			ArrayList<Integer> M = new ArrayList<Integer>();
			for (int i = pages.length - 1 ; i > -1 ; i--){
				M.add(pages[i]);
			}
			while (M.size() != 6){
				M.add(0);
			}
			for (int i = 0 ; i < 3 ; i++){
				ArrayList<Integer> n = new ArrayList<Integer>();
				n.clear();
				n.add(M.get(i));
				n.add(M.get(5-i));
				a.add(n);
			}
			return findMax(a);
		} else {
			
			for (int i = 0 ; i < 3 ; i++){
				ArrayList<Integer> n = new ArrayList<Integer>();
				n.clear();
				n.add(pages[pages.length - i - 1]);
				a.add(n);
			}
			 
			return best(pages,0,0,0,0);				
		}
		*/
		return best(pages,0);	
	}
	private int best(int[] pages, int index){
		if (index == pages.length){
			return findMax(T);
		}
		//System.out.println(T[0] + " "+ T[1] + " " + T[2] + "  " + pages[pages.length - index - 1] + "  " + mean);
		int flag = 0;
		for (int k = 0; k < 3; k++){
			for (int i = 0 ; i < pages[pages.length - 1] ; i++){
				if (T[k] + pages[pages.length - index - 1] == mean - i){
					T[k] += pages[pages.length - index - 1];
					flag = 1;
					break;
					
				}
			}
			if (flag == 1){
				flag = 0;
				break;
			}
			
			if (k == 2){
				apply2Min(pages[pages.length - index - 1]);
			}
		}
		
		return best(pages,index+1); 
	}
	private int findMax(int[] t) {
		int max = 0;
		for (int k = 0 ; k < t.length ; k++){
			if (t[k] > max){
				max = t[k];
			}
		}
		for (int k = 0 ; k < t.length ; k++){
			if (t[k] == max){
				return t[k];
			}
		}
		return 0;
	}
	private void apply2Min(int q) {
		int min = 100000;
		for (int k = 0 ; k < T.length ; k++){
			if (T[k] + q < min){
				min = T[k] + q;
			}
		}
		for (int k = 0 ; k < T.length ; k++){
			if (T[k] + q == min){
				T[k] += q;
				break;
			}
		}
		
	}
	private int calcMean(int[] pages) {
		int sum = 0;
		for (int i = 0 ; i < pages.length ; i++){
			sum += pages[i];
		}
		if (sum % 3 == 0) {
        	return sum / 3;
        } else {
        	return 1 + sum / 3;
        }
	}

	private int sum(ArrayList<Integer> aa){
		int sum = 0;
		for (int i = 0 ; i < aa.size() ; i++){
			sum += aa.get(i);
		}
		return sum;
	}

	private int findMax(ArrayList<ArrayList<Integer>> A) {
		int max = 0;
		for (int k = 0 ; k < A.size(); k++) {
			if (sum(A.get(k))>max){
				max = sum(A.get(k));
			}
		}
		return max;
	}

	public static void main(String[] args) { // Remember to change method to
		int[] a = {3, 4, 5, 6, 7, 8};
		int[] b = {10, 10, 9, 5, 3, 3, 3};
		int[] c = {100,100,100,100,100,100,100,100,100,100};
		TypingJob TR = new TypingJob();
		System.out.println("answer " + TR.bestTime(a));
		System.out.println("answer " + TR.bestTime(b));
		System.out.println("answer " + TR.bestTime(c));
	}

}
