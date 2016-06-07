public class LinkedStringList {
	Node myHead; //The first node in the linked list
	/**
	 * The inner Node class used in implementing the Linked List
	 */
	public class Node{
		String value; //The string value contained by this node
		Node next; //A pointer to the next node in the linked list
		
		public Node(){
			value = null;
			next = null;
		}
		
		public Node(String s){
			value = s;
			next = null;
		}
		
		public Node(String s, Node n){
			value = s;
			next = n;
		}
	}
	
	/**
	 * Creates a new LinkedStringList containing the input
	 * @param data - the String being stored in this LinkedStringList
	 */
	public LinkedStringList(String data){
		myHead = new Node(data);
	}
	
	/**
	 * Adds the given String to the beginning of the LinkedStringList
	 * @param toAdd - the String we want to add to the beginning
	 */
	public void addToBeginning(String toAdd){
		myHead = new Node(toAdd, myHead);
	}
	
	/**
	 * Adds the given String to the end of the LinkedStringList
	 * @param toAdd - the String we want to add to the end
	 */
	public void addToEnd(String toAdd){
		Node current = myHead;
		while (current.next != null){
			current = current.next;
		}
		current.next = new Node(toAdd,null);
		
	}
	
	/**
	 * Returns the size of this LinkedList
	 * @return The number of nodes in this LinkedList
	 */
	public int size(){
		int count = 0;
		Node current = myHead;
		while (current.next != null){
			current = current.next;
			count++;
		}
		return count;
	}
	
	/**
	 * Returns the String represented by this LinkedStringList. 
	 * For example, if the nodes contain "aa", "bb", and "cc"
	 * as their values, we should return "aabbcc"
	 * @return the String formed by concatenating all the nodes in this 
	 * LinkedStringList
	 */
	public String toString(){
		StringBuilder output = new StringBuilder();
		Node current = myHead;
		while (current != null){
			output.append(current.value);
			current = current.next;
		}
		return output.toString();
	}
	
	/**
	 * Returns true if one of the nodes' value is subStr
	 * @param subStr - the value we're looking for
	 * @return true if one of the Nodes has value equal to subStr
	 */
	public boolean containsNode(String subStr){
		Node current = myHead;
		while (current.next != null){
			current = current.next;
			if (current.toString().equals(subStr)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the value of the ith node, where myHead is the 0th node,
	 * myHead.next is the 1st node, etc. If i is not a valid index (<0
	 * or >= the number of nodes), return null.
	 * @param i - the index of the node whose value we should return
	 * @return the value inside the specified node
	 */
	public String getNode(int i){
		Node current = myHead;
		int count = 0;
		if (i == 0){
			return current.toString();
		}
		while (current.next != null){
			current = current.next;
			count++;
			if (count == i) {
				return current.toString();
			}
		}
		return null;
	}
	
	/**
	 * Deletes the ith node from this LinkedStringList. For example,
	 * calling deleteNode(i) on the list "aa"->"bb"->"cc" should turn it
	 * into the list "aa"->"cc". If there's only one node, or if i is <0 or
	 * >= the number of nodes, don't do anything.
	 * @param i - the index of the node we wish to delete
	 */
	public void deleteNode(int i){
		Node current = myHead;
		if (i == 0){
			current.next = myHead;
		} else {
			int count = 0;
			while (current.next != null){
				if (count == i + 1){
					current.next=current.next.next;
					break;	
				}
				count++;
				current = current.next;
			}
		}
	}
}
