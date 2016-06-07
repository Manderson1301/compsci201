
public class ClientsList {
	public String[] dataCleanup(String[] names) {
        
        //get rid of all "with comma" entries
        for (int i = 0 ;  i < names.length ; i++) {
        	if (names[i].indexOf(",")!=-1) {
        		names[i] = names[i].substring(2 + names[i].indexOf(",")) + " " + names[i].substring(0,names[i].indexOf(","));
        	}
        }
        //BUBBLE SORT
        String sS1;
        String sS2;
        
        boolean swapbol = true;
        while(swapbol) {
            swapbol = false;
            for(int c = 0 ; c < names.length -1; c++) {
            	sS1 = names[c].substring(1 + names[c].indexOf(" ")) + " " + names[c].substring(0,names[c].indexOf(" "));
            	sS2 = names[c+1].substring(1 + names[c+1].indexOf(" ")) + " " + names[c+1].substring(0,names[c+1].indexOf(" "));
            	
            	if (sS1.compareTo(sS2)>0) {
                    String temp = names[c];
                    names[c] = names[c+1];
                    names[c+1] = temp;
                    swapbol = true;
                }
            }
        }
        return names;
   }
}

//http://www.tutorialspoint.com/java/java_string_indexof.htm
//http://www.tutorialspoint.com/java/java_string_substring.htm
//http://www.tutorialspoint.com/java/java_string_compareto.htm
//http://www.go4expert.com/articles/bubble-sort-algorithm-absolute-beginners-t27883/