/**
 * Junit Test for UnboundedPriorityQueue
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

public class TestUnboundedPriorityQueue
{
	private UnboundedPriorityQueue<Integer> priority;

	/**
     	* SetUp method that executes right before each test methods
     	*/
	@Before
	public void setUp()
	{
		priority = new UnboundedPriorityQueue<Integer>(); 
	}

	/**
	 * Cleanup method that executes right after each test methods
	 */
	@After

	public void tearDown()
	{
		priority.clear();
	}

	/**
	 * Test method for enqueue method.
	 * it test if items are pushed into the queue or not by checking its size after enqueing method.
	 * first, it is assured if the priority queue is empty or not then 1,3,8,6 are enqueued into the queue.
	 * after pushing the size of priority queue should be 4. 
	 */
	@Test
	public void testEnqueue()
	{
		assertTrue(priority.isEmpty());
		priority.enqueue(1);
		priority.enqueue(3);
		priority.enqueue(8);
		priority.enqueue(6);
		assertTrue(!priority.isEmpty());
		assertTrue(priority.size() == 4);

	}
	/**
	 * Test method for dequeue.
	 * first, it is assured if the priority queue is empty or not then 1,3,6,4 are enqueued into the queue.
	 * then dequeue method is called 2 times and size of queue is checked after that. it should be 2.
	 * then again dequeue method is called and size should be 1 and again removed so at last queue should be
	 * empty.
	 */

	@Test
	public void testDequeue() 
	{
		assertTrue(priority.isEmpty());
		priority.enqueue(1);
		priority.enqueue(3);
		priority.enqueue(6);
		priority.enqueue(4);
		priority.dequeue();
		priority.dequeue();
		assertTrue(priority.size()==2);
		priority.dequeue();
		assertTrue(priority.size()==1);
		priority.dequeue();
		assertTrue(priority.isEmpty());
	}

	/**
	 * Test method for isEmpty()
	 *first it is checked the priority queue to be empty then 1 is enqueued.
	 * so after that isEmpty() should be false. 
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(priority.isEmpty());
		priority.enqueue(1);
		assertFalse(priority.isEmpty());
	}

	/**
	 * Test method for isFull().
	 * first it is checked the priority queue to be empty then 1,2,3 are enqueued.
	 * so after that priority queue shouldnot be full.
	 */
	@Test
	public void testIsFull()
	{
		assertTrue(priority.isEmpty());
		priority.enqueue(1);
		priority.enqueue(2);
		priority.enqueue(3);
		assertTrue(!priority.isEmpty());
	}
	

	/**
	 * Test method for size()
	 * 1, 2, and 3 are enqueued and now size of priority queue should be 3.
	 */
	@Test
	public void testSize() 
	{
		priority.enqueue(1);
		priority.enqueue(2);
		priority.enqueue(3);
		assertTrue(priority.size()==3);
	}

	/**
	 * Test method for toString.
	 * random  numbers are inserted into the priority and printed using tostring().
	 * so it should print all numbers in arrays in priority sorted. 
	 */
	@Test
	public void testToString() 
	{
		priority.enqueue(15);
		priority.enqueue(9);
		priority.enqueue(18);
		priority.enqueue(14);
		priority.enqueue(2);
		priority.enqueue(11);
		priority.enqueue(7);
		priority.enqueue(16);
		priority.enqueue(13);
		priority.enqueue(12);
		System.out.println(priority.toString());
		assertTrue(priority.toString().equals("[2, 9, 7, 13, 12, 18, 11, 16, 15, 14]"));
	}
	/**
	* test method for clear()
	* as we are not enqueuing anything size should be 0.
	*/
	@Test
	public void testClear()
	{
		assertTrue(priority.size()==0);
	}

	/**
	 * expected indexoutofbound as we are trying to dequeue from empty.
	 */

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDequeueFromEmpty()
	{
		priority.dequeue();
	}
	
	
}// end of TestMaxHeap class.

