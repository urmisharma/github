/**
*@author urmila sharma
* Queue for Binary Tree
*/
import java.util.Arrays;
/**
*Queue class is generic. 
*push, pop, size, clear, isEmpty, isFull,toString methods are implemented in this class.
* Queue size can grow when needed.
* Arrays is used to implement the queue.
*/
public class Queue<T>
{
		private T[] data;
		private int front;
		private int back;
		private int capacity;
		private int currentSize;
		
		/**
		* this is a no argument constructor. it
		* capacity of Queue is initialized as 10.
		* front, back are given a intial value as -1.
		*/
		public Queue()
		{
			capacity = 10;
			data = (T[]) new Object[capacity];
			front = -1;
			back = -1;
			currentSize = 0;
		}
		/*
		* this ocnstructor takes integer capc as an argument.
		* helpful for testing.
		* @param  integer capc is the capacity of the Queue.
		*/
		public Queue(int capc)
		{
			capacity = capc;
			data = (T[]) new Object[capacity];
			front = -1;
			back = -1;
			currentSize = 0;
		}
		/*
		*push method pushed the item into the Queue. 
		* isFull method is called to check if the queue is full or not. 
		* if the Queue is full then reSize method is called which grow the size.
		* item is pushed into the queue in the front of the queue. 
		* currentSize is updated. 
		* @ param T item is the item to be pushed into the queue. 
		* @ return nothing 
		*/
		public void push(T item)
		{
			if(isFull())
				{reSize();}
			data[++front] = item;
			currentSize++;
		}
		/**
		* pop method helps to pop the item fron the queue
		* currentSize is checked if it is 0 then IndexOutOfBoundsException is thrown.
		* currentSize is updated and then item is poped. 
		* @ return data which is poped from the queue. 
		*/
		public T pop()
		{
			if(currentSize == 0)
			{
			
				throw new IndexOutOfBoundsException("Cannot dequeue from empty queue");	
			}
			else 
			currentSize--;
			return data[++back];
		}
		/**
		* isEmpty() method checks if the queue is empty or not.
		* currentSize is used to check the empty queue. 
		* @return true if the currentSize is 0 . 
		*
		*/
		public boolean isEmpty()
		{
			return currentSize == 0;
		}
		/**
		* isFull() method checks if the queue is full or not.
		* @return true if the currentSize is equal to data.length. 
		*/
		
		public boolean isFull()
		{
			return currentSize== data.length;
		}
		/**
		* reSize() method helps to reSize the size of the queue when it is full.
		* capacity of queue is multiplied and then added by 1 to resize. 
		* this method helps to grow the capacity of queue. 
		*/
		public void reSize()
		{
			T[] temp = (T[]) new Object[data.length];
			for (int i = 0; i <= front;i++)
				temp[i] = data[i];

			capacity = capacity*2+1;

			data = (T[]) new Object[capacity];
			for (int i = 0; i <= front;i++)
				data[i]= temp[i];
		}
		/**
		* size() method calculate the size of the queue.
		* @return integer currentSize of queue.
		*/
		public int size()
		{
			return this.currentSize;
		}
		/**
		*clear() method clears the queue and makes it empty. 
		* currentSize is set to 0 . 
		*/
		public void clear()
		{
			currentSize = 0;
		}
		/**
		* toString() method reperesents the queue in string form.
		* @return array of data of the queue.
		*/
		public String toString() 
		{	
			T[] array1 = (T[])new Object[data.length];
			for (int i = 0; i < data.length; i++){
				array1[i] = data[i];

			}
			return Arrays.toString(array1);
		}

}// end of Queue class
