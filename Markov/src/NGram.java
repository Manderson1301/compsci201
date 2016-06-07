import java.util.Arrays;
import java.util.List;

public class NGram implements Comparable<NGram> {

	public String[] contents;
	private String separator;

	public NGram(List<String> source, String sep) {
		separator = sep;
		contents = new String[source.size()];
		contents = Arrays.copyOf(source.toArray(new String[source.size()]), source.size());
	}

	public int compareTo(NGram other) {

		int lenOther = other.contents.length;
		int lenThis = this.contents.length;
		int minLen = 0;

		if (lenThis <= lenOther) {
			minLen = lenThis;
		} else {
			minLen = lenOther;
		}

		// loop over words
		for (int i = 0; i < minLen; i++) {

			// use String compareTo
			if (this.contents[i].compareTo(other.contents[i]) != 0) {
				return this.contents[i].compareTo(other.contents[i]);
			}

		}
		// if makes it to end, return difference in length
		return lenThis - lenOther;
	}

	public boolean equals(Object o) {

		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}

		// make sure that o is an NGram
		if (!(o instanceof NGram)) {
			return false;
		}

		NGram o2 = (NGram) o;

		// make sure both NGrams have the same length
		if ((this.contents.length) != o2.contents.length) {
			return false;
		}

		// loop over words
		for (int i = 0; i < this.contents.length; i++) {
			// make sure that they are the same
			if (!this.contents[i].equals(o2.contents[i])) {
				return false;
			}
		}

		return true;
	}

	public int hashCode() {

		int hashcode = 0;
		int n = this.contents.length;
		// loop over words
		for (int i = 0; i < n; i++) {
			hashcode += (this.contents[i].hashCode()) * Math.pow(10, n - 1 - i);
		}

		return hashcode;

	}

	public String toString() {

		// last element of the list/array
		int lenofThis = this.contents.length;
		String lastEle = this.contents[lenofThis - 1];
		return lastEle + separator;
	}
}