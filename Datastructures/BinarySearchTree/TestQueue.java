/**
 * Junit Test for Queue
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

public class TestQueue<T>
{
	private Queue<Integer> queue;

	/**
     	* SetUp method that executes right before each test methods
     	*/
	@Before
	public void setUp()
	{
		queue = new Queue<Integer>(); 
	}

	/**
	 * Cleanup method that executes right after each test methods
	 */
	@After

	public void tearDown()
	{
		queue.clear();
	}

	/**
	 * Test method for push() of queue.
	 * it test if items are pushed into the queue or not by checking its size after push method.
	 * first, it is assured if the queue is empty or not then 1,2,3,4 are pushed into the queue.
	 * after pushing the size of queue should be 4. 
	 */
	@Test
	public void testPush()
	{
		assertTrue(queue.isEmpty());
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		assertTrue(queue.size() == 4);
	}
	/**
	 * Test method for pop().
	 * first, it is assured if the queue is empty or not then 1,2,3,4 are pushed into the queue.
	 * then pop method is called 2 times and size of queue is checked after that. it should be 2.
	 * then again pop method is called and size should be 1 and again poped so at last queue should be
	 * empty.
	 */
	@Test
	public void testPop() {
		assertTrue(queue.isEmpty());
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.pop();
		queue.pop();
		assertTrue(queue.size()==2);
		queue.pop();
		assertTrue(queue.size()==1);
		queue.pop();
		assertTrue(queue.isEmpty());
	}
	/**
	 * Test method for isEmpty()
	 *first it is checked the queue to be empty then 1 is pushed.
	 * so after pushing isEmpty() should be false. 
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(queue.isEmpty());
		queue.push(1);
		assertFalse(queue.isEmpty());
	}

	/**
	 * Test method for isFull().
	 * first it is checked the queue to be empty then 1,2,3 are pushed.
	 * so after pushing queue shouldnot be full.
	 */
	@Test
	public void testIsFull()
	{
		assertTrue(queue.isEmpty());
		queue.push(1);
		queue.push(2);
		queue.push(3);
		assertTrue(!queue.isFull());
	}

	/**
	 * Test method for reSize()
	 * first it is checked the queue to be empty then 1,2,3 to 10 is pushed.
	 * now, queue should be full. after that again 11 and 12 is pushed.
	 * if the resize works then size should noew grow and become 12.
	 */
	@Test
	public void testReSize() 
	{
		assertTrue(queue.isEmpty());
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.push(5);
		queue.push(6);
		queue.push(7);
		queue.push(8);
		queue.push(9);
		queue.push(10);
		assertTrue(queue.isFull());
		queue.push(11);
		queue.push(12);
		assertTrue(queue.size()==12);
	}

	/**
	 * Test method for size()
	 * 1, 2, and 3 are pushed and now size of queue should be 3.
	 */
	@Test
	public void testSize() {
		queue.push(1);
		queue.push(2);
		queue.push(3);
		assertTrue(queue.size()==3);
	}

	/**
	 * Test method for toString.
	 * 1 to 10 numbers are pushed into the queue and printed.
	 * so it should print all numbers in arrays. 
	 */
	@Test
	public void testToString() 
	{
		Queue<Integer> queue = new Queue<Integer>(10);
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.push(5);
		queue.push(6);
		queue.push(7);
		queue.push(8);
		queue.push(9);
		queue.push(10);
		//System.out.println(queue.toString());
		assertTrue(queue.toString().equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"));
	}
	/**
	* test method for clear()
	* as we are not pushing anything size should be 0.
	*/

	@Test
	public void testClear()
	{
		assertTrue(queue.size()==0);
	}
}// end of TestQueue class.

