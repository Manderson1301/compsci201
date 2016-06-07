/**
 * Example of printing for first discussion section
 */ 
public class Picture {
    public static void main(String[] args) {
	//TODO: Improve this method through using loops
        System.out.println("*");
        System.out.println("**");
        System.out.println("***");
        System.out.println("****");
        System.out.println("*****");
        System.out.println("******");
        System.out.println("*******");
        System.out.println("********");
        System.out.println("*********");
        System.out.println("**********");
        
        
        String staroutput = "*";
        for(int i=1;i < 6; i++){
        	System.out.println(staroutput);
        	staroutput += "*";
        }
    }
}
