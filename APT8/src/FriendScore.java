import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class FriendScore {
	private HashMap<Integer, ArrayList<Integer>> myMap;
	private int mySize;
	
	public int highestScore(String[] friends) {
		mySize = friends.length;
		initializeMap(friends);
		//System.out.println("");
		int[] friendsScore = new int[mySize];
		
		for(int i = 0 ; i < mySize ; i++){
			ArrayList<Integer> friendsArList = myMap.get(i);
			HashSet<Integer> friendsSet = new HashSet<Integer>();
			for (int f : friendsArList){
				friendsSet.add(f);
				ArrayList<Integer> twoFriendsArList = myMap.get(f);
				for(int f2 : twoFriendsArList){
					if(f2 != i){
						friendsSet.add(f2);
					}
				}
			}
			/*
			System.out.println("set of " + i);
			for(int j : friendsSet){
				System.out.print(j + " ");
			}
			System.out.println("");
			*/
			friendsScore[i] = friendsSet.size();
		}
		
		int max = 0;
		for (int iDistance : friendsScore) {
			if( iDistance > max){
				max = iDistance;
			}
		}
		return max;
	}

	private void initializeMap(String[] friends) {
		myMap = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0 ; i < mySize ; i++){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int j = 0 ; j < mySize; j++){
				if(friends[i].charAt(j) == 'Y'){
					temp.add(j);
				}
			}
			myMap.put(i,temp);
			/*
			System.out.println("friends of " + i);
			for(int j = 0 ; j < temp.size(); j++){
				System.out.print(temp.get(j) + " ");
			}
			System.out.println("");
			*/
		}
	}
	/*
	public static void main(String[] args) {
		String[] connects = {
				"NYYN", 
				"YNNY", 
				"YNNY", 
				"NYYN"};
		
		FriendScore FS = new FriendScore();
		System.out.println("answer " + FS.highestScore(connects));
	}
	*/
	
}


