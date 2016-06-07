
public class AccessList {
	public String mayAccess(int[] rights, int minPermission) {
        if (rights.length == 0){
        	return "";
        } else {
        	String access = "";
        	for(int i = 0; i < rights.length; i++) {
        		if (rights[i] < minPermission) {
        			access += "D";
        		} else {
        			access += "A";
        		}
        	}
        	return access;
        }
    }
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		int[] f = {0,17,2,3,4};
		System.out.println(mayAccess( f , 3 ) );
	}
	*/
 }
