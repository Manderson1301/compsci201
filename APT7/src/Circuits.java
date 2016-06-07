import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Circuits {

	private int mySize; // Number of vertices
	private int myGraph[][]; // Adjacency matrix
	private int myIn[]; // In-degree of each vertex
	private int myOut[]; // Out-degree of each vertex
	private int distances[];
	
	public Circuits(){
		distances = new int[mySize];
	}

	public int howLong(String[] connects, String[] costs) {
		buildGraph(connects, costs); // build adjacency matrix
		//visualize(); // visualize adjacency matrix
		return search(); // BFS/DFS/other search
	}

	// Build the adjacency matrix representation
	public void buildGraph(String[] connects, String[] costs) {
		mySize = connects.length;
		myGraph = new int[mySize][mySize];
		myIn = new int[mySize];
		myOut = new int[mySize];

		// Go through the input
		// if there are any connection from "fromV"
		// then add myGraph[fromV][toV] with the correct weight and increment
		// myIn[toV]
		for (int fromV = 0; fromV < mySize; fromV++) {
			if (!connects[fromV].equals("")) {
				String[] edges = connects[fromV].split(" ");
				String[] weights = costs[fromV].split(" ");
				for (int i = 0; i < edges.length; i++) {
					int toV = Integer.parseInt(edges[i]);
					myGraph[fromV][toV] = Integer.parseInt(weights[i]);
					myIn[toV]++;
					myOut[fromV]++;
				}
			}
		}
		return;
	}

	// Help visualize the adjacency matrix myGraph and the In Degrees myIn
	public void visualize() {
		// print the adjacency matrix
		for (int fromV = 0; fromV < mySize; fromV++) {
			for (int toV = 0; toV < mySize; toV++) {
				System.out.print(myGraph[fromV][toV] + " ");
			}
			System.out.println();
		}

		// print the inDegrees
		for (int toV = 0; toV < mySize; toV++) {
			System.out.print(myIn[toV] + " ");
		}
		System.out.println("<< InDegrees");
		System.out.println();
		for (int fromV = 0; fromV < mySize; fromV++) {
			System.out.print(myOut[fromV] + " ");
		}
		System.out.println("<< OutDegrees");
		System.out.println();
		return;
	}

	// BFS or DFS
	private int search() {
		int[] distance = new int[mySize]; // Distance to vertex i

		// Data structure to track search
		//Queue<Integer> q = new LinkedList<Integer>();
		// OR
		Stack<Integer> nodes = new Stack<Integer>();
		Stack<Integer> currScore = new Stack<Integer>();

		// Add all the "roots" (InDegree is 0)
		for (int i = 0 ; i < mySize ; i++){
			if(myIn[i]==0){
				nodes.add(i);
				currScore.add(0);
			}
		}

		
		// Remove each item until nothing left
		// While queue/stack is not empty
		while (nodes.size() >= 1){
			int tempNode = nodes.pop();
			int tempCurrScore = currScore.pop();
			boolean flag = false;
			for (int i = 0 ; i < mySize; i++){
				if(myGraph[tempNode][i] != 0){
					nodes.add(i);
					currScore.add(tempCurrScore + myGraph[tempNode][i]);
					flag = true;
				}
			}
			if(flag){
				continue;
			}
			if (tempCurrScore > distance[tempNode]){
				distance[tempNode] = tempCurrScore;
			}
		}
		
		// get the next element

		// Check all possible connections
		// start with default weight in myGraph[fromV][toV]
		// Update weights if longer path is found using BFS/DFS

		// Get the max distance to any vertex
		int max = 0;
		for (int iDistance : distance) {
			if( iDistance > max){
				max = iDistance;
			}
		}
		return max;
	}
	/*
	public static void main(String[] args) {
		String[] connects = {"1 2 3 4 5","2 3 4 5","3 4 5","4 5","5",""};
		String[] costs    = {"2 2 2 2 2","2 2 2 2","2 2 2","2 2","2",""};
		
		Circuits C = new Circuits();
		System.out.println(C.howLong(connects, costs));
	}
	*/
}
