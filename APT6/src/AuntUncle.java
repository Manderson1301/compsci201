import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AuntUncle {
	private HashMap<String,ArrayList<String>> parentsMap;
	private HashMap<String,ArrayList<String>> kidsMap;
	private HashMap<String,ArrayList<String>> sibsMap;
	
	public AuntUncle(){
		parentsMap = new HashMap<String,ArrayList<String>>();
		kidsMap = new HashMap<String,ArrayList<String>>();
		sibsMap = new HashMap<String,ArrayList<String>>();
	}
	
	public String[] list(String[] parents, String target) {
        initializeMaps(parents);
        if (!parentsMap.containsKey(target)){
        	return null;
        } else {
        	ArrayList<String> tParents = parentsMap.get(target);
        	ArrayList<String> tGrandParents = new ArrayList<String>();
        	for (int k = 0 ; k < tParents.size(); k++){
        		if (parentsMap.containsKey(tParents.get(k))) {
        			tGrandParents.addAll(parentsMap.get(tParents.get(k)));
        		}
        	}
        	HashSet<String> parentGenSet = new HashSet<String>(); 
        	for (int g = 0 ; g < tGrandParents.size(); g++){
        		parentGenSet.addAll(kidsMap.get(tGrandParents.get(g)));
        	}
        	ArrayList<String> list = new ArrayList<String>(parentGenSet);
        	
        	for (int j = 0 ; j < tParents.size() ; j++){
        		list.remove(tParents.get(j));
        	}
        	list.remove(target);
            String[] answer2 = new String[list.size()];
            answer2 = list.toArray(answer2);
            
            return answer2;
        }
        
        
    }

	private void initializeMaps(String[] parents) {
		for (int i = 0 ; i < parents.length ; i++){
			String[] splited = parents[i].split(" ");
			for (int k = 0 ; k < 2 ; k++){
				ArrayList<String> arList = new ArrayList<String>();
				if (kidsMap.containsKey(splited[k])){
					arList = kidsMap.get(splited[k]);
				}
				arList.add(splited[2]);
				kidsMap.put(splited[k], arList);
			}
			ArrayList<String> arList2 = new ArrayList<String>();
			if (parentsMap.containsKey(splited[2])){
				arList2 = parentsMap.get(splited[2]);
			}
			arList2.add(splited[0]);
			arList2.add(splited[1]);
			parentsMap.put(splited[2], arList2);
		}
	}
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		AuntUncle AU = new AuntUncle();
		String[] n = {"HECTOR DANA ROB", "ROB MARY JOE", "JOE MARY FRANK"};
		
		String[] m = AU.list(n,"FRANK");
		for (int k = 0 ; k < m.length ; k++){
			System.out.println(m[k]);
		}
		
	}
	*/
}
