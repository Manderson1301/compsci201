import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class ErdosNumber {

	private HashMap<String, HashSet<String>> myGraph;
	private HashMap<String, Integer> dist;

	public ErdosNumber() {
		myGraph = new HashMap<String, HashSet<String>>();
		dist = new HashMap<String, Integer>();
	}
	
	public class SortByNum implements Comparator<String> {
		@Override
		public int compare(String auth1, String auth2) {
			int t1 = dist.get(auth1);
			int t2 = dist.get(auth2);
			return t1-t2;
		}
	}

	public String[] calculateNumbers(String[] pubs) {
		initializeGraph(pubs);
		/*
		for(String s : myGraph.keySet()){
			for(String h : myGraph.get(s)){
				System.out.println(s + " " + h);
			}
		}
		*/
		
		dist.put("ERDOS", 0);
		Comparator<String> c = new ErdosNumber.SortByNum();
		PriorityQueue<String> Q = new PriorityQueue<String>(c);
		// fill shit
		HashSet<String> S = new HashSet<String>();
		Q.add("ERDOS");
		while (Q.size() > 0) {
			String curr = Q.poll();
			if (S.contains(curr)) {
				continue;
			} else {
				//System.out.println(curr + " " + dist.get(curr));
				S.add(curr);
				for (String neigh : myGraph.get(curr)) {
					//System.out.println("   " + neigh);
					if (!dist.containsKey(neigh)) {
						dist.put(neigh, dist.get(curr) + 1);
					}
					Q.add(neigh);
				}
			}

		}
		
		for(String auth : myGraph.keySet()){
			if (!S.contains(auth)){
				dist.put(auth, -1);
			}
		}
		
		
		return sortDist(dist);
	}

	private String[] sortDist(HashMap<String, Integer> dist) {
		String[] answer = new String[dist.keySet().size()];
		int count = -1;
		for (String auth : dist.keySet()) {
			count++;
			if (dist.get(auth) == -1){
				answer[count] = auth;
			} else {
				answer[count] = auth + " " + dist.get(auth);
			}
		}
		Arrays.sort(answer);
		return answer;
	}

	public void initializeGraph(String[] pubs) {
		for (String publication : pubs) {
			// split pubs into authors
			String[] authors = publication.split("\\s+");
			for (String a : authors) {
				if (!myGraph.containsKey(a)) {
					myGraph.put(a, new HashSet<String>());
				}
				HashSet<String> temp = myGraph.get(a);
				for (String b : authors) {
					if (!a.equals(b)) {
						temp.add(b);
					}
				}
				myGraph.put(a, temp);
			}

		}
	}
	/*
	public static void main(String[] args){
		//String[] n = {"ERDOS A", "A B", "B C", "C X Y", "ERDOS X"};
		String[] n = {
				"ERDOS ERYX", 
				"ERDOS FISH", 
				"ERYX BALL", 
				"BALL WRITER", 
				"WRITER THEFAXMAN", 
				"FISH WRITER"
				};
		ErdosNumber EN = new ErdosNumber();
		
		String[] j = EN.calculateNumbers(n);
		
		for(int i = 0; i < j.length ; i++){
			System.out.println(j[i]);
		}
		
		
	}
	*/
	
}
