
/**
 * TestRBT.java created by Zhengjia Mao on Macbook Pro in p2_project
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

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test if a Red-Black tree 
// extension of rbt is correct.  Mostly check node color and position

//@SuppressWarnings("rawtypes")
public class TestRBT {

	protected RBT<Integer, String> rbt;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		rbt = new RBT<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * CASE 123 Insert three values in sorted order and then check the root, left,
	 * and right keys to see if RBT rebalancing occurred.
	 * 
	 */
	@Test
	void testRBT_001_insert_sorted_order_simple() {
		try {
			rbt.insert(10, "10");
			Assert.assertTrue(rbt.rootIsBlack());

			rbt.insert(20, "20");
			Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20));
			Assert.assertEquals(rbt.colorOf(20), RBT.RED);

			rbt.insert(30, "30"); // SHOULD CAUSE REBALANCING
			Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();

		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * CASE 321 Insert three values in reverse sorted order and then check the root,
	 * left, and right keys to see if rebalancing occurred in the other direction.
	 */
	@Test
	void testRBT_002_insert_reversed_sorted_order_simple() {
		try {
			rbt.insert(30, "30");
			Assert.assertTrue(rbt.rootIsBlack());

			rbt.insert(20, "20");
			Assert.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(20));
			Assert.assertEquals(rbt.colorOf(20), RBT.RED);

			rbt.insert(10, "10"); // SHOULD CAUSE REBALANCING
			Assert.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();
		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}

	}

	/**
	 * CASE 132 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred
	 * correctly.
	 */
	@Test
	void testRBT_003_insert_smallest_largest_middle_order_simple() {
		try {
			rbt.insert(10, "10");
			Assert.assertTrue(rbt.rootIsBlack());

			rbt.insert(30, "30");
			Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(30));
			Assert.assertEquals(rbt.colorOf(30), RBT.RED);

			rbt.insert(20, "20"); // SHOULD CAUSE REBALANCING
			Assert.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();
		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}

	}

	/**
	 * CASE 312 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if rebalancing occurred
	 * correctly.
	 */
	@Test
	void testRBT_004_insert_largest_smallest_middle_order_simple() {
		try {
			rbt.insert(30, "30");
			Assert.assertTrue(rbt.rootIsBlack());

			rbt.insert(10, "10");
			Assert.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(10));
			Assert.assertEquals(rbt.colorOf(10), RBT.RED);

			rbt.insert(20, "20"); // SHOULD CAUSE REBALANCING
			Assert.assertTrue(rbt.getKeyOfLeftChildOf(20).equals(10));

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child
			Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
			Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();
		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Inserts 7 values and outputs a preorder traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testRBT_005_pre_order_tra() {
		try {
			rbt.insert(1, "1");
			rbt.insert(2, "2");
			rbt.insert(3, "3");
			rbt.insert(4, "4");
			rbt.insert(5, "5");
			rbt.insert(6, "6");
			rbt.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!rbt.getPreOrderTraversal().toString().equals("[2, 1, 4, 3, 6, 5, 7]")) {
			fail();
		}

	}
	
	/**
	 * Inserts 7 values and outputs a inorder traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testrbt_006_in_order_tra() {
		try {
			rbt.insert(1, "1");
			rbt.insert(2, "2");
			rbt.insert(3, "3");
			rbt.insert(4, "4");
			rbt.insert(5, "5");
			rbt.insert(6, "6");
			rbt.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!rbt.getInOrderTraversal().toString().equals("[1, 2, 3, 4, 5, 6, 7]")) {
			fail();
		}
	}

	/**
	 * Inserts 7 values and outputs a postorder traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testrbt_007_post_order_tra() {
		try {
			rbt.insert(1, "1");
			rbt.insert(2, "2");
			rbt.insert(3, "3");
			rbt.insert(4, "4");
			rbt.insert(5, "5");
			rbt.insert(6, "6");
			rbt.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!rbt.getPostOrderTraversal().toString().equals("[1, 3, 5, 7, 6, 4, 2]")) {
			fail();
		}
	}

	/**
	 * Inserts 7 values and outputs a levelorder traversal list of keys
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testrbt_008_level_order_tra() {
		try {
			rbt.insert(1, "1");
			rbt.insert(2, "2");
			rbt.insert(3, "3");
			rbt.insert(4, "4");
			rbt.insert(5, "5");
			rbt.insert(6, "6");
			rbt.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (!rbt.getLevelOrderTraversal().toString().equals("[2, 1, 4, 3, 6, 5, 7]")) {
			fail();
		}
	}

	/**
	 * Inserts 2 values and try deleting an existing node
	 * 
	 * Check whether the expected exception is thrown
	 */
	@Test
	void testrbt_009_delete() {
		boolean result = false;
		try {
			rbt.insert(1, "1");
			rbt.insert(2, "2");
			rbt.remove(2);
		} catch (UnsupportedOperationException e1) {
			result = true;
		} catch (Exception e2) {
			// do nothing
		}
		if (result == false) {
			fail("no exception throw");
		}
	}

	/**
	 * Inserts 7 values and calculates the height after rebalancing.
	 * 
	 * Check whether it is same as expected
	 */
	@Test
	void testrbt_010_height() {
		try {
			rbt.insert(1, "1");
			rbt.insert(2, "2");
			rbt.insert(3, "3");
			rbt.insert(4, "4");
			rbt.insert(5, "5");
			rbt.insert(6, "6");
			rbt.insert(7, "7");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
		if (rbt.getHeight() != 4) {
			fail();
		}
	}
	
	/**
	 * Try insert an existing key and make sure the expected exception is thrown
	 */
	@Test
	void testrbt_011_insert_duplicate() {
		boolean result = false;
		try {
			rbt.insert(1, "1");
			rbt.insert(1, "1");
		}catch(DuplicateKeyException e1) {
			result = true;
		}catch(Exception e2) {
			//do nothing
		}
		if (result == false) {
			fail("no exception throw");
		}
	}
	

} // copyright Deb Deppeler, all rights reserved, DO NOT SHARE
