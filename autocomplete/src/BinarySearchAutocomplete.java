import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * Using a sorted array of Term objects, this implementation uses binary search
 * to find the top term(s).
 * 
 * @author Austin Lu, adapted from Kevin Wayne
 *
 */
public class BinarySearchAutocomplete implements Autocompletor {

	Term[] myTerms;

	/**
	 * Given arrays of words and weights, initialize myTerms to a corresponding
	 * array of Terms sorted lexicographically.
	 * 
	 * This constructor is written for you, but you may make modifications to
	 * it.
	 * 
	 * @param terms
	 *            - A list of words to form terms from
	 * @param weights
	 *            - A corresponding list of weights, such that terms[i] has
	 *            weight[i].
	 * @return a BinarySearchAutocomplete whose myTerms object has myTerms[i] =
	 *         a Term with word terms[i] and weight weights[i].
	 * @throws a
	 *             NullPointerException if either argument passed in is null
	 */
	public BinarySearchAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null)
			throw new NullPointerException("One or more arguments null");
		myTerms = new Term[terms.length];
		for (int i = 0; i < terms.length; i++) {
			myTerms[i] = new Term(terms[i], weights[i]);
		}
		Arrays.sort(myTerms);

	}

	/**
	 * Uses binary search to find the index of the first Term in the passed in
	 * array which is considered equivalent by a comparator to the given key.
	 * This method should not call comparator.compare() more than 1+log n times,
	 * where n is the size of a.
	 * 
	 * @param a
	 *            - The array of Terms being searched
	 * @param key
	 *            - The key being searched for.
	 * @param comparator
	 *            - A comparator, used to determine equivalency between the
	 *            values in a and the key.
	 * @return The first index i for which comparator considers a[i] and key as
	 *         being equal. If no such index exists, return -1 instead.
	 */
	public static int firstIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
		if (a.length == 0) {
			return -1;
		} else if (key.getWord().equals("")) {
			return 0;
		}
		int len = a.length;
		// c will keep track of the .compare result
		int c = 0;
		int lo = 0;
		int hi = len - 1;
		int mid = 0;
		// standard binary search
		while (lo <= hi) {

			if (mid - (lo + (hi - lo) / 2) == 0) {
				// to avoid infinite loops
				break;
			}
			mid = lo + (hi - lo) / 2;

			c = comparator.compare(a[mid], key);
			if (c < 0) {
				lo = mid + 1;
			} else if (c > 0) {
				hi = mid - 1;
			} else {
				// in this case (c==0) and therefore it should stay
				// included in the testing
				hi = mid;
			}
		}
		int looplo = 0;
		int loophi = 0;
		if (c < 0) {
			looplo = mid;
			loophi = hi + 1;
		} else {
			looplo = lo;
			loophi = mid;
			if (c == 0) {
				loophi++;
			}
		}
		int h = 0;
		for (int j = looplo; j < loophi; j++) {
			h = comparator.compare(a[j], key);
			if (h == 0) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * The same as firstIndexOf, but instead finding the index of the last Term.
	 * 
	 * @param a
	 *            - The array of Terms being searched
	 * @param key
	 *            - The key being searched for.
	 * @param comparator
	 *            - A comparator, used to determine equivalency between the
	 *            values in a and the key.
	 * @return The last index i for which comparator considers a[i] and key as
	 *         being equal. If no such index exists, return -1 instead.
	 */
	public static int lastIndexOf(Term[] a, Term key, Comparator<Term> comparator) {
		if (a.length == 0) {
			return -1;
		} else if (key.getWord().equals("")) {
			return a.length - 1;
		}
		int len = a.length;
		int c = 0;
		int lo = 0;
		int hi = len - 1;
		int mid = 0;
		while (lo <= hi) {

			if (mid - (lo + (hi - lo) / 2) == 0) {
				break;
			}
			mid = lo + (hi - lo) / 2;
			c = comparator.compare(a[mid], key);
			if (c < 0) {
				lo = mid + 1;
			} else if (c > 0) {
				hi = mid - 1;
			} else {
				lo = mid;
			}
		}

		int looplo = 0;
		int loophi = 0;
		if (c < 0) {
			loophi = mid;
			looplo = lo - 1;
		} else {
			loophi = hi;
			looplo = mid - 1;
			if (c != 0) {
				looplo = mid;
			}
		}
		int h = 0;
		for (int j = loophi; j > looplo; j--) {
			h = comparator.compare(a[j], key);
			if (h == 0) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * Required by the Autocompletor interface. Returns an array containing the
	 * k words in myTerms with the largest weight which match the given prefix,
	 * in descending weight order. If less than k words exist matching the given
	 * prefix (including if no words exist), then the array instead contains all
	 * those words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then
	 * topKMatches("b", 2) should return {"bell", "bat"}, but topKMatches("a",
	 * 2) should return {"air"}
	 * 
	 * @param prefix
	 *            - A prefix which all returned words must start with
	 * @param k
	 *            - The (maximum) number of words to be returned
	 * @return An array of the k words with the largest weights among all words
	 *         starting with prefix, in descending weight order. If less than k
	 *         such words exist, return an array containing all those words If
	 *         no such words exist, reutrn an empty array
	 * @throws a
	 *             NullPointerException if prefix is null
	 */
	public String[] topKMatches(String prefix, int k) {
		if (prefix == null) {
			throw new NullPointerException();
		} else if (k == 1) {
			String[] end = new String[1];
			end[0] = this.topMatch(prefix);
			return end;
		} else if (k == 0) {
			String[] end = new String[0];
			return end;
		}

		Comparator<Term> comp = new Term.PrefixOrder(prefix.length());
		Term pre = new Term(prefix, 0);

		// find first and last index
		int firInd = BinarySearchAutocomplete.firstIndexOf(this.myTerms, pre, comp);
		int lasInd = BinarySearchAutocomplete.lastIndexOf(this.myTerms, pre, comp);
		if (firInd == -1 && lasInd == -1) {
			String[] end = new String[0];
			return end;
		}
		//copy range of zeros
		Term[] zeros = Arrays.copyOfRange(this.myTerms, firInd, lasInd + 1);
		//sort
		Arrays.sort(zeros, new Term.ReverseWeightOrder());
		int numResults = Math.min(k, zeros.length);

		String[] end = new String[numResults];

		for (int kk = 0; kk < numResults; kk++) {
			end[kk] = zeros[kk].getWord();
		}

		return end;
	}

	@Override
	/**
	 * Given a prefix, returns the largest-weight word in myTerms starting with
	 * that prefix. e.g. for {air:3, bat:2, bell:4, boy:1}, topMatch("b") would
	 * return "bell". If no such word exists, return an empty String.
	 * 
	 * @param prefix
	 *            - the prefix the returned word should start with
	 * @return The word from myTerms with the largest weight starting with
	 *         prefix, or an empty string if none exists
	 * @throws a
	 *             NullPointerException if the prefix is null
	 * 
	 */
	public String topMatch(String prefix) {
		if (prefix == null) {
			throw new NullPointerException();
		}

		Comparator<Term> comp = new Term.PrefixOrder(prefix.length());
		Term pre = new Term(prefix, 0);

		// find indexes of first and last
		int firInd = BinarySearchAutocomplete.firstIndexOf(this.myTerms, pre, comp);
		int lasInd = BinarySearchAutocomplete.lastIndexOf(this.myTerms, pre, comp);

		// if not found return empty string
		if (firInd == -1 && lasInd == -1) {
			return "";
		} else if (lasInd - firInd == 0) {
			// if only one entry then return that one
			return this.myTerms[firInd].getWord();
		}
		// make new array of only matches

		Term[] zeros = Arrays.copyOfRange(this.myTerms, firInd, lasInd + 1);
		// sort
		Arrays.sort(zeros, new Term.ReverseWeightOrder());

		// return first
		return zeros[0].getWord();
	}

}
