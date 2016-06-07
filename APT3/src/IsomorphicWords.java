import java.util.*;

public class IsomorphicWords {
	private HashMap<Character,ArrayList<Integer>> base;
	private HashMap<Character,ArrayList<Integer>> test;

	public IsomorphicWords() {
		base = new HashMap<Character, ArrayList<Integer>>();
		test = new HashMap<Character, ArrayList<Integer>>();
	}
	public int countPairs(String[] words) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int answer = 0;

		for (int i = 0 ; i < words.length ; i++){
			indexes.add(i);
		}
		for (int i = 0 ; i < words.length ; i++){
			convertToMap(base, words[i]);
			indexes.set(i,-1);
			for (int j = 0 ; j < indexes.size() ; j++){
				if (indexes.get(j)!=-1){
					convertToMap(test, words[indexes.get(j)]);
					for (int k = 0 ; k < words[i].length(); k++) {
						char c1 = words[i].charAt(k);
						char c2 = words[j].charAt(k);
						if (!((base.get(c1)).equals(test.get(c2)))) {
							break;
						} else if (k == (words[i].length() - 1)) {
							answer += 1;
						}
					}
				} else {
					continue;
				}
			}
		}
		return answer;
	}

	private void convertToMap(HashMap<Character, ArrayList<Integer>> map, String str) {
		map.clear();
		
		for (int i = 0 ; i < str.length(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(i));
				map.put(str.charAt(i), temp);
			} else {
				ArrayList<Integer> temp2 = map.get(str.charAt(i));
				temp2.add(i);
				map.put(str.charAt(i), temp2);
			}
		}
	}
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		String[] a = {"aa", "ab", "bb", "cc", "cd"};
		System.out.println(countPairs(a));
	}
	*/
}
