import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class TournamentRanker {
	private HashMap<String, Integer> winsMap;
	private HashMap<String, String> lossMap;
	
	public TournamentRanker(){
		winsMap = new HashMap<String, Integer>();
		lossMap = new HashMap<String, String>();
	}

	public class tourneysort implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			if (winsMap.get(arg0) - winsMap.get(arg1) != 0) {
				return winsMap.get(arg1) - winsMap.get(arg0);
			} else {
				if (lossMap.get(arg0).equals("")) {
					return -1;
				} else if (lossMap.get(arg1).equals("")) {
					return 1;
				} else {
					return compare(lossMap.get(arg0), lossMap.get(arg1));
				}
			}
		}
	}

	private void initializeMaps(String[] names, String[] lostTo) {
		int count = 0;
		winsMap.clear();
		lossMap.clear();
		for (int i = 0; i < names.length; i++) {
			lossMap.put(names[i], lostTo[i]);
			
			count = 1;
			
			if (!lostTo[i].equals("")) {
				
				if (winsMap.containsKey(lostTo[i])) {
					count += winsMap.get(lostTo[i]);
				}
				winsMap.put(lostTo[i], count);
				if (!winsMap.containsKey(names[i])){
					winsMap.put(names[i], 0);
				}
			}
			
		}
	}

	public String[] rankTeams(String[] names, String[] lostTo) {
		initializeMaps(names, lostTo);
		
		Arrays.sort(names, new tourneysort());
		return names;

	}
/*
	public static void main(String[] args) { // Remember to change method to
		String[] names = { "RODDICK", "SCHUETTLER", "FERREIRA", "AGASSI" };

		String[] lost2 = { "SCHUETTLER", "AGASSI", "AGASSI", "" };

		TournamentRanker TR = new TournamentRanker();
		String[] an = TR.rankTeams(names, lost2);
		System.out.println(an[0]);
	}
*/
}
