/**
 * Junit Test for MaxHeap
 * @author urmi
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Explanation of annotations:
// @Test flags a method as a TEST method. 
// @Before indicates that the annotated method will be run BEFORE every
//  test method is run.
// @After indicates that the annotated method will be run AFTER every
//  test method is run.

public class TestMaxHeap
{
	private MaxHeap<Integer> heap;

	/**
     	* SetUp method that executes right before each test methods
     	*/
	@Before
	public void setUp()
	{
		heap = new MaxHeap<Integer>(10); 
	}

	/**
	 * Cleanup method that executes right after each test methods
	 */
	@After

	public void tearDown()
	{
		heap.clear();
	}

	/**
	 * Test method for insert() of heap.
	 * it test if items are pushed into the queue or not by checking its size after push method.
	 * first, it is assured if the queue is empty or not then 1,2,3,4 are pushed into the queue.
	 * after pushing the size of queue should be 4. 
	 */
	@Test
	public void testInsert()
	{
		assertTrue(heap.isEmpty());
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(4);
		assertTrue(heap.size() == 4);
	}
	/**
	 * Test method for remove().
	 * first, it is assured if the heap is empty or not then 1,2,3,4 are inserted into the heap.
	 * then remove method is called 2 times and size of heap is checked after that. it should be 2.
	 * then again remove method is called and size should be 1 and again removed so at last heap should be
	 * empty.
	 */

	@Test
	public void testRemove() 
	{
		assertTrue(heap.isEmpty());
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(4);
		heap.remove();
		heap.remove();
		assertTrue(heap.size()==2);
		heap.remove();
		assertTrue(heap.size()==1);
		heap.remove();
		assertTrue(heap.isEmpty());
	}

	/**
	 * Test method for isEmpty()
	 *first it is checked the heap to be empty then 1 is inserted.
	 * so after inserting isEmpty() should be false. 
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(heap.isEmpty());
		heap.insert(1);
		assertFalse(heap.isEmpty());
	}

	/**
	 * Test method for isFull().
	 * first it is checked the heap to be empty then 1,2,3 are inserted.
	 * so after inserting heap shouldnot be full.
	 */
	@Test
	public void testIsFull()
	{
		assertTrue(heap.isEmpty());
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		assertTrue(!heap.isEmpty());
	}

	/**
	 * Test method for size()
	 * 1, 2, and 3 are inserted and now size of heap should be 3.
	 */
	@Test
	public void testSize() 
	{
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		assertTrue(heap.size()==3);
	}

	/**
	 * Test method for toString.
	 * random  numbers are inserted into the heap and printed using tostring().
	 * so it should print all numbers in arrays in MaxHeap sorted. 
	 */
	@Test
	public void testToString() 
	{
		//Queue<Integer> queue = new Queue<Integer>(10);
		heap.insert(15);
		heap.insert(9);
		heap.insert(18);
		heap.insert(14);
		heap.insert(2);
		heap.insert(11);
		heap.insert(7);
		heap.insert(6);
		heap.insert(3);
		heap.insert(12);
		System.out.println(heap.toString());
		assertTrue(heap.toString().equals("[18, 14, 15, 9, 12, 11, 7, 6, 3, 2]"));
	}
	/**
	* test method for clear()
	* as we are not inserting anything size should be 0.
	*/
	@Test
	public void testClear()
	{
		assertTrue(heap.size()==0);
	}

	/**
	 * testAddToFull method test if items is added to full heap.
	 * 10 elements are inserted in a heap then heap should be full.
	 * 2 is tried to inserted in a heap so, it should throw outofBoudndsException
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddToFull()
	{
		for(int i = 0; i<10; i++)
		{
			heap.insert(i);
		}
		heap.insert(2);
	}

	/**
	 * testRemoveFromEmpty method checks if the item is
	 * remvoed from empty heap or not. 
	 */
	@Test
	public void testRemoveFromEmpty()
	{
		heap.remove();
	}
	
}// end of TestMaxHeap class.

