import static org.junit.Assert.*;

import org.junit.Test;


public class TestLinkedStringList {

	public LinkedStringList getTestCase(){
		LinkedStringList output = new LinkedStringList("dd");
		output.addToBeginning("cc");
		output.addToBeginning("bb");
		output.addToBeginning("aa");
		return output;
	}
	
	@Test
	public void testAddToEnd() {
		LinkedStringList testCase = getTestCase();
		testCase.addToEnd("ee");
		testCase.addToEnd("ff");
		assertEquals("aabbccddeeff", testCase.toString());
	}
	
	@Test
	public void testSize(){
		LinkedStringList testCase = getTestCase();
		assertEquals(4, testCase.size());
		testCase.addToBeginning("aa");
		assertEquals(5, testCase.size());
	}

	@Test
	public void testContainsNode(){
		LinkedStringList testCase = getTestCase();
		assertTrue(testCase.containsNode("aa"));
		assertTrue(testCase.containsNode("bb"));
		assertTrue(testCase.containsNode("cc"));
		assertTrue(testCase.containsNode("dd"));
		assertFalse(testCase.containsNode("a"));
		assertFalse(testCase.containsNode("b"));
		assertFalse(testCase.containsNode("ee"));
	}
	

	@Test
	public void testGetNode(){
		LinkedStringList testCase = getTestCase();
		assertEquals("aa", testCase.getNode(0));
		assertEquals("bb", testCase.getNode(1));
		assertEquals("cc", testCase.getNode(2));
		assertEquals("dd", testCase.getNode(3));
		assertEquals(null, testCase.getNode(-1));
		assertEquals(null, testCase.getNode(4));
	}
	
	@Test 
	public void testDeleteNode(){
		LinkedStringList testCase = getTestCase();
		testCase.deleteNode(1);
		assertEquals("aaccdd", testCase.toString());
		testCase.deleteNode(0);
		assertEquals("ccdd", testCase.toString());
		testCase.deleteNode(-1);
		assertEquals("ccdd", testCase.toString());
		testCase.deleteNode(4);
		assertEquals("ccdd", testCase.toString());
	}
}
