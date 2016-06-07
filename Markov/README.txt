Name: Miguel Ricardo Anderson
NetID: mra21

ANALYSIS

Part A: Brute Force VS. Map

1. Big-O notation hypothesis

	i. the length of the training text
		Brute - O(n^2) the ".indexOf()" function is useful but takes a lot time and is run multiple times in this method that does no storing
		Map - O(n) - it will only run through the text once to create myMap and then all the operations are operations on myMap to get the next states which are O(1). 
		
	ii. the k-value or length of the word
		Brute - O(1) - no code segments explicitly depend on the k value
		Map -  O(1) - there will be more myMap keys but otherwise none of the operations are affected greatly by greater k values 
	
	iii. the length of the random text
		Brute - O(n) - the entire process lies within this for loop but none of the actions depend on this length so the runtime would increase linearly with the length
		Map - O(n) - this will determine how many times the for loop runs in .generateText() the operations within this loop are O(n)
		
2. Comparison with Benchmark

	My hypothesis was correct except for the length of the training text with O(n^2) for the Brute and O(n) for the Map. It was actually O(n) for the Brute and O(1) for the Map.
		
Part B: HashMap VS. TreeMap

3. Big-O notation hypothesis for number of keys

	i. HashMap with default hashCode function
		O(n) - with all collisions the operations would have to go through most of the values to access a particular one
	
	ii. HashMap with the hashCode function you wrote
		O(1) - with a good hashCode all "lookups" would take constant time as there would be no collision handling necessary 
	
	ii. TreeMap 
		O(log n) - TreeMap operations have O(log n) as a sacrifice for keeping the map sorted
		
4. Comparison with Benchmark
	I was correct about my hashCode (using the same logic as in question one). For the dafault hashCode function it was O(n) as I hypothesized. Lastly the TreeMap resulted in O(log n) also as I had hypothesized.
