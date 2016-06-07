import java.util.HashMap;

public class BoggleScore {
	private HashMap<Character, long[][]> myMap;
	private long modulus;

	public BoggleScore() {
		myMap = new HashMap<Character, long[][]>();
		modulus = (long) 1E13;
	}

	public long bestScore(String[] grid, String[] words) {
		long score = 0;
		initializeMap(grid);
		for (int i = 0; i < words.length; i++) {
			long[][] wordGrid = new long[4][4];
			int index = 0;
			score += calcScore(words[i], wordGrid, index) % modulus;
		}

		return score % modulus;
	}

	private long calcScore(String word, long[][] wordGrid, int index) {
		//System.out.println("level " + index);
		if (index == word.length()) {
			long score = 0;
			for (int i = 0; i < wordGrid.length; i++) {
				for (int j = 0; j < wordGrid[i].length; j++) {
					score += wordGrid[i][j] % modulus;
				}
			}
			//System.out.println("pre-multiply " + score);
			return (long) (score * index * index % modulus);
		}
		
		if (!myMap.containsKey(word.charAt(index))) {
			return 0 % modulus;
		}

		

		long[][] nextCharGrid = myMap.get(word.charAt(index));
		long[][] newGrid = new long[4][4];
		
		
		
		if (index == 0) {
			/*
			for (int i = 0 ; i < nextCharGrid.length ; i++){
				for(int j = 0 ; j < nextCharGrid[i].length ; j++){
					System.out.println(i + "," + j + " " + nextCharGrid[i][j]);
				}
			}
			*/
			
			return calcScore(word, nextCharGrid, index + 1);
		} else {
			for (int i = 0; i < nextCharGrid.length; i++) {
				for (int j = 0; j < nextCharGrid[i].length; j++) {
					if (nextCharGrid[i][j] == 1) {
						for (int m = -1; m <= 1; m++) {
							for (int n = -1; n <= 1; n++) {
								if (i+m>-1 && i+m <4 && j+n>-1 && j+n<4) {
									/*
									if (index == 12){
										System.out.println("aaaaaa " + i + "," + j + " " + newGrid[i][j]);
										System.out.println("bbbbbb " + i + "," + j + " " + wordGrid[i + m][j + n]);
									}
									*/
									newGrid[i][j] += wordGrid[i + m][j + n] % modulus;
									
								}
							}
						}
						/*
						if (index == 12){
							System.out.println("totb4   "+ newGrid[i][j]);
							System.out.println("neg     "+ wordGrid[i][j]);
							System.out.println("newshit "+ (newGrid[i][j] - wordGrid[i][j]));
						}
						*/
						newGrid[i][j] -= wordGrid[i][j] % modulus; // removes
																// old[i][j]
					}
				}
			}
			/*
			for (int i = 0 ; i < wordGrid.length ; i++){
				for(int j = 0 ; j < wordGrid[i].length ; j++){
					System.out.println("word " + i + "," + j + " " + wordGrid[i][j]);
				}
			}
			for (int i = 0 ; i < newGrid.length ; i++){
				for(int j = 0 ; j < newGrid[i].length ; j++){
					System.out.println("new " + i + "," + j + " " + newGrid[i][j]);
				}
			}
			*/
			return calcScore(word, newGrid, index + 1);

		}
	}

	private void initializeMap(String[] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length(); j++) {
				if (!myMap.containsKey(grid[i].charAt(j))) {
					long[][] tempCharGrid = new long[4][4];
					for (int ii = 0; ii < grid.length; ii++) {
						for (int jj = 0; jj < grid[ii].length(); jj++) {
							if (grid[i].charAt(j) == grid[ii].charAt(jj)) {
								tempCharGrid[ii][jj] = 1;
							}
						}
					}
					myMap.put(grid[i].charAt(j), tempCharGrid);
				}
			}
		}

	}
	/*
	public static void main(String[] args) { // Remember to change method to
		BoggleScore bs = new BoggleScore();
		// String[] n = { "AAAA", "AAAA", "AAAA", "AAAA"};
		String[] n = {"AAAA", "AAAA", "AAAA", "AAAA"};
		String[] j = {"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"};
		
		// String[] j = { "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
		// "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
		// "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
		// "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" };

		long m = bs.bestScore(n, j);
		System.out.println(m);

	}
*/
}
