/**
 * BinarySearchTree class
 * @author urmi
 */

/**
 *generic implementation of a Binary Search Tree.
 *extended from the provided Binary Tree class.
 *contains boolean contains method, remove method, insert method. 
 */
public class BinarySearchTree<T extends Comparable<T>>extends BinaryTree<T>
{
	 // private T[] sequence;
	 // private T nullSymbol;

	/**
	 *no argument constructor. 
	 * super() is called.
	 */
	 public BinarySearchTree()
	 {
		super();
	 }
	/**
	 * this constructor takes t[] seq as an argument. 
	 * implementation of sequence and inserting is done here.
	 * @param T[] seq is the array containing sequence of the nodes of the tree.
	 * super() is called.
	 */
	 public BinarySearchTree(T[] seq)
	 {
		super(seq);
		// this.sequence = seq;
		// for(int i = 0; i<seq.length;i++)
		// {
		// 	insert(seq[i]);
		// }

	 }
	/**
	 *this constructor is useful for test. 
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *  A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *  During construction of the tree, when such a special symbol is encountered, 
	 *  that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 *  @param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	
	 public BinarySearchTree(T[] seq, T nullSymbol)
	 {
		super(seq, nullSymbol);
		// this.sequence = seq;
		// this.nullSymbol = nullSymbol;
		// for(int i = 0; i<seq.length;i++)
		// {
		// 	insert(seq[i]);
		// }
	 }
	/**
	 * insert method insert the value in the binarysearch tree.
	 * insertion is done recursively.
	 * if the item to be inserted is not nullSumbol then it is inserted. 
	 * @param T value is the item to be inserted i nthe binary search tree.
	 */
	public void insert(T value)
    	{
        	// if(value != nullSymbol)
        	// {
            	root = insert(value, root);
        	//}
         }
	/**
	 * this insert method is the internal method to insert into subtree.
	 * it is private and takes arguments.
	 * recursive method is implemented here. if value to be inserted is less
	 * than 0 then it is inserted in leftNode, if it is more than 0 then inserted in rightNode.
	 * @param value is the item to insert.
	 * @param node is the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
    	private BinaryNode<T> insert(T value, BinaryNode<T> bNode)
    	{
        	if(bNode == null)
        	{
            	return new BinaryNode<T>(value);
        	}
 
        	int compareValue = value.compareTo(bNode.getData());
 
        	if(compareValue < 0)
        	{
            	bNode.setLeftNode(insert(value, bNode.getLeftNode()));
        	}
        	else if(compareValue > 0)
        	{
            	bNode.setRightNode(insert(value, bNode.getRightNode()));
        	}
        	else
        	{
 			// Duplicate then do nothing. 
        	}
 
        	return bNode;
 
    	}// end of internal method of insert.

 	/**
 	 * remove the item from the tree. 
 	 * @param T value is the item to be removed. 
 	 */
    	public void remove(T value)
    	{
        	root = remove(value, root); 
    	}
 	/**
 	 *internal method fot removing a item from a subtree.
 	 * @param T value is the item to be removed.
 	 * @param node is the node that roots the subtree.
 	 * @return node, new root of the subtree.
 	 */
    	private BinaryNode<T> remove(T value, BinaryNode<T> bNode)
    	{
        	if(bNode ==null)
        	{
            	return bNode;// nothing to remvoe
        	}
 
        	int compareValue = value.compareTo(bNode.getData());
 
        	if(compareValue < 0)
        	{
            	bNode.setLeftNode(remove(value, bNode.getLeftNode()));
        	}
 
        	else if(compareValue > 0)
        	{
            	bNode.setRightNode(remove(value, bNode.getRightNode()));
        	}
 
        	else if(bNode.getLeftNode() != null && bNode.getRightNode() != null)
        	{
            	bNode.setData(findMin(bNode.getRightNode()).getData());
            	bNode.setRightNode(remove(bNode.getData(), bNode.getRightNode()));
        	}
 
        	else
        	{
            	if(root != bNode)
            	{
                	bNode = bNode.getRightNode(); 
            	}
             	else
            	{
                	bNode = bNode.getLeftNode();
            	}
        	}

        	return bNode;
    	}//end of internal method of remove

 	/**
 	 * internal method for finding the minimum(smallest element in the subtree.
 	 * recursive implementation of findMin.
 	 * @param node is the node that roots the subtree.
 	 * @return node containing the smallest element.
 	 */
    	private BinaryNode<T> findMin(BinaryNode<T> bNode)
    	{
        	if(bNode == null)
        	{
            	return null;
        	}
 
        	else if(bNode.getLeftNode() == null)
        	{
            	return bNode;
        	}
 
        	return findMin(bNode.getLeftNode());
    	}// end of findMin class

	/**
	 * method to find an item in a subtree
	 * @param T value is the item to find in a subtree.
	 * @return the value.
	 */
    	public boolean contains(T value)
    	{
        	return contains(value, root);
    	}

	/**
	 * internal method for finding the item from a subtree.
	 * @param T value is the item to find.
	 * @param node is the node that roots the subtree.
	 * @return true if the item is found, otherwise false. 
	 */
    	private boolean contains(T value, BinaryNode<T> bNode)
    	{
        	if(bNode == null)
        	{
            	return false;
        	}
 
        	int compareValue = value.compareTo(bNode.getData());
 
        	if(compareValue < 0)
        	{
            	return contains(value, bNode.getLeftNode());
        	}
 
        	else if(compareValue >0)
        	{
            	return contains(value, bNode.getRightNode());
        	}
 
        	return true;
 	}// end of internal method of contains

} // end of Binary Search Tree class
