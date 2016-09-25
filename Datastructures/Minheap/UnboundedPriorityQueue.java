/**
 * @author urmi
 * generic implementation of a UnboundedPriorityQueue.
 * which extended comparable.
 * contains public enqueue method, dequeue method, size method, isEmpty method, isFull method.
 */
public class UnboundedPriorityQueue<T extends Comparable<T>> 
{

	private int size; // size of queue
	private MinHeap<T> heap; // heap 

	/**
 	 * this constructor takes no argument. 
 	 * implementation of size, heap is done.
 	 * helpful for test class.
 	 */
 
	public UnboundedPriorityQueue()
	{	
		size  = 0;
		heap = new MinHeap<T>();
	}

	/**
 	 * this constructor takes integer capacity  as an argument. 
 	 * implementation of heap, capacity is done.
 	 * @param int capacity is size of queue
 	 */
	public UnboundedPriorityQueue(int capacity)
	{
		size = capacity;
		heap = new MinHeap<T>(capacity);
	}

	/**
 	 * equeue method takes T data as a parameter. 
 	 * data are inserted in to the heap of priority queue.
 	 * returns nothing.
 	 * @param T data is the value to be enqueued.
 	 */
	public void enqueue(T data)
	{
		heap.insert(data);
		size++;
	 }

	/**
 	 * dequeue method takes the data from the queue.
 	 * it takes no paramenter but returns the data dequeued from the queue.
 	 * if the queue is empty then outofbound index is thrown.
 	 * otherwise, data is dequeued and returned. 
 	 * @return T root, data dequeued from the queue.
 	 */
	public T dequeue()
	{
	 
		if (isEmpty())
    		{
 			throw new IndexOutOfBoundsException("cannot dequeue");
    		}	
    		T root;
		root = heap.remove();
		size--;
		return root;
	}// end of dequeue method

	/**
 	 * isEmpty method checks if the queue is empty or not.
 	 * returns true if heap is empty.
 	 * @return boolean heap to be null.
 	 */
	public boolean isEmpty()
	{
		return heap.isEmpty();
	}// end of isEmpty method

	/*
 	 * toString() method reperesents the priorityqueue in string form.
 	 * return a string representing the content of the internal array
 	 * used inside priorityqueue.
 	 * @return string data, array contents of a priorityqueue.
 	 */
	public String toString()
	{
		return heap.toString();
	}// end of toString method

	/**
 	 *clear() method clears the queue and makes it empty. 
 	 * size is set to 0.
 	 * helpful method for testclass. 
 	 */
	public void clear()
	{
		size = 0;
	}// end of clear method

	/**
 	 * size method returns the size of a queue.
 	 * @return int size, size of a queue. 
	 */
	public int size()
	{
		return this.size;
	}

}// end of Class 

