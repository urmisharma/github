/*
 * JUnit test for HW02
 * Tests one-way linked list
 * Minhaz F. Zibran
*/ 
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import org.junit.Test; 
import org.junit.Before;
import org.junit.After;

// Explanation of annotations:
// @Test flags a method as a TEST method. 
// @Before indicates that the annotated method will be run BEFORE every
//  test method is run.
// @After indicates that the annotated method will be run AFTER every
//  test method is run.

public class TestSinglyLinkedList{
    private SinglyLinkedList<Integer> list;

    /**
     * SetUp method that executes right before each test methods
     */
    @Before
    
    public void setUp(){
        list = new SinglyLinkedList<Integer>();        
    }
    
    /**
     * Cleanup method that executes right after each test methods
     */
    @After
    public void tearDown(){
	list.clear();
    }

    /**
     * Helper method to add elements to the list
     * @param howMany is the number of elements to be added
     */
    private void addElementsToList(int howMany){
	// adds howMany...1 to the list
        for(int i = howMany; i > 0; i--){
            list.add(i);            
        }
    }
    
    /**
     * Test addition of elements to the list
     */
    @Test
    public void testAddToEmptyList(){
	assertTrue(list.isEmpty());
	
	addElementsToList(9);
	assertTrue(list.size() == 9);
	
	Iterator<Integer> it = list.iterator();
	Integer listElement;
	for(int i = 9; i > 0; i--){
	    listElement = it.next();
            assertEquals(i, listElement.intValue()); 
        }
    }
    
    /**
     * Test that insertAt() on an empty list
     * raises an IndexOutOfBoundsException
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testInsertAtEmptyList(){
	assertTrue(list.isEmpty());
	list.insertAt(1, 0); // should raise exception		
    }
    
    
    /**
     * Test that insertAt() on with an invalid index 
     * raises an IndexOutOfBoundsException
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testInsertAtInvalidIndex(){
	assertTrue(list.isEmpty());
	addElementsToList(5);
	assertTrue(list.size() == 5);	
	
	list.insertAt(6, 6);// should raise exception
    }
   
    /**
     * Test that insertAt() on with a valid index 
     * inserts the item in the given index
     */
    @Test
    public void testInsertAtValidIndex(){
	assertTrue(list.isEmpty());
	list.add(1);
	list.add(2);
	list.add(3);
	list.add(4);
	assertTrue(list.size() == 4);
	
	list.insertAt(3, 2);
	assertTrue(list.size() == 5);
	assertTrue(3 == list.getNthFromFirst(2));
    }
   
    /**
     * Test that size tracking works fine
     */
    @Test
    public void testSize(){
	assertTrue(list.isEmpty());
	addElementsToList(5);
	assertTrue(list.size() == 5);
	list.remove(3);
	assertTrue(list.size() == 4);
    }
    
    /**
     * Test if iterator works fine
     */
//fail delete
    @Test
    public void testIteration(){
	assertTrue(list.isEmpty());
	assertFalse(list.iterator().hasNext());
	
	list.add(2);
	assertTrue(list.iterator().hasNext());
	
	list.remove(2);
	assertFalse(list.iterator().hasNext());
    }
    
     /**
     * Test the getNthFromFirst method works correctly with valid argument. Given the numbers
     * that the list holds, counting from zero, the 3rd from first should be 2.
     * Example: For a list: 5 4 3 2 1
     * the 0th from first element is 5
     * the 1st from first element is 4 
     * the 2nd from first is 3
     * etc...
     */
    @Test
    public void testGetNthFromFirst(){
	assertTrue(list.isEmpty());
	addElementsToList(15);	
	assertTrue(list.size() == 15);	
	assertTrue(list.getNthFromFirst(5) == 10);
    }
    
     /**
     * Test thaht the getNthFromFirst method raises IndexOutOfBoundsException
     * when called passing an index out of list's range. Given the numbers
     * that the list holds, counting from zero, the 3rd from first should be 2.
     * Example: For a list: 5 4 3 2 1
     * the 0th from first element is 5
     * the 1st from first element is 4 
     * the 2nd from first is 3
     * etc...
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetNthFromFirstWithNOutOfLimit(){
	assertTrue(list.isEmpty());
	addElementsToList(5);	
	assertTrue(list.size() == 5);	
	list.getNthFromFirst(6); // should cause exception
    }
    
    /**
     * Test that remove() method discards one item from list
     */
    @Test
    
    public void testRemove(){
	assertTrue(list.isEmpty());
	addElementsToList(9);
	assertTrue(list.size() == 9);
	list.remove(9);
	assertTrue(list.size() == 8);
    }
  
    
    /**
     * Test that remove() method deletes the
     * first occurance of an item from list
     */

//fail
    @Test
    public void testRemoveShouldDeleteFirst(){
    	assertTrue(list.isEmpty());
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(2);
    	
    	assertTrue(list.size() == 4);
    	list.remove(2);
    	assertTrue(list.size() == 3);
    		
    	// confirm that current list has 1, 3, 2
    	assertTrue(1 == list.getNthFromFirst(0));
    	assertTrue(3 == list.getNthFromFirst(1));
    	assertTrue(2 == list.getNthFromFirst(2));
	
    }
    
    
    /**
     * Test that remove() method raises NoSuchElementException when
     * attampted to remove a non-existent item
     */
//fail
    @Test(expected=NoSuchElementException.class)
    public void testRemoveNonExistentItem(){
	assertTrue(list.isEmpty());
	addElementsToList(4);
	assertTrue(list.size() == 4);
	list.remove(0);
    }
    
    
    /**
    * Creates a list: 9 8 7 6 5 4 3 2 1
    * then deletes all the odd values
    * resulting list:  9 7 5 3 1
    */
    @Test
//pass
    public void testDeleteEvenNumbers(){
        assertTrue(list.isEmpty());
	addElementsToList(9);	

	
	for (Integer i: list){
	    if (i % 2 == 0){
		list.remove(i);
	    }
	}       
        // Next, check that the list contains only odd numbers        
        for(Integer i: list){
            assertTrue(i%2 != 0);
        }
        
    }

    /**
     * Attempts to remove from an empty list
     * checks to make sure that the IllegalStateException
     * is thrown
     */

	//fail
    @Test(expected=NoSuchElementException.class)
    public void testEmptyRemove(){
	assertTrue(list.isEmpty());
	list.remove(1);
	
    }

    /**
     * Attempts to call next() when already at the
     * end of the list.
     * checks to make sure that the NoSuchElementException
     * is thrown
     */
    @Test(expected=NoSuchElementException.class)
    public void testInvalidNext(){
        assertTrue(list.isEmpty());
        Iterator<Integer> it = list.iterator();
        it.next();
    }

    /**
     * Test thaht the getNthFromLast method raises IndexOutOfBoundsException
     * when called passing an index out of list's range.. Given the numbers
     * that the list holds, counting from zero, the 4th from last should be 4.
     * For the list: 9 8 7 6 5 4 3 2 1
     * the 0th from last element is 1
     * the 1st from last element is 2
     * the 2nd from last is 3
     * etc...
     */
     //fail
    @Test
    public void testGetNthFromLast(){
	assertTrue(list.isEmpty());
	addElementsToList(9);
	assertTrue(list.size() == 9);
        Integer ans = list.getNthFromLast(3);
        assertEquals(new Integer(4),ans);
    }
    
    /**
     * Test thaht the getNthFromLast method raises IndexOutOfBoundsException
     * when called passing an index out of list's range.. Given the numbers
     * that the list holds, counting from zero, the 3rd from last should be 4.
     * For the list: 5 4 3 2 1
     * the 0th from last element is 1
     * the 1st from last element is 2
     * the 2nd from last is 3
     * etc...
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetNthFromLastWithNOutOfLimit(){
	assertTrue(list.isEmpty());
	addElementsToList(5);
	assertTrue(list.size() == 5);
        Integer ans = list.getNthFromLast(5); // should throw exception        
    }
    
    /**
     * Test the clear() method actually deletes all items from the list
     * and the size of the list becomes zero.
     */
    @Test
    public void testClearList(){
	assertTrue(list.isEmpty());
	addElementsToList(5);
	assertTrue(list.size() == 5);
	list.clear();
	assertTrue(list.isEmpty());
	assertTrue(list.size() == 0);
    }   
}
