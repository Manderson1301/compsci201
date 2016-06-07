
public class CountOccurrences {
	public int numberOccurrences(int number, int digit) {
        String strbase = Integer.toString(number);
        String strdigit = Integer.toString(digit);
        
        String strnew = strbase.replaceAll(strdigit,"");
        
        return strbase.length() - strnew.length();
 }
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		
		System.out.println(numberOccurrences(125543,5));
	}
	*/
}
