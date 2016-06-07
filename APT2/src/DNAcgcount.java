public class DNAcgcount {
	public double ratio(String dna) {
		if (dna.length() == 0){
			return 0.0;
		} else {
			String wogcdna = dna.replaceAll("g", "");
			wogcdna = wogcdna.replaceAll("c","");
			return ((dna.length() - wogcdna.length()) * 1.0) / (1.0 * dna.length());
		}
	}
}

// http://stackoverflow.com/questions/13252903/i-need-to-convert-an-int-variable-to-double
