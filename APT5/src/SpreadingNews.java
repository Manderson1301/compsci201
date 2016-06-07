import java.util.ArrayList;
import java.util.Collections;

public class SpreadingNews {
	public int minTime(int[] supervisors) {

		return minBoss(0, supervisors);
	}

	private int minBoss(int boss, int[] supervisors){
		ArrayList<Integer> time4each = new ArrayList<Integer>();


		for (int employ = 0 ; employ < supervisors.length ; employ++){
			if (boss == supervisors[employ]) {
				time4each.add(minBoss(employ, supervisors));
			}
		}

		if(time4each.size() == 0){
			return 0;
		}

		Collections.sort(time4each, Collections.reverseOrder());

		int max = 0;
		
		for (int i = 0 ; i < time4each.size() ; i++){
			if ((time4each.get(i) + i + 1) > max){ 
				max = time4each.get(i) + i + 1;
			}
		}


		return max;
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] org = {-1,0,0};
		SpreadingNews sn = new SpreadingNews();
		int answer = sn.minTime(org);
		System.out.println(answer);
	}
	*/

}
