
/**
 * RBT.java created by Zhengjia Mao on Macbook Pro in p2_project
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements a generic Red-Black tree extension of BST<K,V>.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS DO NOT ADD ANY PACKAGE
 * LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove. If you do not override this
 * operation, you may still have the method in your type, and have the method
 * throw new UnsupportedOperationException. See
 * https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K, V> {

	/**
	 * private inner class, representing a node in the tree
	 * 
	 * @author zmao27 (2020)
	 *
	 */
	private class Node {
		// private fields
		private K key;
		private V value;
		private Node leftNode;
		private Node rightNode;
		private int height;
		private int color;

		/**
		 * empty constructor
		 */
		private Node() {
			key = null;
			value = null;
			leftNode = null;
			rightNode = null;
			color = RBT.RED;
			height = 0;
		}

		/**
		 * Constructor with K V Color
		 */
		private Node(K key, V value, int color) {
			this.key = key;
			this.value = value;
			this.leftNode = null;
			this.rightNode = null;
			this.color = color;
			height = 1;
		}

		/**
		 * Constructor with K V only
		 */
		private Node(K key, V value) {
			this.key = key;
			this.value = value;
			color = RED;
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

		/**
		 * Whether it is a red node
		 * 
		 * @return true if color is red
		 */
		private boolean isRed() {
			return color == RED;
		}

		/**
		 * Whether it is a black node
		 * 
		 * @return true if color is black
		 */
		private boolean isBlack() {
			return color == BLACK;
		}
	}

	// USE AND DO NOT EDIT THESE CONSTANTS
	public static final int RED = 0;
	public static final int BLACK = 1;
	private Node root;
	private int size;
	private List<K> traversedList;

	/**
	 * no-arg constructor
	 */
	public RBT() {
		root = null;
		size = 0;
		traversedList = new ArrayList<K>();
	}

	/**
	 * Constructor with arg
	 * 
	 * @param node
	 */
	public RBT(Node node) {
		root = node;
		size = 0;
		traversedList = new ArrayList<K>();
	}

	/**
	 * Returns the color of the node that contains the specified key. Returns
	 * RBT.RED if the node is red, and RBT.BLACK if the node is black. Returns -1 if
	 * the node is not found.
	 * 
	 * @param
	 * @return
	 */
	public int colorOf(K key) {
		Node found = searchHelper(root, key); // TODO Auto-generated method stub
		return found == null ? -1 : found.color;
	}

	/**
	 * Returns true if root is null or the color of the root is black. If root is
	 * null, returns true.
	 * 
	 * @return true if root is black, else returns false (for red)
	 */
	public boolean rootIsBlack() {
		if (root == null) {
			return true;
		}
		return root.color == RBT.BLACK;
	}

	/**
	 * Returns true if the node that contains this key is RED. If key is null,
	 * throws IllegalNullKeyException. If key is not found, throws
	 * KeyNotFoundException.
	 * 
	 * @return true if the key is found and the node's color is RED, else return
	 *         false if key is found and the node's color is BLACK.
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	public boolean isRed(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException("null key");
		}
		if (!contains(key)) {
			throw new KeyNotFoundException("not found");
		}
		return searchHelper(root, key).color == RBT.RED;
	}

	/**
	 * Returns true if the node that contains this key is BLACK. If key is null,
	 * throws IllegalNullKeyException. If key is not found, throws
	 * KeyNotFoundException.
	 * 
	 * @return true if the key is found and the node's color is BLACK, else return
	 *         false if key is found and the node's color is RED.
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	public boolean isBlack(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException("null key");
		}
		if (!contains(key)) {
			throw new KeyNotFoundException("not found");
		}
		return searchHelper(root, key).color == RBT.BLACK;
	}

	/**
	 * Returns the key that is in the root node of this ST. If root is null, returns
	 * null.
	 * 
	 * @return key found at root node, or null
	 */
	@Override
	public K getKeyAtRoot() {
		if (root == null) {
			return null;
		}
		return root.key;
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public List<K> getInOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelper(root, "INORDER");
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
	@Override
	public List<K> getPreOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelper(root, "PREORDER");
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
	@Override
	public List<K> getPostOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelper(root, "POSTORDER");
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
	@Override
	public List<K> getLevelOrderTraversal() {
		// first time traversing need to initialize LinkedList
		if (traversedList == null) {
			traversedList = new LinkedList<K>();
		} else {
			// clear the list to start over for a new traversal
			traversedList.clear();
		}
		traverseHelper(root, "LEVELORDER");
		return traversedList;
	}

	/**
	 * Recursive helper method that attaches the node to the list depending on the
	 * required order
	 * 
	 * @param current
	 * @param order
	 */
	private void traverseHelper(Node current, String order) {
		// detects the expected order
		if (order.equals("PREORDER")) {
			if (current == null) {
				return;
			}
			// visit, left, right
			traversedList.add(current.key);
			traverseHelper(current.leftNode, order);
			traverseHelper(current.rightNode, order);
		}
		// detects the expected order
		else if (order.equals("INORDER")) {
			if (current == null) {
				return;
			}
			// left, visit, right
			traverseHelper(current.leftNode, order);
			traversedList.add(current.key);
			traverseHelper(current.rightNode, order);
		}
		// detects the expected order
		else if (order.equals("POSTORDER")) {
			if (current == null) {
				return;
			}
			// left, right, visit
			traverseHelper(current.leftNode, order);
			traverseHelper(current.rightNode, order);
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
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		if (contains(key)) {
			throw new DuplicateKeyException();
		}
		if (root == null) {
			root = new Node(key, value, BLACK);
			size++;
		} else {
			Node newNode = new Node(key, value, RED);
			root = insertHelper(root, newNode);
			root = fix(root, newNode);
			size++;
		}
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
		} else if (newNode.key.compareTo(subRoot.key) > 0) {
			subRoot.setRight(insertHelper(subRoot.rightNode, newNode));
		} else {
			throw new DuplicateKeyException("key exists");
		}

		return subRoot;

	}

	/**
	 * Helper method that rebalances the RBT after each insertion
	 * 
	 * @param subRoot
	 * @param K
	 * @return the root from the new built balanced-tree
	 * @throws IllegalNullKeyException - if key is null
	 */
	public Node fix(Node subRoot, Node K) throws IllegalNullKeyException {
		if (root == K) // no action needed
			return K;
		Node P = parentHelper(K);
		if (P.isBlack()) // no action needed
			return subRoot;
		Node GP = parentHelper(P);

		// if red parent and a null/black uncle
		if (GP.rightNode == null || GP.rightNode.isBlack()) { // uncle on the right
			if (K.equals(P.leftNode))
				return rightRotate(GP, P, K);
			if (K.equals(P.rightNode))
				return leftRightRotate(GP, P, K);
		}
		if (GP.leftNode == null || GP.leftNode.isBlack()) { // uncle on the left
			if (K.equals(P.rightNode))
				return leftRotate(GP, P, K);
			if (K.equals(P.leftNode))
				return rightLeftRotate(GP, P, K);
		}

		// if red parent and a red uncle
		if (GP.leftNode.isRed() && GP.rightNode.isRed()) {
			GP.color = RED;
			GP.leftNode.color = BLACK;
			GP.rightNode.color = BLACK;
			K.color = RED;
			root.color = BLACK;
			return fix(root, GP);
		}

		root.color = BLACK;
		return root;
	}

	/**
	 * Helper method to find the parent of the given node
	 * @param child
	 * @return the parent node
	 * @throws IllegalNullKeyException - if child is null
	 */
	private Node parentHelper(Node child) throws IllegalNullKeyException {

		if (child == null) {
			throw new IllegalNullKeyException();
		}
		Node cur = root;
		while (cur.leftNode != child && cur.rightNode != child) {
			if (child.key.compareTo(cur.key) < 0) {
				cur = cur.leftNode;
			} else if (child.key.compareTo(cur.key) > 0) {
				cur = cur.rightNode;
			} else {
				// do nothing
			}
		}
		return cur;
	}

	/**
	 * rotate right and reconstruct the arrangement of child, parent, and grandparent nodes.
	 * @param GP
	 * @param P
	 * @param K
	 * @return the root from the new constructed tree
	 */
	private Node rightRotate(Node GP, Node P, Node K) {
		// store the key and value information from GP, P, K
		Node GPCopy = new Node(GP.key, GP.value);
		Node PCopy = new Node(P.key, P.value);
		Node KCopy = new Node(K.key, K.value);
		Node tempNode1 = GP.rightNode;
		Node tempNode2 = P.rightNode;
		Node tempNode3 = K.leftNode;
		Node tempNode4 = K.rightNode;

		GP.value = PCopy.value;
		GP.key = PCopy.key;
		P.value = KCopy.value;
		P.key = KCopy.key;
		P.setLeft(tempNode3);
		P.setRight(tempNode4);
		GP.setRight(GPCopy);
		GPCopy.setRight(tempNode1);
		GPCopy.setLeft(tempNode2);

		// change colors
		GP.color = BLACK;
		GPCopy.color = RED;
		P.color = RED;
		return root;
	}

	/**
	 * rotate left and reconstruct the arrangement of child, parent, and grandparent nodes.
	 * @param GP
	 * @param P
	 * @param K
	 * @return the root from the new constructed tree
	 */
	private Node leftRotate(Node GP, Node P, Node K) {
		// store the key and value information from GP, P, K
		Node GPCopy = new Node(GP.key, GP.value);
		Node PCopy = new Node(P.key, P.value);
		Node KCopy = new Node(K.key, K.value);
		Node tempNode1 = GP.leftNode;
		Node tempNode2 = P.leftNode;
		Node tempNode3 = K.leftNode;
		Node tempNode4 = K.rightNode;

		GP.value = PCopy.value;
		GP.key = PCopy.key;
		P.value = KCopy.value;
		P.key = KCopy.key;

		GP.setLeft(GPCopy);
		GPCopy.setLeft(tempNode1);
		GPCopy.setRight(tempNode2);
		P.setLeft(tempNode3);
		P.setRight(tempNode4);

		// change colors
		GP.color = BLACK;
		GPCopy.color = RED;
		P.color = RED;
		return root;
	}

	/**
	 * rotate left nad right, reconstruct the arrangement of child, parent, and grandparent nodes.
	 * @param GP
	 * @param P
	 * @param K
	 * @return the root from the new constructed tree
	 */
	private Node leftRightRotate(Node GP, Node P, Node K) {
		// store the key and value information from GP, P, K
		Node GPCopy = new Node(GP.key, GP.value);
		Node PCopy = new Node(P.key, P.value);
		Node KCopy = new Node(K.key, K.value);
		Node tempNode1 = GP.rightNode;
		Node tempNode2 = K.leftNode;
		Node tempNode3 = K.rightNode;

		GP.value = KCopy.value;
		GP.key = KCopy.key;
		GP.setRight(GPCopy);
		GPCopy.setLeft(tempNode3);
		GPCopy.setRight(tempNode1);
		P.setRight(tempNode2);

		// change colors
		GP.color = BLACK;
		GPCopy.color = RED;
		P.color = RED;

		return root;
	}
	/**
	 * rotate right and left, reconstruct the arrangement of child, parent, and grandparent nodes.
	 * @param GP
	 * @param P
	 * @param K
	 * @return the root from the new constructed tree
	 */
	private Node rightLeftRotate(Node GP, Node P, Node K) {
		// store the key and value information from GP, P, K
		Node GPCopy = new Node(GP.key, GP.value);
		Node PCopy = new Node(P.key, P.value);
		Node KCopy = new Node(K.key, K.value);
		Node tempNode1 = GP.leftNode;
		Node tempNode2 = K.leftNode;
		Node tempNode3 = K.rightNode;

		GP.value = KCopy.value;
		GP.key = KCopy.key;
		GP.setLeft(GPCopy);
		GPCopy.setLeft(tempNode1);
		GPCopy.setRight(tempNode2);
		P.setLeft(tempNode3);

		// change colors
		GP.color = BLACK;
		GPCopy.color = RED;
		P.color = RED;

		return root;
	}

	/**
	 * If key is found, remove the key,value pair from the data structure and
	 * decrease num keys, and return true. If key is not found, do not decrease the
	 * number of keys in the data structure, return false. If key is null, throw
	 * IllegalNullKeyException
	 * 
	 * **In this case, remove is not implemented**
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		throw new UnsupportedOperationException("unsupported method");
	}

	/**
	 * Returns the value associated with the specified key.
	 *
	 * Does not remove key or decrease number of keys If key is null, throw
	 * IllegalNullKeyException If key is not found, throw KeyNotFoundException().
	 * 
	 * @throws IllegalNullKeyException
	 * @throws KeyNotFoundException
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		return searchHelper(root, key).value;
	}

	/**
	 * Returns true if the key is in the data structure If key is null, throw
	 * IllegalNullKeyException Returns false if key is not null and is not present
	 * 
	 * @throws IllegalNullKeyException
	 */
	@Override
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
	@Override
	public int numKeys() {
		return size;
	}

	/**
	 * Print the tree.
	 */
	@Override
	public void print() {
		System.out.println("RBTree view (rotated 90 degree CCW)");
		System.out.println("\n-------------------");
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
		if (subRoot == null)
			return;
		level += 1;
		printHelper(subRoot.rightNode, level);// print right side
		System.out.println();
		for (int i = 1; i <= level - 1; i++)// print the space
			System.out.print("    ");
		System.out.print(subRoot.key + "[" + subRoot.color + "]");// print the node
		printHelper(subRoot.leftNode, level);// print left side

	}

	public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
		RBT rbt = new RBT();
//		rbt.insert(10, "10");
//		rbt.insert(20, "20");
//		rbt.insert(30, "30");
//		System.out.println(rbt.colorOf(20) == RBT.RED);
		rbt.insert(1, "1");
		rbt.insert(2, "2");
		rbt.insert(3, "3");
		rbt.insert(4, "4");
		rbt.insert(5, "5");
		rbt.insert(6, "6");
		rbt.insert(7, "7");
		rbt.print();
		System.out.println("size: " + rbt.size);
		System.out.println("height: " + rbt.getHeight());
		System.out.println("PreOrder: " + rbt.getPreOrderTraversal().toString());
		System.out.println("InOrder: " + rbt.getInOrderTraversal().toString());
		System.out.println("PostOrder: " + rbt.getPostOrderTraversal().toString());
		System.out.println("LevelOrder: " + rbt.getLevelOrderTraversal().toString());
	}

}
