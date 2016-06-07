import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import java.util.Iterator;

public class ClassScores {	
	public int[] findMode(int[] scores) {
		// first see that there are no duplicates
		Set<Integer> set = new HashSet<Integer>();
		Set<Integer> set3 = new HashSet<Integer>();
		int [] count = new int[scores.length];

		String bigString = "xx";
		String strTemp;
		String strNew;
		int j = 0;
		Arrays.sort(scores);

		for(int i = 0 ; i < scores.length ; i++){
			set.add(scores[i]);
			bigString += scores[i] + "xx";
		}
		if(set.size() == scores.length) {
			return scores;
		} else {
			//now perform actually mode calculation
			//based on CountOccurrences APT
			for(int i = 0 ; i < scores.length ; i++){
				strTemp = "x" + Integer.toString(scores[i]) + "x";
				strNew = bigString.replaceAll(strTemp,"");
				count[i] = (bigString.length() - strNew.length()) / strTemp.length();
			}
			int max = 0;
			for (int i = 0 ; i < count.length ; i++) {
				if (count[i] > max) {
					max = count[i];
				}
			}
			for (int i = 0 ; i < count.length ; i++) {
				if (count[i] == max) {
					set3.add(scores[i]);
				}
			}

			int[] mode = new int[set3.size()];
			j=0;
			Iterator<Integer> iter = set3.iterator();
			while (iter.hasNext()) {
				mode[j] = iter.next();
				j += 1;
			}
			Arrays.sort(mode);
			return mode;
		}

		
	}

	/*
	public static void main (String[] args) {
		// Remember to change method to static
		int [] test = {5, 45, 4, 92, 32, 26, 40, 79, 18, 51, 40, 78, 41, 40, 26, 84, 54, 38, 45, 12, 33, 23, 44, 81, 67, 39, 72, 93, 67, 53, 9, 67, 11, 39, 15, 79, 7, 85, 9, 31, 77, 25, 67, 48};
		System.out.println(findMode(test));
	}
	*/
}

//http://www.tutorialspoint.com/java/java_set_interface.htm