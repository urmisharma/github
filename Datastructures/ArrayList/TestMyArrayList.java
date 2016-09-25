import static org.junit.Assert.*;
import junit.framework.TestCase;
import java.util.ArrayList;

public class TestMyArrayList<T> extends TestCase 
{
	private MyArrayList<Integer> list;
	private MyArrayList<String>  list2;
	private final int DEFAULT_SIZE=20;
	/**
	* setUp() is a constructor for test class.
	* Instances of MyArrayList is created
	* 
	*/

	public void setUp(){
		list = new MyArrayList<Integer>(20);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);

		list2 = new MyArrayList<String>(20);
		list2.add("I");
		list2.add("love");
		list2.add("to");
		list2.add("cook");
		
	}
	/**
	* testAdd() method test if the value we add is added to the list correctly 		* or not.It should add the value to the end of the list.
	* getData() method is used to test if value is in right index.
	*/
	public void testAdd(){
		
		assertTrue(list.getData(0).equals(1));
		assertTrue(list.getData(1).equals(2));
		assertTrue(list.getData(2).equals(3));
		assertTrue(list.getData(3).equals(4));

		assertTrue(list2.getData(0).equals("I"));
		assertTrue(list2.getData(1).equals("love"));
		assertTrue(list2.getData(2).equals("to"));
		assertTrue(list2.getData(3).equals("cook"));
	}
	/**
	* testRemove() test if the value is removed correctly or not.
	* getData() method is used to test remove method. It shows the current 		* value in the index after remove is done. 
	* When value in 0 index is removed from list. index 0 should include 2 as 		* new value.
	*/
	
	public void testRemove(){

		list.remove(0);
		list2.remove(0);
		assertTrue(list.getData(0).equals(2));
		assertTrue(list2.getData(0).equals("love")); //when the program removes string
	}

	/**
	* testSize() test the size of the MyArrayList.

	*/
	public void testsize()
	{
		
		assertEquals(list.size(),6 );
		assertEquals(list2.size(),4 );
	}
	/**
	* testtoString() test the toString method. It also print out the arrays 	* using toString method. 

	*/
		
	public void testtoString()
	{
		
		assertTrue(list2.toString().equals("[I, love, to, cook, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]"));
	}
}//end of test class
