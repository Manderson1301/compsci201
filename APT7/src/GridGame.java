import java.util.ArrayList;

public class GridGame {
	private char[][] myGrid;
	private static final int MYSIZE = 4;
	
	GridGame(){
		myGrid = new char[MYSIZE][MYSIZE];
	}
	public int winningMoves(String[] grid){
		initializeGrid(grid);
		//printGrid();
        return countWins();
   }
	
	private void initializeGrid(String[] grid) {
		int row = -1;
		for (String s : grid) {
			row++;
			for (int col = 0 ; col < MYSIZE ; col++){
				myGrid[row][col] = s.charAt(col);
			}
		}
		
	}
	public int countWins(){
		int wins = 0;
		ArrayList<int[]> list = nextMoves();
		if(list.size() == 0){
			return 0;
		}
		for (int[] a : list){
			myGrid[a[0]][a[1]] = 'X';
			if (countWins() == 0){
				wins++;
			}
			myGrid[a[0]][a[1]] = '.';
		}
		return wins;
	}
	private ArrayList<int[]> nextMoves() {
		ArrayList<int[]> nM = new ArrayList<int[]>();
		for (int i = 0 ; i < MYSIZE ; i++){
			for (int j = 0 ; j < MYSIZE ; j++){
				if(myGrid[i][j] != 'X'){
					if(emptyNeighbors(i,j)){
						
						int[] temp = {i,j};
						nM.add(temp);
					}
				}
			}
		}
		return nM;
	}
	private boolean emptyNeighbors(int i, int j) {
		int[] kAr = {-1,1};
		for (int k : kAr){
			if(!(j + k < 0 || j + k >= MYSIZE)){
				if(myGrid[i][j+k] == 'X'){
					return false;
				}
			}
			if(!(i + k < 0 || i + k >= MYSIZE)){
				if(myGrid[i+k][j] == 'X'){
					return false;
				}
			}
		}
		return true;
	}
	/*
	public static void main(String[] args) { // Remember to change method to
		GridGame GG = new GridGame();
		
		
		String[] n = {"....", "....", ".X..", "...." };
		int m = GG.winningMoves(n);
		System.out.println(m);

	}
	*/
	
	
	private void printGrid(){
		for (int i = 0; i < MYSIZE ; i++){
			for (int j = 0 ; j < MYSIZE ; j++){
				if (j==MYSIZE-1){
					System.out.println(myGrid[i][j]);
				} else {
					System.out.print(myGrid[i][j]);
				}
			}
		}
		
	}
}
