import java.util.Arrays;
import java.util.Comparator;

public class ClientsList {

	public class lastNameSort implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			String sS1;
			String sS2;
			sS1 = arg0.substring(1 + arg0.indexOf(" ")) + " " + arg0.substring(0,arg0.indexOf(" "));
			sS2 = arg1.substring(1 + arg1.indexOf(" ")) + " " + arg1.substring(0,arg1.indexOf(" "));

			return sS1.compareTo(sS2);
		}

	}
	
	private String[] homogenize(String[] names) {
		for (int i = 0 ;  i < names.length ; i++) {
			if (names[i].indexOf(",")!=-1) {
				names[i] = names[i].substring(2 + names[i].indexOf(",")) + " " + names[i].substring(0,names[i].indexOf(","));
			}
		}
		return names;
	}

	public String[] dataCleanup(String[] names) {

		//get rid of all "with comma" entries
		names = homogenize(names);
		
		Arrays.sort(names, new lastNameSort());
		return names;
	}
}