
public class FloodRelief {
	private int[][] myGrid;
	private int rows;
	private int cols;
	private boolean[][] visited;
	private int leftToReach;

	public int minimumPumps(String[] heights) {
		rows = heights.length;
		cols = heights[0].length();
		initializeGrid(heights);
		visited = new boolean[rows][heights[0].length()];
		leftToReach = rows * cols;
		int pumps = 0;

		for (int c = 0; c < 26; c++) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (!visited[i][j] && (myGrid[i][j] == ((char) 97 + c))) {
						visited[i][j] = true;
						moveUp(i, j);
						//printVisited();
						pumps++;
						//System.out.println("pumps " + pumps);
						leftToReach--;
						if (leftToReach == 0) {
							break;
						}
					}
				}
			}
		}

		return pumps;
	}

	private void printVisited() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				if (visited[i][j]) {
					System.out.print(1);
				} else {
					System.out.print(0);
				}
				if (j == cols - 1) {
					System.out.println("");
				}
			}
		}
		System.out.println(leftToReach);
	}

	private void moveUp(int i, int j) {
		int[] kAr = { -1, 1 };
		for (int k : kAr) {
			if (!(j + k < 0 || j + k >= cols)) {
				if (!visited[i][j+k] && myGrid[i][j + k] >= myGrid[i][j]) {
					visited[i][j + k] = true;
					leftToReach--;
					moveUp(i, j + k);
				}
			}
			if (!(i + k < 0 || i + k >= rows)) {
				if (!visited[i+k][j] && myGrid[i + k][j] >= myGrid[i][j]) {
					visited[i + k][j] = true;
					leftToReach--;
					moveUp(i + k, j);
				}
			}

		}

	}

	private void initializeGrid(String[] heights) {
		myGrid = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				myGrid[i][j] = ((int) heights[i].charAt(j));
			}
		}
		/*
		for (int fromV = 0; fromV < rows; fromV++) {
			for (int toV = 0; toV < cols; toV++) {
				System.out.print(myGrid[fromV][toV] + " ");
			}
			System.out.println();
		}
		*/
	}
	/*
	public static void main(String[] args) {
		String[] h = { "ccccc", "cbbbc", "cbabc", "cbbbc", "ccccc" };

		FloodRelief FR = new FloodRelief();
		System.out.println(FR.minimumPumps(h));
	}
	*/
}