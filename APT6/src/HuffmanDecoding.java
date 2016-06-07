
public class HuffmanDecoding {
	private Node root;
	private Node current;

	private class Node {
		private Character myVal;
		private Node left, right;

		public Node() {
			this.myVal = null;
		}

		public Node(Character val) {
			this.myVal = val;
		}
	}
	
	public HuffmanDecoding() {
		root = new Node();
	}

	public String decode(String archive, String[] dictionary) {
		initializeTree(dictionary);
		current = root;
		StringBuilder sB = new StringBuilder();
		for (int i = 0 ; i < archive.length() ; i++){
			if (archive.charAt(i) == '0'){
				if (current.left.myVal == null){
					current = current.left;
				} else {
					sB.append(current.left.myVal);
					current = root;
				}
			} else if (archive.charAt(i) == '1'){
				if (current.right.myVal == null){
					current = current.right;
				} else {
					sB.append(current.right.myVal);
					current = root;
				}
			}
		}
		return sB.toString();
	}

	private void initializeTree(String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			current = root;
			
			for (int j = 0; j < dictionary[i].length(); j++) {
				Character val = null;
				if (j + 1 == dictionary[i].length()) {
					val = (char) ((char) i + 65);
					
				}
				if (dictionary[i].charAt(j) == '0') {
					if (current.left == null) {
						current.left = new Node(val);
						current = current.left;
					} else if (current.left.myVal == null){
						current = current.left;
					} else {
						//for when there are prefix entries
					}
				} else if (dictionary[i].charAt(j) == '1') {
					if (current.right == null) {
						current.right = new Node(val);
						current = current.right;
					} else if (current.right.myVal == null){
						current = current.right;
					} else {
						//for when there are prefix entries
					}
				}
			}
		}

	}
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		HuffmanDecoding hd = new HuffmanDecoding();
		//int[] p = {1, 2, 3, 4, 5, 6, -1};
		String p = "101101"; 
		//int[] p = {-1, 0, 1, 1, 0, 0, 5, 5};
		
		String[] n = {"00", "10", "01", "11"};
		//String[] n = {"Root", "SubB", "LEAF1", "LEAF2", "LEAF3", "SubA", "LEAF4", "LEAF5"};
		String m = hd.decode(p,n);
		System.out.println(m);
		
	}
	*/
}
