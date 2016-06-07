import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * General trie/priority queue algorithm for implementing Autocompletor
 * 
 * @author Austin Lu
 *
 */
public class TrieAutocomplete implements Autocompletor {

	/**
	 * Root of entire trie
	 */
	protected Node myRoot;

	/**
	 * Constructor method for TrieAutocomplete. Should initialize the trie
	 * rooted at myRoot, as well as add all nodes necessary to represent the
	 * words in terms.
	 * 
	 * @param terms
	 *            - The words we will autocomplete from
	 * @param weights
	 *            - Their weights, such that terms[i] has weight weights[i].
	 * @throws a
	 *             NullPointerException if either argument is null
	 */
	public TrieAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null)
			throw new NullPointerException("One or more arguments null");
		// Represent the root as a dummy/placeholder node
		myRoot = new Node('-', null, 0);

		for (int i = 0; i < terms.length; i++) {
			add(terms[i], weights[i]);
		}
	}

	/**
	 * Add the word with given weight to the trie. If word already exists in the
	 * trie, no new nodes should be created, but the weight of word should be
	 * updated.
	 * 
	 * In adding a word, this method should do the following: Create any
	 * necessary intermediate nodes if they do not exist. Update the
	 * subtreeMaxWeight of all nodes in the path from root to the node
	 * representing word. Set the value of myWord, myWeight, isWord, and
	 * mySubtreeMaxWeight of the node corresponding to the added word to the
	 * correct values
	 * 
	 * @throws a
	 *             NullPointerException if word is null
	 * @throws an
	 *             IllegalArgumentException if weight is negative.
	 * 
	 */
	private void add(String word, double weight) {
		if (word == null) {
			throw new NullPointerException();
		} else if (weight < 0) {
			throw new IllegalArgumentException();
		}
		Node curr = myRoot;

		// This flag will keep track whether we are in a new node territory and
		// therefore every subsequent node is newly created
		boolean flag = true;

		// cycle through characters
		for (int k = 0; k < word.length(); k++) {
			// update (mySubtreeMaxWeight) if necessary
			if (flag && curr.mySubtreeMaxWeight < weight) {
				curr.mySubtreeMaxWeight = weight;
			}
			// make new node
			if (!flag || !curr.children.containsKey(word.charAt(k))) {
				curr.children.put(word.charAt(k), new Node(word.charAt(k), curr, weight));
				flag = false;
			}
			// update to next node
			curr = curr.children.get(word.charAt(k));
		}
		// The word has been added
		// Now we handle corner cases
		// If the new word is replacing an old word we must work up the tree

		// this boolean is to mark whether it is necessary to update
		// (mySubtreeMaxWeight) up the tree
		boolean flag2 = false;
		double oldWeight = 0;

		// No updating needed, just add and done
		if (!curr.isWord) {
			curr.isWord = true;
			curr.setWord(word);
		} else {
			// up the tree updating needed
			flag2 = true;
			oldWeight = curr.getWeight();
		}

		// no matter what the inputted weight is the weight used
		curr.setWeight(weight);

		if (flag2) {
			// update (mySubtreeMaxWeight) for the last character/Node added
			if (curr.children.keySet().size() != 0) {
				double max = 0;
				for (Character c : curr.children.keySet()) {
					if (curr.children.get(c).mySubtreeMaxWeight > max) {
						max = curr.children.get(c).mySubtreeMaxWeight;
					}
				}
				if (max > weight) {
					curr.mySubtreeMaxWeight = max;
				}
			} else {
				curr.mySubtreeMaxWeight = weight;
			}

			// while loop to move up the tree
			while (curr != myRoot) {
				// move up
				curr = curr.parent;
				double max = 0;

				// if the (mySubtreeMaxWeight) is greater than both then you can
				// stop moving up the chain
				if (curr.mySubtreeMaxWeight > oldWeight && curr.mySubtreeMaxWeight > weight) {
					break;
				}

				// if there is more than one child then we must check to see if
				// there is a new (mySubtreeMaxWeight)
				if (curr.children.keySet().size() != 1) {
					for (Character c : curr.children.keySet()) {
						if (curr.children.get(c).mySubtreeMaxWeight > max) {
							max = curr.children.get(c).mySubtreeMaxWeight;
						}
					}
				} else {
					// only one child means just use the new weight as
					// (mySubtreeMaxWeight)
					max = weight;
				}
				curr.mySubtreeMaxWeight = max;
			}

		}

	}

	@Override
	/**
	 * Required by the Autocompletor interface. Returns an array containing the
	 * k words in the trie with the largest weight which match the given prefix,
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
	 *         no such words exist, return an empty array
	 * @throws a
	 *             NullPointerException if prefix is null
	 */
	public String[] topKMatches(String prefix, int k) {
		if (prefix == null) {
			throw new NullPointerException();
		} else if (k == 1) {
			// if k==1 then one should just use the faster .topMatches
			String[] end = new String[1];
			end[0] = this.topMatch(prefix);
			return end;
		} else if (k == 0) {
			String[] end = new String[0];
			return end;
		}

		// start at myRoot
		Node curr = myRoot;

		// move down chain to find prefix root
		for (int i = 0; i < prefix.length(); i++) {

			// if it doesn't exist, return empty array
			if (!curr.children.containsKey(prefix.charAt(i))) {
				String[] end = new String[0];
				return end;
			}

			// move down chain
			curr = curr.children.get(prefix.charAt(i));
		}

		// now to create the answer
		ArrayList<Node> answer = new ArrayList<Node>();
		Comparator<Node> comparator = new Node.ReverseSubtreeMaxWeightComparator();
		PriorityQueue<Node> nodePQ = new PriorityQueue<Node>(comparator);

		// add prefix root
		nodePQ.add(curr);

		// if it's a word add it to sorted list of words
		if (curr.isWord) {
			answer.add(curr);
		}

		// flag1 keeps track of whether we can begin checking to see if the kth
		// element
		// of the wordlist is bigger than any weight still left in the queue
		boolean flag1 = true;
		while (flag1 || answer.get(k - 1).getWeight() < nodePQ.peek().mySubtreeMaxWeight) {
			// testing the top entry in the queue
			// remove this entry
			Node currHead = nodePQ.peek();
			nodePQ.poll();

			// go through the children of this Node
			for (Character c : currHead.children.keySet()) {
				Node tempNode = currHead.children.get(c);
				// if this child is a word then we need to add it to the sorted
				// wordlist
				if (tempNode.isWord) {

					// add to sorted wordlist
					answer.add(tempNode);

					// if the sorted wordlist is now long enough we can begin
					// seeing
					// if the kth is bigger than the remaining quene items
					if ((answer.size() > k)) {
						flag1 = false;
					}
					// sort the list (only necessary once long enough)
					// if this child has no children it is not added to the
					// queue
					if (tempNode.children.keySet().size() == 0) {
						continue;
					}
				}
				// add child to queue to then later test it's children
				nodePQ.add(tempNode);
			}

			if (!flag1) {
				// Now that the sortedwordlist is long enough we will
				// sort it to see if we are done
				Collections.sort(answer, Collections.reverseOrder());
			}
			// if queue is empty then we have visited all Nodes and the while
			// loop ends regardless
			if (nodePQ.isEmpty()) {
				Collections.sort(answer, Collections.reverseOrder());
				break;
			}
		}

		// pick size depending on sorted wordlist
		int numResults = Math.min(k, answer.size());

		// allocate answer
		String[] finAnswer = new String[numResults];

		// cycle through and fill (finAnswer)
		for (int i = 0; i < numResults; i++) {
			finAnswer[i] = answer.get(i).getWord();
		}

		return finAnswer;
	}

	@Override
	/**
	 * Given a prefix, returns the largest-weight word in the trie starting with
	 * that prefix.
	 * 
	 * @param prefix
	 *            - the prefix the returned word should start with
	 * @return The word from _terms with the largest weight starting with
	 *         prefix, or an empty string if none exists
	 * @throws a
	 *             NullPointerException if the prefix is null
	 * 
	 */
	public String topMatch(String prefix) {
		if (prefix == null) {
			throw new NullPointerException();
		}

		// start at myRoot
		Node curr = myRoot;

		// move down to prefix root
		for (int i = 0; i < prefix.length(); i++) {
			// if doesnt exist return empty string
			if (!curr.children.containsKey(prefix.charAt(i))) {
				return "";
			}
			// move down
			curr = curr.children.get(prefix.charAt(i));
		}

		// pick maxWeight
		double maxWeight = curr.mySubtreeMaxWeight;

		// while loop to reach the maxWeight word
		while (!curr.isWord && curr.getWeight() < maxWeight) {

			// cycle through children
			for (Character c : curr.children.keySet()) {

				// if we're in the right chain, move down and break (for loop)
				if (curr.children.get(c).mySubtreeMaxWeight == maxWeight) {
					curr = curr.children.get(c);
					break;
				}
			}
		}
		return curr.getWord();
	}

}
