import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SandwichBar {
	public int whichOrder(String[] available, String[] orders){
		int flag = 0;
		List<String> availablelist = Arrays.asList(available);
		Set<String> availableTS = new TreeSet <String> (availablelist);
		for (int i = 0 ; i < orders.length ; i++) {
			String[] tempstrarray = orders[i].split(" ");
			List<String> tsalist = Arrays.asList(tempstrarray);
			Set<String> tsaTS = new TreeSet <String> (tsalist);
			for (String tempstr : tsaTS) {
				if (!availableTS.contains(tempstr)) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				return i;
			} else {
				flag = 0;
			}
		}
		return -1;
	}

	//http://java-tweets.blogspot.com/2012/07/convert-array-to-treeset-in-java.html
	//http://www.tutorialspoint.com/java/java_treeset_class.htm
	
/*
	public static void main (String[] args) {
		// Remember to change method to static
		String[] aa = {"a","b","c"};
		String[] bb = {"a b d" ,"a b f","b c d"};
		System.out.println(whichOrder(aa,bb));
	}
*/
}
