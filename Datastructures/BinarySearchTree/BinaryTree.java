import java.util.List;
import java.util.ArrayList;

public class BinaryTree<T>{
	
	
	protected BinaryNode<T> root = null;	
	
	private T nullSymbol = null;

	/**
	 * Default constructor
	 */
	public BinaryTree(){

	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence.
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 */
	public BinaryTree(T[] seq){
		initFromBfsSequence(seq);
	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *	A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *	During construction of the tree, when such a special symbol is encountered, 
	 *	that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 * 	@param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 * 	@param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	public BinaryTree(T[] seq, T nullSymbol){
		this.nullSymbol = nullSymbol;
		initFromBfsSequence(seq);
	}

	private void initFromBfsSequence(T[] seq){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
				
		List<BinaryNode<T>> nodes 
						= new ArrayList<BinaryNode<T>>(seq.length);
		this.root = new BinaryNode<T>(seq[0]);
		nodes.add(root);

		for(int i = 0; i < seq.length; i++){			
			if(nodes.get(i) == null){ 				
				handelNullParentNode(nodes, i, seq.length);				
			}else{				
				handleNonNullParentNode(nodes, i, seq);				
			}
		}		
	}

	private void handelNullParentNode(List<BinaryNode<T>> nodes, 
								int nullNodeIndex, int seqLength){
		int leftIndex = (nullNodeIndex * 2) + 1;
				
		if(leftIndex < seqLength){
			nodes.add(null);

			int rightIndex = (nullNodeIndex * 2) + 2;
			if(rightIndex < seqLength){
				nodes.add(null);
			}
		}
	}

	private void handleNonNullParentNode(List<BinaryNode<T>> nodes, 
								int parentIndex, T[] seq){
		int leftIndex = (parentIndex * 2) + 1;			
		if(leftIndex < seq.length){
			BinaryNode<T> leftNode = null;
			if(!seq[leftIndex].equals(nullSymbol)){
				leftNode = new BinaryNode<T>(seq[leftIndex]);
			}
			nodes.get(parentIndex).leftNode = leftNode;
			nodes.add(leftNode);

			int rightIndex = (parentIndex * 2) + 2;				
			if(rightIndex < seq.length){
				BinaryNode<T> rightNode = null;
				if(!seq[rightIndex].equals(nullSymbol)){
					rightNode = new BinaryNode<T>(seq[rightIndex]);
				}
				nodes.get(parentIndex).rightNode = rightNode;
				nodes.add(rightNode);			
			}
		}
	}

	public int height(){
		if (root == null) return 0;	
		return root.height();
	}
	/**
	* width() method returns the width of the binary tree. 
	* width() method is implemented in BinaryNode class.
	* @return integer width of a tree.
	*/
	public int width(){
		
		return root.width();
	}
	/**
	*breadthFirstTravese() returns the string that represents the breadthFirstTravesal
	* sequence of the binary tree.
	* breadthFirstTraverse() method is implemented in BinaryNode class.
	* @return string sequence of breadthFirstTraverse
	*/

	public String breadthFirstTraverse(){
		return root.breadthFirstTraverse();
	}

	public String preOrderTraverse(){
		return root.preOrderTraverse().trim();				
	}

	public String postOrderTraverse(){
		return root.postOrderTraverse().trim();
	}

	public String inOrderTraverse(){
		return root.inOrderTraverse().trim();
	}
	
	class BinaryNode<T>{
		private T data = null;
		private BinaryNode<T> leftNode = null;
		private BinaryNode<T> rightNode = null;

		public BinaryNode(T data){
			this.data = data;			
		}

		public String toString(){
			return "" + data;
		}

		public BinaryNode<T> getLeftNode(){
			return this.leftNode;
		}

		public BinaryNode<T> getRightNode(){
			return this.rightNode;
		}

		public void setLeftNode(BinaryNode<T> node){
			this.leftNode = node;
		}

		public void setRightNode(BinaryNode<T> node){
			this.rightNode = node;
		}

		public T getData(){
			return this.data;
		}

		public void setData(T data){
			this.data = data;
		}
		
		public int height(){
			if(isLeaf()) return 0;
			
			int leftHeight = 0;
			int rightHeight = 0;

			if(leftNode != null){ 
				leftHeight = leftNode.height();
			}

			if(rightNode != null){
				rightHeight = rightNode.height();
			}
			
			int maxHeight = leftHeight > rightHeight? leftHeight: rightHeight;

			return maxHeight + 1 ;
		}

		public boolean isLeaf(){
			return (leftNode == null && rightNode == null);
		}


		public String preOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			
			stringBuffer.append(" " + data);
			
			if(leftNode != null){
				stringBuffer.append(leftNode.preOrderTraverse());				
			}
			
			if(rightNode != null){
				stringBuffer.append(rightNode.preOrderTraverse());
			}

			return stringBuffer.toString();				
		}

		public String postOrderTraverse(){			
			StringBuilder stringBuffer = new StringBuilder();
			
			if(leftNode != null){
				stringBuffer.append(leftNode.postOrderTraverse());
			}

			if(rightNode != null){
				stringBuffer.append(rightNode.postOrderTraverse());
			}

			stringBuffer.append(" " + data);			

			return stringBuffer.toString();	
		}
		/**
		* width() method computes the width of the binary tree. 
		* Queue is used in order to compute the width. 
		* datas are pushed and poped and level of the tree is considered.
		* if the level of the tree is higher than maxwidth then maxwidth will level.
		* and while the level is greater than 0 nodes are pushed in rightnode and levelnode. 
		* @return integer maxwidth of a tree.
		*/
		public int width(){
			int maxWidth = 0;// maxwidth is set up as 0.
			if(isLeaf()){
				return 1;
			}
			if (this.data.equals(null)) 
				{return 0;}
			else
			{
				Queue<BinaryNode<T>> queue = new Queue<BinaryNode<T>>();
				int level = 0; // level is set up as 0.
				
				queue.push(this);
				//while the queue is not empty level is calculated as size and compared with 
				// maxwidth. if the level is more than maxwidth than new maxwdith will be equal
				// to current level. 
				 while(!queue.isEmpty()){
			            level = queue.size();
			            if(level>maxWidth){
			                maxWidth = level;
			            }
			            // now as level is more than 0, nodes are pushed as leftNOde and rightNode.
			            while(level>0){
			                BinaryNode<T> node = (BinaryNode<T>)queue.pop();
			                if(node.leftNode!=null) queue.push(node.leftNode);
			                if(node.rightNode!=null) 
			                	queue.push(node.rightNode);
			                level--; // updated level
			            }
			        }
			}
			return maxWidth;
		}

		public String inOrderTraverse(){	
			// The following null pointer checking was missed out
			// while coding in class, and thus we got null-pointer exceptions 
			// See, a common source of error!

			StringBuilder stringBuffer = new StringBuilder();			

			if(leftNode != null){ 
				stringBuffer.append(leftNode.inOrderTraverse());
			}

			stringBuffer.append(" " + data);			

			if(rightNode != null){
				stringBuffer.append(rightNode.inOrderTraverse());
			}

			return stringBuffer.toString();		
		}
		/**
		* breadthFirstTraverse method traverse the tree in breadth-first order.
		* Stringbuilder is used for string representation of the sequence. 
		* queue is used in order to traverse a tree. 
		* node is pushed into the queue and while queue is not empty,node is poped and printed.
		* then nodes are pushed into lefetnode if leftnode is not empty and to rightnode if 
		* right node is not empty.
		* @return string of breadthFirstTraverse. 
		*/
		public String breadthFirstTraverse()
		{
			
			
				StringBuilder string = new StringBuilder();
				Queue<BinaryNode<T>> queue = new Queue<BinaryNode<T>>();
				if (this == null){
					return "0";}
				queue.clear();
				queue.push(this);
				while (!queue.isEmpty()){
					BinaryNode<T> node = queue.pop();
					string.append(node.data + " ");
					if (node.leftNode != null){
						queue.push(node.leftNode);
					}
					
					if (node.rightNode != null){
						queue.push(node.rightNode);
					}
					
				}
				
				string.setLength(string.length()-1);
				return string.toString();
		}
	}
}// end of BinaryTree class
