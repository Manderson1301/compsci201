public class BinarySearchTree {

	TreeNode root = null;

	/**
	 * The Node class used to build this tree. It contains an integer value, as
	 * well as pointers to two children. The constructor is used as follows:
	 * TreeNode newNode = new TreeNode(val);
	 * 
	 */
	class TreeNode {
		public int myValue;
		public TreeNode myLeft; // holds smaller tree nodes
		public TreeNode myRight; // holds larger tree nodes

		public TreeNode(int val) {
			myValue = val;
		}
	}

	/**
	 * Adds the inputted value as a new node to the BST, maintaining the binary
	 * search property - every node to the left of a node should have a lower
	 * value, and every node to the right should have higher value
	 * 
	 * @param newValue
	 *            - the value to be added
	 */
	public void add(int newValue) {
		if (root == null)
			root = new TreeNode(newValue);
		else
			add(newValue, root);
	}

	public void add(int newValue, TreeNode current) {
		if (current.myValue > newValue) {
			if (current.myLeft == null) {
				current.myLeft = new TreeNode(newValue);
			} else {
				add(newValue, current.myLeft);
			}
		} else {
			if (current.myRight == null) {
				current.myRight = new TreeNode(newValue);
			} else {
				add(newValue, current.myRight);
			}
		}
	}

	/**
	 * Prints out a String showing the structure of this tree
	 */
	public String toString() {
		return toString(root, "");
	}

	public String toString(TreeNode current, String level) {
		String leftString = "null";
		String rightString = "null";

		if (current.myLeft != null)
			leftString = toString(current.myLeft, level + "   ");
		if (current.myRight != null)
			rightString = toString(current.myRight, level + "   ");

		return current.myValue + "\n" + level + "L: " + leftString + "\n" + level + "R: " + rightString;
	}

	/**
	 * Counts the total number of nodes in this tree
	 * 
	 * @return the total number of nodes in this tree
	 */
	public int countNodes() {
		return countNodes(root);
	}

	public int countNodes(TreeNode current) {
		if (current == null) {
			return 0;
		} else {
			int L = countNodes(current.myLeft);
			int R = countNodes(current.myRight);
			return 1 + L + R;
		}
	}

	/**
	 * Returns the height of the tree. An empty tree has height 0. A one-node
	 * tree has height 1. Otherwise, you can think of the height as the number
	 * of nodes on the longest path from the root to any leaf (node without
	 * children)
	 * 
	 * @return
	 */
	public int computeHeight() {
		// TODO add call to computeHeight(TreeNode)
		return computeHeight(root);
	}

	private int computeHeight(TreeNode current) {
		if (current == null) {
			return 0;
		}
		if (current.myLeft == null && current.myRight == null) {
			return 1;
		} else {
			int L = computeHeight(current.myLeft);
			int R = computeHeight(current.myRight);
			if (L > R) {
				return 1 + L;
			} else {
				return 1 + R;
			}

		}

	}

	/**
	 * Returns true if the input value can be found in the tree
	 * 
	 * @param value
	 *            - the value being searched for
	 * @return true if the value exists in the tree, false otherwise
	 */
	public boolean containsNode(int value) {
		return containsNode(root, value);
	}

	private boolean containsNode(TreeNode current, int value) {
		if (current == null) {
			return false;
		} else if (current.myValue == value) {
			return true;
		} else {
			return containsNode(current.myLeft, value) || containsNode(current.myRight, value);
		}
	}

	/**
	 * Returns the largest value in the tree. You may assume there is at least
	 * one value in the tree.
	 * 
	 * @return the largest value in the tree
	 */
	public int findMax() {
		return findMax(root);
	}

	// finds the largest value in the tree
	private int findMax(TreeNode current) {
		int max = current.myValue;
		int L = 0;
		int R = 0;
		if (current.myLeft != null) {
			L = findMax(current.myLeft);
		} else {
			L = -1000000000;
		}
		if (current.myRight != null) {
			R = findMax(current.myRight);
		} else {
			R = -1000000000;
		}
		if (L > max) {
			max = L;
		} else if (R > max) {
			max = R;
		}
		return max;
	}
}