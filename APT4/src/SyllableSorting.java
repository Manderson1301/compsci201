import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class SyllableSorting {
	private HashSet<Character> vowels;
	public SyllableSorting(){
		vowels = new HashSet<Character>(); 
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}

	public class endSort implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			String[] a1 = convertIt(o1);
			String[] a2 = convertIt(o2);
			
			String[] a1un = new String[a1.length];
			String[] a2un = new String[a2.length];
			
			for (int i = 0 ; i < a1.length ; i++) {
				a1un[i] = a1[i];
			}
			for (int i = 0 ; i < a2.length ; i++) {
				a2un[i] = a2[i];
			}
			
			Arrays.sort(a1);
			Arrays.sort(a2);

			if (!Arrays.equals(a1, a2)){
				int iterLen;
				int lenDif = (a1.length - a2.length);
				if (lenDif < 0) {
					iterLen = a1.length;
				} else {
					iterLen = a2.length;
				}

				for (int i = 0 ; i < iterLen ; i++) {
					if (a1[i].compareTo(a2[i]) != 0) {
						return a1[i].compareTo(a2[i]);
					}
				}
				if (lenDif <0) {
					return -1;
				} else {
					return 1;
				}
			} else {
				for (int i = 0 ; i < a1un.length ; i++) {
					if (a1un[i].compareTo(a2un[i]) != 0) {
						return a1un[i].compareTo(a2un[i]);
					}
				}
			}
			return o1.compareTo(o2);
		}
	}

	public String[] sortWords(String[] words) {
		Arrays.sort(words, new endSort());
		return words;
	}

	public String[] convertIt(String o1) {
		
		ArrayList<String> a1  = new ArrayList<String>();
		int counter = 0;
		for (int i = 1 ; i < o1.length(); i++) {
			if ( vowels.contains(o1.charAt(i)) ) {
				if (i+1 != o1.length()) {
					if ( !vowels.contains(o1.charAt(i+1)) ){
						a1.add(o1.substring(counter,i+1));
						counter = i + 1;
					}
				} else {
					a1.add(o1.substring(counter));
				}
			}
		}
		
		String[] end = new String[a1.size()];
		end = a1.toArray(end);
		return end;
	}
	
	public static void main(String[] args) {
		SyllableSorting test = new SyllableSorting();
		String[] words = {"maga", "gamayawa"};
		String[] answer = test.sortWords(words);
		System.out.println(answer[0] + " " + answer[1]);
	
	}
	
}
