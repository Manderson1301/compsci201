import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DrawTree {
	
	private HashMap<Integer,ArrayList<Integer>> myMap;
	private String[] names1;
	private ArrayList<String> answer;
	public DrawTree(){
		myMap = new HashMap<Integer,ArrayList<Integer>>();
		answer = new ArrayList<String>();
	}
	
	public String[] draw(int[] parents, String[] names) {
        names1 = names;
        initializeMap(parents,names);
        int indOfRoot = find(parents,-1); 
        int level = 0;
        ArrayList<Integer> bro = new ArrayList<Integer>();
        addParent(indOfRoot,level,bro);
        
        //Change arraylist to array
        String[] answer2 = new String[answer.size()];
        answer2 = answer.toArray(answer2);
        
        return answer2;
     }
	private int find(int[] array, int value) {
	    for(int i=0; i<array.length; i++){
	    	if(array[i] == value) {
	    		return i;
	    	}
	    }
	    return -1;
	}
	
	private void addParent(int indCurrent, int level, ArrayList<Integer> bro) {
		StringBuilder sB = new StringBuilder();
		for (int j = 0 ; j < level ; j++){
			if (bro.indexOf(j) != -1){
				sB.append("| ");
			} else {
				sB.append("  ");
			}
		}
		sB.append("+-");
		sB.append(names1[indCurrent]);
		answer.add(sB.toString());
		if (myMap.containsKey(indCurrent)){
			ArrayList<Integer> tempArList = myMap.get(indCurrent);
			if (tempArList.size() > 1){
				bro.add(level + 1);
			}
			if (tempArList.size() == 1){
				addParent(tempArList.get(0),level + 1, bro);
			} else {
				for (int k = 0 ; k < tempArList.size() ; k++){
					if (k == tempArList.size() - 1 && bro.size() > 0){
						bro.remove(bro.size()-1);
					}
					addParent(tempArList.get(k),level + 1, bro);
				}
			}
		}
	}

	private void initializeMap(int[] parents, String[] names) {
		for (int i = 0 ; i < names.length ; i++){
        	if (parents[i]!=-1){
        		ArrayList<Integer> arList = new ArrayList<Integer>();
        		if (myMap.containsKey(parents[i])){
        			arList = myMap.get(parents[i]);
        		}
        		arList.add(i);
        		myMap.put(parents[i], arList);
        	}
		}
	}
/*
public static void main (String[] args) {
	// Remember to change method to static
	DrawTree dT = new DrawTree();
	//int[] p = {1, 2, 3, 4, 5, 6, -1};
	int[] p = {1, 2, 3, 4, 6, 6, -1};
	//int[] p = {-1, 0, 1, 1, 0, 0, 5, 5};
	
	String[] n = {"A", "B", "C", "D", "E", "F", "G"};
	//String[] n = {"Root", "SubB", "LEAF1", "LEAF2", "LEAF3", "SubA", "LEAF4", "LEAF5"};
	String[] m = dT.draw(p,n);
	for (int k = 0 ; k < m.length ; k++){
		System.out.println(m[k]);
	}
	
}
*/
}