import static org.junit.Assert.*;

import org.junit.Test;


public class TestBinarySearchTree {
	public BinarySearchTree testInstance(){
		BinarySearchTree tree = new BinarySearchTree();
        tree.add(5);
        tree.add(7); 
        tree.add(2);
        tree.add(9);
        tree.add(6);
        return tree;
	}
	
	@Test
	public void testAdd(){
		BinarySearchTree test = testInstance();
		assertEquals(5, test.root.myValue);
		assertEquals(7, test.root.myRight.myValue);
		assertEquals(2, test.root.myLeft.myValue);
		assertEquals(9, test.root.myRight.myRight.myValue);
		assertEquals(6, test.root.myRight.myLeft.myValue);
	}

	@Test
	public void testCountNodes(){
		assertEquals(0, new BinarySearchTree().countNodes());
		assertEquals(5, testInstance().countNodes());
	}
	
	@Test 
	public void testComputeHeight(){
		assertEquals(0, new BinarySearchTree().computeHeight());
		assertEquals(3, testInstance().computeHeight());
	}
	
	@Test
	public void testContainsNode(){
		BinarySearchTree test = testInstance();
		assertFalse(test.containsNode(1));
		assertTrue(test.containsNode(2));
		assertFalse(test.containsNode(3));
		assertFalse(test.containsNode(4));
		assertTrue(test.containsNode(5));
		assertTrue(test.containsNode(6));
		assertTrue(test.containsNode(7));
		assertFalse(test.containsNode(8));
		assertTrue(test.containsNode(9));
	}
	
	@Test
	public void testFindMax(){
		assertEquals(9, testInstance().findMax());
	}
}
