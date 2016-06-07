import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class SortByFreqs {
	private HashMap<String, Integer> bank;
	public SortByFreqs(){
		bank = new HashMap<String, Integer>();
		
	}
	public class dataSort implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			if (bank.get(o1) != bank.get(o2)) {
				return bank.get(o2) - bank.get(o1);
			} else {
				return o1.compareTo(o2);
			}
		}
	}
	public String[] sort(String[] data) {
		initializeBank(data);
        Arrays.sort(data, new dataSort());
        ArrayList<String> dataNew = new ArrayList<String>();
        int index = 0;
        for (int i = 0 ; i < data.length ; i++) {
        	dataNew.add(index, data[i]);
        	index++;
        	i += bank.get(data[i]) -1 ; 
        }
        String[] dataFinal = new String[dataNew.size()];
        dataFinal = dataNew.toArray(dataFinal);
        return dataFinal;
	}
	private void initializeBank(String[] data) {
		bank.clear();
		for (int i = 0 ; i < data.length ; i++) {
			if (bank.containsKey(data[i])) {
				bank.put(data[i], bank.get(data[i]) + 1);
			} else {
				bank.put(data[i],  1);
			}	
		}
		
	}
}
