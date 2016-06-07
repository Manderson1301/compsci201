public class SoccerLeagues {
	public int[] points(String[] matches) {
		int[] answer = new int[matches.length];
        for (int i = 0 ; i < matches.length ; i++) {
			for (int ii = 0 ; ii < matches.length ; ii++) {
            	if (i!=ii) {
            		if (matches[i].charAt(ii) == 'W') {
            			answer[i] += 3;
            		} else if (matches[i].charAt(ii) == 'D') {
            			answer[i] += 1;
            		}
            	}
            }
        	for (int iii = 0 ; iii < matches.length ; iii++) {
        		if (i!=iii) {
            		if (matches[iii].charAt(i) == 'L') {
            			answer[i] += 3;
            		} else if (matches[iii].charAt(i) == 'D') {
            			answer[i] += 1;
            		}
            	}
            }
        }
        return answer;
    }
}
