
/**
 * TestBST.java created by Zhengjia Mao on Macbook Pro in p2_project
 *
 * Author: 	 Zhengjia Mao(zmao27@wisc.edu)
 * Date: 	 @date 02/28/2020
 * 
 * Course: 	 CS400
 * Semester: Spring 2020
 * Lecture:	 002
 * 
 * IDE:		 Eclipse IDE for Java Developers
 * Version:  2019-12 (4.14.0)
 * Build id: 20191212-1212
 *
 * Device: 	 Zane's MacBook Pro
 * OS: 		 macOS Mojave
 * Version:  10.14.6
 * OS Build: 18G95
 *
 * List Collaborators: NONE
 *
 * Other Credits: NONE
 *
 * Known Bugs: NONE
 */

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@SuppressWarnings("rawtypes")
public class TestBST {

	protected STADT<Integer, String> bst;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		bst = new BST<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * CASE 123 Insert three values in sorted order and then check the root, left,
	 * and right keys to see if insert worked correctly.
	 */
	@Test
	void testBST_001_insert_sorted_order_simple() {
		try {
			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(10))
				fail("insert at root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyOfRightChildOf(10).equals(20))
				fail("insert to right child of root does not work");

			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(10))
				fail("inserting 30 changed root");

			if (!bst.getKeyOfRightChildOf(20).equals(30))
				fail("inserting 30 as right child of 20");

			// IF rebalancing is working,
			// the bst should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
			Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	/**
	 * CASE 321 Insert three values in reverse sorted order and then check the root,
	 * left, and right keys to see if insert worked in the other direction.
	 */
	@Test
	void testBST_002_insert_reversed_sorted_order_simple() {
		try {
			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(30))
				fail("insert at root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyOfLeftChildOf(30).equals(20))
				fail("insert to left child of root does not work");

			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(30))
				fail("inserting 10 changed root");

			if (!bst.getKeyOfLeftChildOf(20).equals(10))
				fail("inserting 10 as left child of 20");

			// IF rebalancing is working,
			// the bst should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	/**
	 * CASE 132 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced bst.
	 * 
	 * Then check the root, left, and right keys to see if insert occurred
	 * correctly.
	 */
	@Test
	void testBST_003_insert_smallest_largest_middle_order_simple() {
		try {
			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(10))
				fail("insert at root does not work");

			bst.insert(30, "30");
			if (!bst.getKeyOfRightChildOf(10).equals(30))
				fail("insert to right child of root does not work");
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));

			bst.insert(20, "20");
			if (!bst.getKeyAtRoot().equals(10))
				fail("inserting 20 changed root");

			if (!bst.getKeyOfLeftChildOf(30).equals(20))
				fail("inserting 20 as left child of 30");

			// IF rebalancing is working,
			// the bst should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	/**
	 * CASE 312 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced bst.
	 * 
	 * Then check the root, left, and right keys to see if insert occurred
	 * correctly.
	 */
	@Test
	void testBST_004_insert_largest_smallest_middle_order_simple() {
		try {
			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(30))
				fail("insert at root does not work");

			bst.insert(10, "10");
			if (!bst.getKeyOfLeftChildOf(30).equals(10))
				fail("insert to left child of root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyAtRoot().equals(30))
				fail("inserting 10 changed root");

			if (!bst.getKeyOfRightChildOf(10).equals(20))
				fail("inserting 20 as right child of 10");

			// the bst should have 30 at the root
			// and 10 as its left child and 20 as 10's right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

			bst.print();

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	/**
	 * Inserts 7 values and outputs a preorder traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testBST_005_pre_order_tra() {
		try {
			bst.insert(4, "4");
			bst.insert(2, "2");
			bst.insert(3, "3");
			bst.insert(1, "1");
			bst.insert(6, "6");
			bst.insert(5, "5");
			bst.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!bst.getPreOrderTraversal().toString().equals("[4, 2, 1, 3, 6, 5, 7]")) {
			fail();
		}

	}

	/**
	 * Inserts 7 values and outputs a inorder traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testBST_006_in_order_tra() {
		try {
			bst.insert(4, "4");
			bst.insert(2, "2");
			bst.insert(3, "3");
			bst.insert(1, "1");
			bst.insert(6, "6");
			bst.insert(5, "5");
			bst.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!bst.getInOrderTraversal().toString().equals("[1, 2, 3, 4, 5, 6, 7]")) {
			fail();
		}
	}

	/**
	 * Inserts 7 values and outputs a postorder traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testBST_007_post_order_tra() {
		try {
			bst.insert(4, "4");
			bst.insert(2, "2");
			bst.insert(3, "3");
			bst.insert(1, "1");
			bst.insert(6, "6");
			bst.insert(5, "5");
			bst.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!bst.getPostOrderTraversal().toString().equals("[1, 3, 2, 5, 7, 6, 4]")) {
			fail();
		}
	}

	/**
	 * Inserts 7 values and outputs a level traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testBST_008_level_order_tra() {
		try {
			bst.insert(4, "4");
			bst.insert(2, "2");
			bst.insert(3, "3");
			bst.insert(1, "1");
			bst.insert(6, "6");
			bst.insert(5, "5");
			bst.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!bst.getLevelOrderTraversal().toString().equals("[4, 2, 6, 1, 3, 5, 7]")) {
			fail();
		}
	}

	
	@Test
	void testBST_009_delete() {
		try {
			bst.insert(1, "1");
			bst.insert(2, "2");
			bst.insert(3, "3");
			bst.remove(2);
			if (bst.contains(2)) {
				fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	@Test
	void testBST_010_height() {
		try {
			bst.insert(1, "1");
			bst.insert(2, "2");
			bst.insert(3, "3");
			bst.insert(4, "4");
			bst.insert(5, "5");
			bst.insert(6, "6");
			bst.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (bst.getHeight() != 7) {
			fail();
		}
	}

	/**
	 * Try insert an existing key and make sure the expected exception is thrown
	 */
	@Test
	void testBST_011_insert_duplicate() {
		boolean result = false;
		try {
			bst.insert(1, "1");
			bst.insert(1, "1");
		}catch(DuplicateKeyException e1) {
			result = true;
		}catch(Exception e2) {
			//do nothing
		}
		if (result == false) {
			fail("no exception throw");
		}
	}
	

}
