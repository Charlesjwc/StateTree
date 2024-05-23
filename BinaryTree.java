import java.util.*;
/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Charles Chang
 *	@since	16 May, 2024
 */
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	private final String STATE_FILE = "states2.txt";
	
	/**	constructor for BinaryTree */
	public BinaryTree() {
		root = null;
	}
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) {
		//	Create Node
		TreeNode newNode = new TreeNode(value);
		TreeNode current = root;
		TreeNode last = null;
		while (current != null) {
			last = current;
			if (newNode.getValue().compareTo(current.getValue()) < 0) {
				if (current.getLeft() == null) {
					current.setLeft(newNode);
					return;
				}
				current = current.getLeft();
			}
			else {
				if (current.getRight() == null) {
					current.setRight(newNode);
					return;
				}
				current = current.getRight();
			}
		}
		root = newNode;
	}
	
	/**
	 *	Print Binary Tree Inorde
	 */
	public void printInorder() {
		printInorderRecurse(root);
	}
	private void printInorderRecurse(TreeNode node) {
		if (node == null)
			return;
		printInorderRecurse(node.getLeft());
		System.out.print(node.getValue() + " ");
		printInorderRecurse(node.getRight());
	}
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() {
		printPreorderRecurse(root);
	}
	private void printPreorderRecurse(TreeNode node) {
		if (node == null)
			return;
		System.out.print(node.getValue() + " ");
		printPreorderRecurse(node.getLeft());
		printPreorderRecurse(node.getRight());
	}
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() {
		printPostorderRecurse(root);
	}
	private void printPostorderRecurse(TreeNode node) {
		if (node == null)
			return;
		printPostorderRecurse(node.getLeft());
		printPostorderRecurse(node.getRight());
		System.out.print(node.getValue() + " ");
	}
		
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();
		//	Sorted Inputs
		ArrayList<E> inputs = new ArrayList<E>();
		getSortedList(root, inputs); 
		//	Input
		balancedTreeRecurse(balancedTree, inputs, 0, inputs.size() - 1);
		//	Return
		return balancedTree;
	}
	
	public void getSortedList(TreeNode<E> node, ArrayList<E> list) {
		if (node == null)
			return;
		getSortedList(node.getLeft(), list);
		list.add(node.getValue());
		getSortedList(node.getRight(), list);
	}
	private void balancedTreeRecurse(BinaryTree<E> tree, ArrayList<E> input,
									int min, int max) {
		if (min > max)
			return;
		int mid = (min + max) / 2;
		tree.add(input.get(mid));
		balancedTreeRecurse(tree, input, min, mid - 1);
		balancedTreeRecurse(tree, input, mid + 1, max);
	}
	
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(E value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		if (node.getValue().equals(value)) {
			if (node.getLeft() == null && node.getRight() == null)
				return null;
			else if (node.getRight() == null)
				return node.getLeft();
			else {
				//	Replace the value of node with the smallest value on
				//	right subtree. Delete the original smallest value
				E smallest = deleteSmallest(node.getRight());
				TreeNode<E> newNode = new TreeNode<E>(smallest, 
										node.getLeft(), node.getRight());
				return newNode;
			}
		} else if (node.getValue().compareTo(value) > 0) {
			node.setLeft(remove(node.getLeft(), value));
			return node;
		}
		else {
			node.setRight(remove(node.getRight(), value));
			return node;
		}
	}
	private E deleteSmallest(TreeNode<E> node) {
		if (node.getLeft() == null) {
			E temp = node.getValue();
			remove(root, temp);
			return temp;
		}
		else return deleteSmallest(node.getLeft());
	}
	
	
	
	
	//	State tree methods
	public void loadData() {
		Scanner s = FileUtils.openToRead(STATE_FILE);
		int count = 1;
		
		String name = "";
		String abbreviation = "";
		int population = 0;
		int area = 0;
		int reps = 0;
		String capital = "";
		int month = 0;
		int day = 0;
		int year = 0;
		
		while (s.hasNext()) {
			String input = s.next();
			switch (count) {
				case 1:	name = input;			break;
				case 2:	abbreviation = input;	break;
				case 3:	population = Integer.parseInt(input);	break;
				case 4:	area = Integer.parseInt(input);			break;
				case 5:	reps = Integer.parseInt(input);			break;
				case 6:	capital = input;		break;
				case 7:	month = Integer.parseInt(input);		break;
				case 8:	day = Integer.parseInt(input);			break;
				case 9:	year = Integer.parseInt(input);			break;
			}
			if (count == 9) {
				insert((E)(new State(name, abbreviation, population, area, reps,
						capital, month, day, year)));
				count = 0;
			}
			count ++;
		}
	}
	
	public void insert(E next) {
		add(next);
	}
	
	public void printList() {
		if (root == null) {
			System.out.println("ERROR: NO TREE");
			return;
		}
		printRecurse(root.getLeft());
		System.out.println(root.getValue());
		printRecurse(root.getRight());
	}
	
	private void printRecurse(TreeNode node) {
		if (node == null)
			return;
		printRecurse(node.getLeft());
		System.out.println(node.getValue());
		printRecurse(node.getRight());
	}
	
	public void testFind() {
		System.out.println("Testing search algorithm\n");
		String find = Prompt.getString("Enter state name to search for (Q to quit)");
	}
	
	public int size() {
		if (root == null)
			return 0;
		int sum = 0;
		sum += size(root.getLeft());
		sum += 1;
		sum += size(root.getRight());
		return sum;
	}
	
	private int size(TreeNode node) {
		if (node == null)
			return 0;
		int sum = 0;
		sum += size(node.getLeft());
		sum += 1;
		sum += size(node.getRight());
		return sum;
	}
	
	public void clear() {
		root = null;
	}
	
	public void printLevel() {}
	
	public void testDepth() {}
	
	public void testDelete() {}

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	
	
}
