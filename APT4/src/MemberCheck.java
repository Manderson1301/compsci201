import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class MemberCheck {
	private HashMap<String, Integer> bank1;
	private HashMap<String, Integer> bank2;
	private HashMap<String, Integer> bank3;
	private HashSet<String> overlapSet;
	
	
	public MemberCheck(){
		bank1 = new HashMap<String, Integer>();
		bank2 = new HashMap<String, Integer>();
		bank3 = new HashMap<String, Integer>();
		overlapSet = new HashSet<String>();
	}
	
	public String[] whosDishonest(String[] club1, String[] club2, String[] club3) {
		initBank(club1, bank1);
		initBank(club2, bank2);
		initBank(club3, bank3);

		add2overlap(bank1,bank2);
		add2overlap(bank2,bank3);
		add2overlap(bank1,bank3);
		
		String[] answer = new String[overlapSet.size()];
		answer = overlapSet.toArray(answer);
		Arrays.sort(answer);
		return answer;
		
		
	}
	
	private void add2overlap(HashMap<String, Integer> bankA, HashMap<String, Integer> bankB) {
		for (String i : bankA.keySet() ) {
			if (bankB.containsKey(i)) {
				overlapSet.add(i);
			}
		}
	}

	public void initBank(String[] club, HashMap<String, Integer> bank) {
		bank.clear();
		for (int i = 0 ; i < club.length ; i++) {
			if (bank.containsKey(club[i])) {
				bank.put(club[i], bank.get(club[i]) + 1);
			} else {
				bank.put(club[i],  1);
			}
		}
	}
}
