import java.util.Arrays;
import java.util.Random;
/**
 * Startup class has a heap of type integer.
 * new heap is created of capacity 10.
 * 10 random numbers between 0 to 100 are inserted into the heap and printed in a toString.
 * I have also printed size of heap. 
 * for removal, loop is used to remove elements until heap is empty and are added to array. 
 * array of removed elements is printed.
 */
public class Startup
{
	public static void main(String[] args)
	{
		MaxHeap<Integer> heap = new MaxHeap<Integer>(10);// creating an instance of MAxHeap

		Random random = new Random(); // creating random numbers
		for (int i = 0; i<10; i++)
		{
			heap.insert(random.nextInt(100));
		}
		System.out.println("The Heap Array is " + heap.toString());
		System.out.println("size is "+ heap.size());

		int[] remove1 = new int[10];
		for(int i = 0; i<10 ; i++)
		{
			remove1[i] = heap.remove();
		}
		System.out.println("Removed Array is " + Arrays.toString(remove1));
	}// end of main method

}// end of Starup class

