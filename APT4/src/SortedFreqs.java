import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SortedFreqs {
	private HashMap<String, Integer> bank;
	public SortedFreqs(){
		bank = new HashMap<String, Integer>();
		
	}
	private void initializeBank(String[] data) {
		bank.clear();
		for (int i = 0 ; i < data.length ; i++) {
			if (bank.containsKey(data[i])) {
				bank.put(data[i], bank.get(data[i]) + 1);
			} else {
				bank.put(data[i],  1);
			}	
		}

	}
	public int[] freqs(String[] data) {
		initializeBank(data);
		Arrays.sort(data);
		HashSet<String> set1 = new HashSet<String>();
		for (int i = 0 ; i < data.length ; i++){
			set1.add(data[i]);
		}
		String[] ar1 = new String[set1.size()]; 
		ar1 = set1.toArray(ar1);
		Arrays.sort(ar1);
		int[] answer = new int[set1.size()];
		for (int i = 0 ; i < set1.size() ; i++) {
			answer[i] = bank.get(ar1[i]);
		}
		return answer;
	}
}
