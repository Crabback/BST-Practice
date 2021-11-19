/**
 * CompareDS.java created by Zhengjia Mao on Macbook Pro in p2_project
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

/**
 * CompareDS - A class that compares the performance of two tree DS.
 * 
 * @author zmao27 (2020)
 *
 */
public class CompareDS {
	RBT rbt;
	BST bst;
	int n;

	/**
	 * constructor 
	 * @param n - number of items to perform on
	 */
	public CompareDS(int n) {
		rbt = new RBT();
		bst = new BST();
		this.n = n;
	}

	/**
	 * Compare method that outputs the processing time
	 * 
	 * @throws IllegalNullKeyException
	 * @throws DuplicateKeyException
	 */
	public void compareInsert() throws IllegalNullKeyException, DuplicateKeyException {
		System.out.println("CompareDS.main Compares work time for: " + bst.getClass().getName() + " and "
				+ rbt.getClass().getName());
		System.out.println("Description: Insert " + n + " items\n");

		long startTime1 = System.nanoTime();
		for (int i = 0; i < n; i++) { // runBST
			bst.insert(String.valueOf(i), Integer.toString(i));
		}
		long endTime1 = System.nanoTime();
		System.out.println(bst.getClass().getName() + " is doing work for " + n + " values");
		System.out.println("It took " + (endTime1 - startTime1) + " ns to process " + n + " items");
		
		long startTime2 = System.nanoTime();
		for (int j = 0; j < n; j++) { // runB
			rbt.insert(String.valueOf(j), Integer.toString(j));
		}
		long endTime2 = System.nanoTime();
		System.out.println(rbt.getClass().getName() + " is doing work for " + n + " values");
		System.out.println("It took " + (endTime2 - startTime2) + " ns to process " + n + " items\n");
	}

	/**
	 * @param args
	 * @throws DuplicateKeyException 
	 * @throws IllegalNullKeyException 
	 */
	public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException {
		CompareDS c_100 = new CompareDS(100);
		c_100.compareInsert();

	}

}
