import java.util.Arrays;
import java.util.Comparator;


public class TheBestName {

	public class LengthAlpha implements Comparator<String> {
		@Override
		public int compare(String str1, String str2) {	
			if (str1.equals("JOHN")) {
				return -1;
			} else if (str2.equals("JOHN")) {
				return 1;
			} else {	
				int score1 = calcScore(str1);
				int score2 = calcScore(str2);
				if (score2 - score1 != 0) { 
					return score2 - score1;
				} else {
					return str1.compareTo(str2);
				}
			}
		}
		private int calcScore(String str1) {
			int score = 0;
			for (int j = 0 ; j < str1.length(); j++) {
    			score += (Character.getNumericValue(str1.charAt(j)) - 9);
    		}
			return score;
		}
	}
	public String[] sort(String[] names) {
		Arrays.sort(names, new LengthAlpha());
		return names;
    }

	/*
	public static void main(String[] args) {
		TheBestName test = new TheBestName();
		String[] input1 = {"CBA", "ABC", "BCA", "JOHN"};
		System.out.println(Arrays.toString(input1));
		String[] output1 = test.sort(input1);
		System.out.println(Arrays.toString(output1));
		
		String[] input2 = {"JOHN", "PETR", "ACRUSH"};
		System.out.println(Arrays.toString(input2));
		String[] output2 = test.sort(input2);
		System.out.println(Arrays.toString(output2));
	}
	*/
}
