import java.util.PriorityQueue;

public class HuffProcessor implements Processor {

	private int[] occurArray;
	private String[] extractArray;
	// private int Count;

	public HuffProcessor() {
		// Count = 0;
		occurArray = new int[ALPH_SIZE + 1];
		extractArray = new String[ALPH_SIZE + 1];
	}

	// COMPRESSION SECTION

	public void compress(BitInputStream in, BitOutputStream out) {

		// run through (in) and count occurrences
		int temp = in.readBits(BITS_PER_WORD);
		while (temp != -1) {
			occurArray[temp]++;
			temp = in.readBits(BITS_PER_WORD);
		}
		// add frequency of 0 for pseudo_EOF
		occurArray[PSEUDO_EOF] = 0;

		// reset (in)
		in.reset();

		// create priority queue and then compress to tree
		PriorityQueue<HuffNode> pq = createNodesPQ();
		HuffNode root = compress2Tree(pq);

		// write huffnumber
		out.writeBits(BITS_PER_INT, HUFF_NUMBER);

		// write header and extract codes for each character
		extractCodesWriteHeader(root, "", out);

		// write body
		temp = in.readBits(BITS_PER_WORD);
		String code;
		while (temp != -1) {
			code = extractArray[temp];
			out.writeBits(code.length(), Integer.parseInt(code, 2));
			temp = in.readBits(BITS_PER_WORD);
		}

		// add end of file code
		code = extractArray[PSEUDO_EOF];
		out.writeBits(code.length(), Integer.parseInt(code, 2));

		// System.out.println("Count " + Count);
	}

	private PriorityQueue<HuffNode> createNodesPQ() {
		PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();
		for (int i = 0; i < ALPH_SIZE; i++) {
			if (occurArray[i] != 0) {
				// System.out.println(i + " " + occurArray[i]);
				pq.add(new HuffNode(i, occurArray[i]));
			}
		}

		// add pseudo-EOF node to priority queue (despite zero frequency)
		pq.add(new HuffNode(PSEUDO_EOF, occurArray[PSEUDO_EOF]));

		return pq;
	}

	private HuffNode compress2Tree(PriorityQueue<HuffNode> pq) {
		HuffNode left;
		HuffNode right;
		HuffNode parent;
		while (pq.size() > 1) {
			// poll two smallest nodes
			left = pq.poll();
			right = pq.poll();

			// record sum of weights
			int newWeight = left.weight() + right.weight();

			// make and add parent
			parent = new HuffNode(-1, newWeight, left, right);
			pq.add(parent);
		}

		return pq.poll();
	}

	public void extractCodesWriteHeader(HuffNode current, String path, BitOutputStream out) {
		if (current == null) {
			return;
		}

		if (current.left() == null && current.right() == null) {
			// no children

			// store path value
			extractArray[current.value()] = path;

			// Count++;

			out.writeBits(1, 1);
			out.writeBits(BITS_PER_WORD + 1, current.value());
			return;
		}

		out.writeBits(1, 0);

		// recursion calls
		extractCodesWriteHeader(current.left(), path + 0, out);
		extractCodesWriteHeader(current.right(), path + 1, out);

	}

	// DECOMPRESSION SECTION

	public void decompress(BitInputStream in, BitOutputStream out) {
		int temp = in.readBits(BITS_PER_INT);
		// huff number check
		if (temp != HUFF_NUMBER) {
			throw new HuffException("Bad Huffnumber");
		}
		// store root
		final HuffNode root1 = readHeader(in);
		HuffNode current = root1;
		temp = in.readBits(1);
		while (temp != -1) {
			if (temp == 1) {
				current = current.right();
			} else {
				current = current.left();
			}
			if (current.left() == null && current.right() == null) {
				if (current.value() == PSEUDO_EOF) {
					return;
				} else {
					out.writeBits(BITS_PER_WORD, current.value());
					current = root1;
				}
			}
			temp = in.readBits(1);
		}

		throw new HuffException("Problem with the PSEUDO_EOF");
	}

	private HuffNode readHeader(BitInputStream in) {
		int temp = in.readBits(1);
		if (temp == 0) {
			HuffNode left = readHeader(in);
			HuffNode right = readHeader(in);
			return new HuffNode(-1, 0, left, right);
		} else {
			return new HuffNode(in.readBits(9), 0);
		}
	}
}