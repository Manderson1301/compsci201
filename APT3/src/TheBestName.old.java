import java.util.ArrayList;
import java.util.Arrays;

public class TheBestName {
	public String[] sort(String[] names) {
        int[] score = new int[names.length];
        String [] answer = new String[names.length];

        for (int i = 0 ; i < names.length ; i++) {
        	if (names[i].equals("JOHN")){
        		score[i] = 2147483647;
        	} else {
        		for (int j = 0 ; j < names[i].length(); j++) {
        			score[i] += (Character.getNumericValue(names[i].charAt(j)) - 9);
        		}
        	}
        }
        int [] sortedScore = score.clone();
        Arrays.sort(sortedScore);
        int [] sortedScore2 = new int[names.length];
        int count = 0;
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0 ; i < names.length ; i++){
        	indexes.add(i);
        }
        for (int j = 0 ; j < names.length ; j++) {
        	for (Integer i : indexes) {
        		if (score[i] == sortedScore[names.length-1-j]) {
        			answer[count] = names[i];
        			sortedScore2[count] = score[i];
        			indexes.remove(i);
        			
        			count += 1;
        			break;		
        		}
        	}
        }
        //BUBBLE SORT
        boolean swapbol = true;
        while(swapbol) {
            swapbol = false;
            for(int c = 0 ; c < (names.length - 1); c++) {
            	
            	if (sortedScore2[c]==sortedScore2[c+1] && (answer[c].compareTo(answer[c+1]))>0) {
                    String tempS = answer[c];
                    answer[c] = answer[c+1];
                    answer[c+1] = tempS;
                    swapbol = true;
                }
            }
        }
        return answer;
 }
/*
public static void main (String[] args) {
	// Remember to change method to static
	String[] a = {"JOHN", "A", "AA", "AAA", "JOHN", "B", "BB", "BBB", "JOHN", "C", "CC", "CCC", "JOHN"};
	String[] b = sort(a);
	System.out.println(b);
}
*/
}
//http://www.tutorialspoint.com/java/lang/character_getnumericvalue.htm
//http://www.go4expert.com/articles/bubble-sort-algorithm-absolute-beginners-t27883/