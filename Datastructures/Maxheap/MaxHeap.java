/**
 * HW04
 * MaxHeap class
 * @author urmi
 */
import java.util.Arrays;

/**
 * generic implementation of a MaxHeap.
 * which extended comparable.
 * contains public insert method, remove method, size method, isEmpty method, isFull method
 * and private shiftDown and shiftUp method.
 */
public class MaxHeap<T extends Comparable<T>> 
{
	private T[] data; // generic array
	private int capacity; // fixed size of an array
	private int position; // current number of elements 
	
	/**
	 * this constructor takes integer capacity  as an argument. 
	 * implementation of array, capacity and position is done.
	 * @param int capacity is size of an array.
	 */
	@SuppressWarnings("unchecked")
	public MaxHeap(int capacity)
	{
		this.capacity = capacity;
		data = (T[]) new Comparable[capacity];
		position = -1;
	}
	/**
	 * insert method insert the value in a maxheap.
	 * after the insertion of value siftUp method is called which is implemented as private method.
	 * @param T value is the item to be inserted in a maxheap.
	 */
	public void insert(T value) 
	{
		data[position++ +1] = value;
	        siftUp();
	}
	/**
	 * remove method remove the element in a maxheap.
	 * after the removal of element siftDown method is called which is implemented as private method.
	 * if there is only one element in a heap then, root is removed and returned.
	 * if the heap is empty then, it thows out of bound exception.
	 * @return T root which is the maximum value from the root of the heap. 
	 */
	public T remove()  
	{
		if (data[1] == null) 
		{
	    	   T temp = data[0]; //root is saved as temp
	    	   data[0] = null;  // root is removed 
	    	   return temp;	    // saved temp root is returned
		}
	       	if (position == 0)
	       	{
	    		throw new IndexOutOfBoundsException("Empty Heap");
	    	}	

	    	T root = data[0];
	        data[0] = data[position];
	        data[position--] = null;
	        siftDown(0);
	        return root;
	}
	/**
	 * size method returns the size of a heap.
	 * @return int current position + 1, size of a heap. 
	 */
	public int size()
	{
		return position+1;
	}
	/**
	 * isEmpty method checks if the heap is empty or not.
	 * returns true if root is null.
	 * @return boolean root of the heap to be null.
	 */
	public boolean isEmpty() 
	{
		return data[0] == null;
	}
	
	/**
	 * toString() method reperesents the maxheap in string form.
	 * return a string representing the content of the internal array
	 * used inside MaxHeap.
	 * @return string data, array contents of a maxHeap.
	 */
	public String toString() 
	{
		return Arrays.toString(data);
	}

	/**
	*clear() method clears the maxHeap and makes it empty. 
	* root is set to null.
	* helpful method for testclass. 
	*/
	public void clear()
	{
		data = null;
	}

	/**
	* siftUp() method computes internal function for inserting element. 
	* if maxHeap is full then INdexOutOfBoundException is thrown. 
	* after the item is inserted, it is compared with parent.
	* if the item inserted is higher than parent then they are swaped.
	* if not then we are done. 
	*/
	private void siftUp() 
	{
		if(position == capacity)
		{
			throw new IndexOutOfBoundsException("Cannot insert to Full heap");
		}
	        int index = position;

	        while (index >= 0) 
	        {
	        	int par = (index-1)/2; // parent
	           	T item = data[index];
	           	T parent = data[par]; 

	          	if (parent == null)
	          	{
	        	   break;
	           	}
	           
	           	if (item.compareTo(parent) > 0)
	           	{
	                	// swap between item and parent
	                	data[par] = item; 
	            		data[index] = parent;
	            		// move up one level
	                	index = par;
	            	} 
	            	else 
	            	{
	                	break;
	            	}
	        }// end of while loop
	}// end of siftUp method.

	/**
	 * Helpful method for remove method. It is private as we donot want to access it directly.
	 * removal is done from the root. After removal of element is done then  
	 *
	 *@param integer index
	 */
   	private void siftDown(int index)
	{
		int index1 = position;
	    	int maxChild;
	    	T top = data[index];      

	    	while(index < position/2)      
	        {                             
			int leftChild = 2*index+1;
	         	int rightChild = leftChild+1;
	                                        
	         	if(rightChild < index1 && data[leftChild].compareTo(data[rightChild]) < 0)
	        		maxChild = rightChild;
	         	else
	            		maxChild = leftChild;
	                                        
	         	if( top.compareTo(data[maxChild] )>0)
	            		break;

	                  // shift child up
	         	data[index] = data[maxChild];
	         	index = maxChild; // go down
	         }
	           data[index] = top; 
	}// end of private class siftDown 

}//end of main clas maxheap
