import java.util.Arrays;

/**
 * generic implementation of a MinHeap.
 * which extended comparable.
 * contains public insert method, remove method, size method, isEmpty method, isFull method
 * and private shiftDown and shiftUp method.
 */
public class MinHeap<T extends Comparable<T>> 
{
	private T[] data; // generic array
	private int capacity; //  size of an array
	private int position; // current number of elements 
 	
	
	/**
	 * this constructor donot take any argument. 
	 * implementation of array, capacity and position is done.
	 * helpful for test class.
	 */
	
	public MinHeap()
	{
		capacity = 10;
		data = (T[]) new Comparable[capacity];
		position = -1;
	}

	/**
	 * this constructor takes integer capacity  as an argument. 
	 * implementation of array, capacity and position is done.
	 * @param int capacity is size of an array.
	 */
	@SuppressWarnings("unchecked")
	public MinHeap(int capacity)
	{
		data = (T[]) new Comparable[capacity];
		position = -1;
	}
	
	/**
	 * insert method insert the value in a minheap.
	 * after the insertion of value siftUp method is called which is implemented as private method.
	 * @param T value is the item to be inserted in a minheap.
	 */
	public void insert(T value) 
	{
        
		data[position++ +1] = value;
	        siftUp();
	}

	/**
	 * resize method helps to increase the size of an heap when necessary.
	 * capacity of heap is multiplied and then added by 1 to resize. 
	 * this method helps to grow the capacity of heap. 
	 */
	 
	public void reSize()
	{
		T[] temp = (T[]) new Comparable[data.length];
		for (int i = 0; i <= data.length;i++)
			temp[i] = data[i];

		capacity = capacity*2+1;

		data = (T[]) new Comparable[capacity];
		for (int i = 0; i <= data.length;i++)
			data[i]= temp[i];
	}
	/**
	 * remove method remove the element in a maxheap.
	 * after the removal of element siftDown method is called which is implemented as private method.
	 * if there is only one element in a heap then, root is removed and returned.
	 * if the heap is empty then, it thows out of bound exception.
	 * @return T root which is the minimum value from the root of the heap. 
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
	 * isFull method check if the heap is full or not.
	 * returns true if position == capacity.
	 * @return boolean position of heap to be equal to its capacity.
	 */
	public boolean isFull()
	{
		return position == capacity;
	}
	
	/**
	 * toString() method reperesents the minheap in string form.
	 * return a string representing the content of the internal array
	 * used inside MinHeap.
	 * @return string data, array contents of a minHeap.
	 */
	public String toString() 
	{
		return Arrays.toString(data);
	}

	/**
	*clear() method clears the minHeap and makes it empty. 
	* root is set to null.
	* helpful method for testclass. 
	*/
	public void clear()
	{
		data = null;
	}

	/**
	* siftUp() method computes internal function for inserting element. 
	* if minHeap is full then resize method is called. 
	* after the item is inserted, it is compared with parent.
	* if the item inserted is lower than parent then they are swaped.
	* if not then we are done. 
	*/
	private void siftUp() 
	{
		if(position == capacity)
		{
			
			reSize();
			
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
	           
	           	if (parent.compareTo(item) > 0)
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
	    	int minChild;
	    	T top = data[index];      

	    	while(index < position/2)     
	        {                             
			int leftChild = 2*index+1;
	         	int rightChild = leftChild+1;
	                                        
	         	if(leftChild !=index1 && data[rightChild].compareTo(data[leftChild]) < 0)
	         		minChild = rightChild;
	         	  
	         	else
	            		minChild = leftChild;
	         		         
	         	
	         	if( top.compareTo(data[minChild] )<0)
	            		
	         	
	         		break;

	                  // shift child up
	         	data[index] = data[minChild];
	         	index = minChild; // go down
	         }
	           data[index] = top; 
	}// end of private class siftDown 

}//end of main clas minheap
