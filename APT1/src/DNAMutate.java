
public class DNAMutate {
	public String mutate(String dna) {
		String revdna = "";
        while(dna.length() != 0){
        	revdna += dna.charAt(dna.length()-1);
        	dna = dna.substring(0,dna.length()-1);
        }
        return revdna;
    }
	
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		System.out.println("a    " + mutate("a"));
	}
	*/
}
