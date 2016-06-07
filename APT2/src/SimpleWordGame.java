public class SimpleWordGame {
	public int points(String[] player, String[] dictionary) {
		int score = 0;
		for (int i = 0 ; i < dictionary.length ; i++) {
			for (int ii = 0 ; ii < player.length ; ii++) {
				if (dictionary[i].equals(player[ii])){
					score += Math.pow((player[ii].length()), 2);
					break;
				}				
			}
		}
		return score;
	}
}
