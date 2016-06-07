import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class LinkStrandold implements IDnaStrand, Iterator<String> {

	private Node myHead;
	private Node myTail;
	private Node current;
	private int myAppends;
	private long nucleoCount;

	public class Node {
		private String value; // The string value contained by this node
		private Node next; // A pointer to the next node in the linked list

		public Node() {
			value = null;
			next = null;
		}

		public Node(String s) {
			value = s;
			next = null;
		}

		public Node(String s, Node n) {
			value = s;
			next = n;
		}

		public Node(IDnaStrand dna) {
			value = dna.toString();
			next = null;
		}
	}

	/**
	 * Create a strand representing an empty DNA strand, length of zero.
	 */
	public LinkStrandold() {
		initialize("");
	}

	/**
	 * Create a strand representing s. No error checking is done to see if s
	 * represents valid genomic/DNA data.
	 * 
	 * @param s
	 *            is the source of cgat data for this strand
	 */
	public LinkStrandold(String s) {
		initialize(s);
	}

	/**
	 * Initialize this strand so that it represents the value of source. No
	 * error checking is performed.
	 * 
	 * @param source
	 *            is the source of this enzyme
	 */
	@Override
	public void initialize(String source) {
		myAppends = 0;
		nucleoCount = source.length();
		myHead = new Node(source);
		Node temp = myHead;
		while (temp.next != null) {
			temp = temp.next;
		}
		myTail = temp;
		current = myHead;
	}

	/**
	 * Cut this strand at every occurrence of enzyme, essentially replacing
	 * every occurrence of enzyme with splicee.
	 * 
	 * @param enzyme
	 *            is the pattern/strand searched for and replaced
	 * @param splicee
	 *            is the pattern/strand replacing each occurrence of enzyme
	 * @return the new strand leaving the original strand unchanged.
	 */
	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		if (this.myHead.next != null) {
			throw new RuntimeException(
					"This method can only be applied to one Node");
		} else {
			int pos = 0;
			int start = 0;
			StringBuilder search = new StringBuilder(myHead.value);
			boolean first = true;
			LinkStrand ret = null;

			while ((pos = search.indexOf(enzyme, pos)) >= 0) {
				if (first) {
					ret = new LinkStrand(search.substring(start, pos));
					first = false;
				} else {
					ret.append(search.substring(start, pos));
				}
				start = pos + enzyme.length();
				ret.append(splicee);
				pos++;
			}

			if (start < search.length()) {
				if (ret == null) {
					ret = new LinkStrand("");
				} else {
					ret.append(search.substring(start));
				}
			}
			return ret;
		}
	}

	/**
	 * Returns the number of nucleotides/base-pairs in this strand.
	 */
	@Override
	public long size() {
		return nucleoCount;
	}

	/**
	 * Return some string identifying this class.
	 * 
	 * @return a string representing this strand and its characteristics
	 */
	@Override
	public String strandInfo() {
		return this.getClass().getName();
	}

	/**
	 * Returns a string that can be printed to reveal information about what
	 * this object has encountered as it is manipulated by append and
	 * cutAndSplice.
	 * 
	 * @return
	 */
	@Override
	public String getStats() {
		return String.format("# append calls = %d", myAppends);
	}

	/**
	 * Returns the sequence of DNA this object represents as a String
	 * 
	 * @return the sequence of DNA this represents
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		Node temp = myHead;
		while (temp != null) {
			output.append(temp.value);
			temp = temp.next;
		}
		return output.toString();
	}

	/**
	 * Append a strand of DNA to this strand. If the strand being appended is
	 * represented by a LinkStrand object then an efficient append is performed.
	 * 
	 * @param dna
	 *            is the strand being appended
	 */
	@Override
	public void append(IDnaStrand dna) {
		if (dna instanceof LinkStrand) {
			//myTail.next = ((LinkStrand) dna).myHead;

		} else {
			myTail.next = new Node(dna.toString());
			myTail = myTail.next;
		}
		myAppends++;
		nucleoCount += dna.size();
	}

	/**
	 * Simply append a strand of dna data to this strand.
	 * 
	 * @param dna
	 *            is the String appended to this strand
	 */
	@Override
	public void append(String dna) {
		Node n = new Node(dna);
		myTail.next = n;
		myTail = myTail.next;
		nucleoCount += dna.length();
		myAppends++;
	}

	/**
	 * Returns an IDnaStrand that is the reverse of this strand, e.g., for
	 * "CGAT" returns "TAGC"
	 * 
	 * @return reverse strand
	 */
	@Override
	public IDnaStrand reverse() {

		StringBuilder strBil1 = new StringBuilder();
		strBil1.append(myTail.value);
		String tempRev = strBil1.reverse().toString();
		Node n = new Node(tempRev);
		LinkStrand answer = new LinkStrand(n.value);

		if (myHead.next != null) {
			Stack<Node> stack = new Stack<Node>();
			HashMap<String, String> revStringMap = new HashMap<String, String>();
			revStringMap.put(myTail.value, tempRev);
			while (current.next != null) {
				// reverse string
				if (revStringMap.containsKey(current.value)) {
					tempRev = revStringMap.get(current.value);
				} else {
					StringBuilder strBil2 = new StringBuilder();
					strBil2.append(current.value);
					tempRev = strBil2.reverse().toString();
					revStringMap.put(current.value, tempRev);
				}
				Node m = new Node(tempRev);
				stack.push(m);
			}
			while (!stack.isEmpty()) {
				answer.append(stack.pop().value);
			}
		}
		return answer;
	}

	/**
	 * Returns the next element in the underlying LinkedList data structure
	 */
	@Override
	public String next() {
		String s = current.value;
		current = current.next;
		return s;
	}

	/**
	 * True if next evaluates correctly False if next returns with an error
	 */
	@Override
	public boolean hasNext() {
		if (current == null) {
			return false;
		}
		return true;
	}
}