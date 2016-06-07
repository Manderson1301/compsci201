import java.util.ArrayList;
import java.util.HashMap;

public class BoggleScoreBad {
	private String[] grid4me;
	private HashMap<Character, ArrayList<int[]>> myMap;

	public BoggleScoreBad() {
		myMap = new HashMap<Character, ArrayList<int[]>>();
	}

	public long bestScore(String[] grid, String[] words) {
		initializeMap(grid);
		/*
		 * for (Character b : myMap.keySet()){ System.out.println(b); for (int[]
		 * k : myMap.get(b)){ System.out.println(k[0] + "," + k[1]); } }
		 */
		long score = 0;
		grid4me = grid;

		for (int i = 0; i < words.length; i++) {

			ArrayList<int[]> g = findStartPos(words[i].charAt(0));
			for (int[] sP : g) {

				score += getScore(words[i], sP, 1);
			}
		}

		return (long) (score % 1E13);
	}

	private void initializeMap(String[] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length(); j++) {
				ArrayList<int[]> tempArList = new ArrayList<int[]>();
				if (myMap.containsKey(grid[i].charAt(j))) {
					tempArList.addAll(myMap.get(grid[i].charAt(j)));
				}
				int[] temp = { i, j };
				tempArList.add(temp);
				myMap.put(grid[i].charAt(j), tempArList);
			}
		}

	}

	private long getScore(String string, int[] startpos, int level) {
		// System.out.println("startpos " + startpos[0] + "," + startpos[1]);
		// System.out.println("level " + level);
		if (level == string.length()) {
			return (long) Math.pow(string.length(), 2);
		} else {
			if (myMap.containsKey(string.charAt(level))) {
				ArrayList<int[]> gg = myMap.get(string.charAt(level));
				long score = 0;
				for (int[] sP : gg) {
					// System.out.println("nextpos " + sP[0] + "," + sP[1]);
					double tempDis = distance(sP[0], sP[1], startpos[0], startpos[1]);
					if (tempDis < 2 && tempDis != 0) {
						score += getScore(string, sP, level + 1);
					}
				}
				return score;
			}
		}
		return 0;
	}

	private ArrayList<int[]> findStartPos(Character cStart) {
		ArrayList<int[]> end = new ArrayList<int[]>();

		for (int i = 0; i < grid4me.length; i++) {
			for (int j = 0; j < grid4me[i].length(); j++) {

				if (cStart.equals(grid4me[i].charAt(j))) {
					int[] tempAr = { i, j };
					end.add(tempAr);
				}
			}

		}

		return end;
	}

	public double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public static void main(String[] args) { // Remember to change method to
		BoggleScoreBad bs = new BoggleScoreBad();
		String[] n = { "AAAA", "AAAA", "AAAA", "AAAA"};
		String[] j = { "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" };

		long m = bs.bestScore(n, j);
		System.out.println(m);

	}

}
