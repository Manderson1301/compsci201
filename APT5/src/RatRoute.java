import java.util.Arrays;

public class RatRoute {

	public int numRoutes(String[] enc) {

		String[][] encTable = new String[enc.length][enc[0].length()];

		for (int i = 0; i < enc.length; i++) {
			for (int j = 0; j < enc[i].length(); j++) {

				encTable[i][j] = Character.toString(enc[i].charAt(j));

			}
		}
		return calcRoutes(encTable);
	}

	private int calcRoutes(String[][] encTable) {
		int x1 = 0;
		int x2 = 0;
		int y1 = 0;
		int y2 = 0;

		for (int i = 0; i < encTable.length; i++) {
			for (int j = 0; j < encTable[i].length; j++) {
				if ((encTable[i][j]).equals("C")) {
					x2 = j;
					y2 = i;
				} else if ((encTable[i][j]).equals("R")) {
					x1 = j;
					y1 = i;
				}
			}
		}
		
		double disRC = distance(x1, y1, x2, y2);
//		System.out.println("(" + x1 + "," + y1 + ") (" + x2 + "," + y2 + ") D of " + disRC);
		if (disRC == 1.0) {
			return 1;
		} else {

			double[] distances = new double[4];
			for (int k = 0; k < 4; k++) {
				distances[k] = disRC + 1;
			}

			// check up
			if (y1 != 0 && !encTable[y1 - 1][x1].equals("X")) {
				distances[0] = distance(x1, y1 - 1, x2, y2);
//				System.out.println("up " + distances[0]);
			}

			if (y1 != encTable.length - 1 && !encTable[y1 + 1][x1].equals("X")) {
				distances[1] = distance(x1, y1 + 1, x2, y2);
//				System.out.println("down " + distances[1]);
			}
			if (x1 != 0 && !encTable[y1][x1 - 1].equals("X")) {
				distances[2] = distance(x1 - 1, y1, x2, y2);
//				System.out.println("left " + distances[2]);
			}
			if (x1 != encTable[0].length - 1 && !encTable[y1][x1 + 1].equals("X")) {
				distances[3] = distance(x1 + 1, y1, x2, y2);
//				System.out.println("right " + distances[3]);
			}

			int finalcount = 0;
			int y1new = 0;
			int x1new = 0;
			for (int k = 0; k < 4; k++) {
				if (distances[k] < disRC) {
					String[][] encTableNew = createDeepCopy(encTable);
					if (k == 0) {
						y1new = y1 - 1;
						x1new = x1; 
						
					}
					if (k == 1){
						y1new = y1 +1;
						x1new = x1;
					}
					if (k == 2) {
						y1new = y1;
						x1new = x1-1; 
						
					}
					if (k == 3){
						y1new = y1;
						x1new = x1+1;
					}
					encTableNew[y1new][x1new] = "R";
					encTableNew[y1][x1] = ".";
					//System.out.println("old");
					//print(encTable);
//					System.out.println("new");
//					print(encTableNew);
					finalcount += calcRoutes(encTableNew);	
					
				}
			}
			return finalcount;
		}
	}


	private String[][] createDeepCopy(String[][] encTable) {
		String[][] target = new String[encTable.length][];
		for (int i=0; i <encTable.length; i++) {
			target[i] = Arrays.copyOf(encTable[i], encTable[i].length);
		}
		return target;
	}

	/*
	private static void print(String[][] encTab) {
		for (int i = 0 ; i< encTab.length ; i++){
			System.out.print("{");
			for (int j = 0 ; j < encTab[i].length ; j++) {
				if (j==encTab[i].length-1){
					System.out.println(encTab[i][j] + "}");
				} else {
					System.out.print(encTab[i][j]);
				}
				
			}
		}
		
	}
	*/
	public static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
/*
	public static void main(String[] args) {
		// Remember to change method to static

		String[] g = { "R.....", "...X.C" };
		//System.out.println(numRoutes(g));

		String[] h = { ".XR.....", "X.......", "....XX..", "....X..X", "..XC.XX." };
		
		String[] k = {".X.....X", "XX..R.X.", ".....X..", "...X....", "X..XX...", "C.......", "X......X"};
		System.out.println(numRoutes(k));
	}
	*/
}


//http://stackoverflow.com/questions/5563157/how-to-deep-copy-2-dimensional-array-different-row-sizes