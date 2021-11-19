
/**
 * BST.java created by Zhengjia Mao on Macbook Pro in p2_project
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

import java.util.LinkedList;
import java.util.List;

// DO IMPLEMENT A BINARY SEARCH TREE IN THIS CLASS

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface that are required solely to
 * support gray-box testing of the internal tree structure. They must be
 * implemented as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.
 * @param <V> A value associated with the given key.
 */
public class BST<K extends Comparable<K>, V> implements STADT<K, V> {

	/**
	 * private inner class, representing a node in the tree
	 * 
	 * @author zmao27 (2020)
	 *
	 */
	private class Node {
		private K key;
		private V value;
		private Node leftNode;
		private Node rightNode;
		// private List<Node> Children;
		private int height;
		private int bf;

		/**
		 * empty constructor
		 */
		private Node() {
			this.key = null;
			this.value = null;
			this.leftNode = null;
			this.rightNode = null;
			height = 1;
		}

		/**
		 * Constructor with K V only
		 */
		private Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.leftNode = null;
			this.rightNode = null;
			height = 1;
		}

		/**
		 * Constructor with K V left right
		 */
		private Node(K key, V value, Node leftNode, Node rightNode) {
			this.key = key;
			this.value = value;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			height = 1;
		}

		/**
		 * Mutator, set the left as the given node
		 * 
		 * @param node
		 */
		private void setLeft(Node node) {
			this.leftNode = node;
		}

		/**
		 * Mutator, set the right as the given node
		 * 
		 * @param node
		 */
		private void setRight(Node node) {
			this.rightNode = node;
		}

	}

	private Node root;
	private int size;
	private List<K> traversedList;

	/**
	 * no-arg constructor
	 */
	public BST() {
		root = null;
	}

	/**
	 * Constructor with arg
	 * 
	 * @param node
	 */
	public BST(Node node) {
		root = node;
	}

	/**
	 * Returns the key that is in the root node of this ST. If root is null, returns
	 * null.
	 * 
	 * @return key found at root node, or null
	 */
	public K getKeyAtRoot() {
		if (root == null) {
			return null;
		} else {
			return root.key;
		}
	}

	/**
	 * A helper method that searches down from the given subRoot and locates the
	 * node with the given key
	 * 
	 * @param subRoot
	 * @param key
	 * @return the found node
	 */
	private Node searchHelper(Node subRoot, K key) {
		if (subRoot == null) {
			return subRoot;
		}
		if (subRoot.key.compareTo(key) == 0) {
			return subRoot;
		}
		if (key.compareTo(subRoot.key) < 0) {
			return searchHelper(subRoot.leftNode, key);
		} else {
			return searchHelper(subRoot.rightNode, key);
		}

	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the left child. If
	 * the left child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the left child of the found key
	 * 
	 * @throws IllegalNullKeyException if key argument is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}

		return searchHelper(root, key).leftNode.key;
	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the right child. If
	 * the right child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the right child of the found key
	 * 
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) {
			throw new KeyNotFoundException();
		}

		return searchHelper(root, key).rightNode.key;
	}

	/**
	 * Returns the height of this BST. H is defined as the number of levels in the
	 * tree.
	 * 
	 * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max(
	 * height(root.left), height(root.right) )
	 * 
	 * Examples: A BST with no keys, has a height of zero (0). A BST with one key,
	 * has a height of one (1). A BST with two keys, has a height of two (2). A BST
	 * with three keys, can be balanced with a height of two(2) or it may be linear
	 * with a height of three (3) ... and so on for tree with other heights
	 * 
	 * @return the number of levels that contain keys in this BINARY SEARCH TREE
	 */
	public int getHeight() {
		return getHeightHelper(root);
	}

	/**
	 * Recursive helper method to cumulatively get the height
	 * 
	 * @param subRoot
	 * @return height
	 */
	private int getHeightHelper(Node subRoot) {
		if (subRoot == null)
			return 0;
		else {
			return subRoot.height + max(getHeightHelper(subRoot.leftNode), getHeightHelper(subRoot.rightNode));
		}
	}

	/**
	 * Helper method that returns the larger number in the comparison
	 * 
	 * @param a
	 * @param b
	 * @return the larger number
	 */
	private int max(int a, int b) {
		if (a > b) {
			return a;
		} else if (a < b) {
			return b;
		} else {
			return a;
		}
	}

	/**
	 * Returns the keys of the data structure in sorted order. In the case of binary
	 * search trees, the visit order is: L V R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in-order
	 */
	public List<K> getInOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelp(root, "INORDER");
		return traversedList;
	}

	/**
	 * Returns the keys of the data structure in pre-order traversal order. In the
	 * case of binary search trees, the order is: V L R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in pre-order
	 */
	public List<K> getPreOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelp(root, "PREORDER");
		return traversedList;
	}

	/**
	 * Returns the keys of the data structure in post-order traversal order. In the
	 * case of binary search trees, the order is: L R V
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in post-order
	 */
	public List<K> getPostOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelp(root, "POSTORDER");
		return traversedList;
	}

	/**
	 * Returns the keys of the data structure in level-order traversal order.
	 * 
	 * The root is first in the list, then the keys found in the next level down,
	 * and so on.
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in level-order
	 */
	public List<K> getLevelOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelp(root, "LEVELORDER");
		return traversedList;
	}

	private void traverseHelp(Node current, String order) {
		// detects the expected order
		if (order.equals("PREORDER")) {
			if (current == null) {
				return;
			}
			// visit, left, right
			traversedList.add(current.key);
			traverseHelp(current.leftNode, order);
			traverseHelp(current.rightNode, order);
		}
		// detects the expected order
		else if (order.equals("INORDER")) {
			if (current == null) {
				return;
			}
			// left, visit, right
			traverseHelp(current.leftNode, order);
			traversedList.add(current.key);
			traverseHelp(current.rightNode, order);
		}
		// detects the expected order
		else if (order.equals("POSTORDER")) {
			if (current == null) {
				return;
			}
			// left, right, visit
			traverseHelp(current.leftNode, order);
			traverseHelp(current.rightNode, order);
			traversedList.add(current.key);
		}
		// detects the expected order
		else if (order.equals("LEVELORDER")) {
			if (current == null) {
				return;
			}
			int height = getHeight();
			for (int i = 1; i <= height; i++) {
				levelOrderHelper(current, i);
			}
		} else {
			// do nothing
		}
	}

	/**
	 * Recursive helper method for level order
	 * 
	 * @param current
	 * @param level
	 */
	private void levelOrderHelper(Node current, int level) {
		if (current == null) {
			return;
		}
		if (level == 1) {
			traversedList.add(current.key);
		} else if (level > 1) {
			levelOrderHelper(current.leftNode, level - 1);
			levelOrderHelper(current.rightNode, level - 1);
		} else {
			return;
		}
	}

	/**
	 * Add the key,value pair to the data structure and increase the number of keys.
	 * If key is null, throw IllegalNullKeyException; If key is already in data
	 * structure, throw DuplicateKeyException(); Do not increase the num of keys in
	 * the structure, if key,value pair is not added.
	 */
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (contains(key)) {
			throw new DuplicateKeyException();
		}
		Node newNode = new Node(key, value);
		root = insertHelper(root, newNode);
		size++;
	}

	/**
	 * Recursive helper method that searches down from the given subroot and insert
	 * the new Node at the appropriate place
	 * 
	 * @param subRoot
	 * @param newNode
	 * @return the lowest subRoot
	 * @throws DuplicateKeyException if key already exists
	 */
	private Node insertHelper(Node subRoot, Node newNode) throws DuplicateKeyException {
		if (subRoot == null) {
			subRoot = newNode;
			return subRoot;
		}
		if (newNode.key.compareTo(subRoot.key) < 0) {
			subRoot.setLeft(insertHelper(subRoot.leftNode, newNode));
		}

		else if (newNode.key.compareTo(subRoot.key) > 0) {
			subRoot.setRight(insertHelper(subRoot.rightNode, newNode));
		}

		else {
			throw new DuplicateKeyException();
		}
		return subRoot;
	}

	/**
	 * If key is found, remove the key,value pair from the data structure and
	 * decrease num keys, and return true. If key is not found, do not decrease the
	 * number of keys in the data structure, return false. If key is null, throw
	 * IllegalNullKeyException
	 */
	public boolean remove(K key) throws IllegalNullKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (!contains(key)) {
			return false;
		}
		root = removeHelper(root, key);
		size--;
		return true;
	}

	/**
	 * Recursive helper method that removes the given node
	 * 
	 * @param subRoot
	 * @param key
	 * @return the removed node
	 */
	private Node removeHelper(Node subRoot, K key) {
		if (subRoot == null)
			return null;
		// compare key and nodes, and recurse down the tree
		if (key.compareTo(subRoot.key) < 0) {
			// the key is smaller than the "root" of the subtree
			subRoot.setLeft(removeHelper(subRoot.leftNode, key));
		} else if (key.compareTo(subRoot.key) > 0) {
			// the key is bigger than the "root" of the subtree
			subRoot.setRight(removeHelper(subRoot.rightNode, key));
		} else {// the node is the one to be deleted
			// no child
			if (subRoot.leftNode == null && subRoot.rightNode == null)
				return null;
			// one child
			if (subRoot.leftNode == null) {
				return subRoot.rightNode;
			} else if (subRoot.rightNode == null) {
				return subRoot.leftNode;
			}
			// two children
			else {
				// if the node has two children, find the successor by using findSuccessor()
				subRoot = findSuccessor(subRoot.rightNode);
				// find the successor for the removed successor
				subRoot.setLeft(removeHelper(subRoot.leftNode, subRoot.key));
			}
		}
		return subRoot;
	}

	/**
	 * Helper method that finds the inorder successor from the node
	 * 
	 * @param subRoot
	 * @return the in order successor node
	 */
	private Node findSuccessor(Node subRoot) {
		Node current = subRoot;
		while (current.leftNode != null) {
			current = subRoot.leftNode;
		}
		return current;
	}

	/**
	 * Returns the value associated with the specified key.
	 *
	 * Does not remove key or decrease number of keys If key is null, throw
	 * IllegalNullKeyException If key is not found, throw KeyNotFoundException().
	 */
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		return searchHelper(root, key).value;
	}

	/**
	 * Returns true if the key is in the data structure If key is null, throw
	 * IllegalNullKeyException Returns false if key is not null and is not present
	 */
	public boolean contains(K key) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		if (root == null)
			return false;
		return searchHelper(root, key) != null;
	}

	/**
	 * Returns the number of key,value pairs in the data structure
	 */
	public int numKeys() {
		return size;
	}

	/**
	 * Print the tree.
	 *
	 * For our testing purposes of your print method: all keys that we insert in the
	 * tree will have a string length of exactly 2 characters. example: numbers
	 * 10-99, or strings aa - zz, or AA to ZZ
	 *
	 * This makes it easier for you to not worry about spacing issues.
	 *
	 * You can display a binary search in any of a variety of ways, but we must see
	 * a tree that we can identify left and right children of each node
	 *
	 * For example:
	 * 
	 * 30 /\ / \ 20 40 / /\ / / \ 10 35 50
	 * 
	 * Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
	 * 
	 * Or, you can display a tree of this kind.
	 * 
	 * | |-------50 |-------40 | |-------35 30 |-------20 | |-------10
	 * 
	 * Or, you can come up with your own orientation pattern, like this.
	 * 
	 * 10 20 30 35 40 50
	 * 
	 * The connecting lines are not required if we can interpret your tree.
	 * 
	 */
	public void print() {
		System.out.println("\nBSTree view (rotated 90 degree CCW)");
		System.out.println("-------------------");
		int level = 0;
		printHelper(root, level);// call helper method
		System.out.println("\n\n-------------------\n");
	}

	/**
	 * Helper method that visualizes the tree
	 * 
	 * @param subRoot
	 * @param level
	 */
	private void printHelper(Node subRoot, int level) {
		if (subRoot == null)// check if null
			return;
		level += 1;
		printHelper(subRoot.rightNode, level);// print right side
		System.out.println();
		for (int i = 1; i <= level - 1; i++)// print the space
			System.out.print("    ");
		System.out.print(subRoot.key);// print the node
		printHelper(subRoot.leftNode, level);// print left side

	}

	public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		BST tree = new BST();
		tree.insert(4, "4");
		tree.insert(2, "2");
		tree.insert(3, "3");
		tree.insert(1, "1");
		tree.insert(6, "6");
		tree.insert(5, "5");
		tree.insert(7, "7");
		System.out.println(tree.size);
		tree.print();
		System.out.println("PreOrder: " + tree.getPreOrderTraversal().toString());
		System.out.println("InOrder: " + tree.getInOrderTraversal().toString());
		System.out.println("PostOrder: " + tree.getPostOrderTraversal().toString());
		System.out.println("LevelOrder: " + tree.getLevelOrderTraversal().toString());
		System.out.println(tree.getHeight());
	}
} // copyrighted material, students do not have permission to post on public sites

//  deppeler@cs.wisc.edu
