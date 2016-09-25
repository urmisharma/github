
import java.util.Arrays;
/**
* Name: Urmila Sharma
* Assignment: HW01

*/

public class MyArrayList<T> 
{

		
		private T[] data;
		private final int DEFAULT_SIZE=20;
		private int size;
		private int index;
		
/**
*Construction for class MyArrayList.It takes an integer which is a size of an
 * array.
 */

	public MyArrayList(int size)
	{
		data = (T[]) new Object[size];

	}

/**
* No argument constructor.
*Default size is set to 20, variable size and index is initialized.
*/

	public MyArrayList( )
	{
		data = (T[]) new Object[20];
		size = 0;
		index = 0;
	}

/*
Add method adds the given item to the end of the list
*/
	public void add(T item)
	{
		data[index]=item;
		size++;
		index++;
	}
		
/**
Remove method remove the item from the ArrayList according to  the given index
*/

	public void remove(int index)
	{
		
		for (int i= index; i<size; i++)
		{
			data[i]=data[i+1];
			
		}
		
	}
/**
size method return the size of the ArrayList
*/	
	public int size()
	{
		return size;

	}
/**
getData method returns the element presents at the index given

*/	

	public T getData(int index)
	{
		return (T) data[index];

	}
/**
toString method returns list of data present in the ArrayList

*/
	public String toString() 
	{
		return Arrays.toString(data);
	
	}

}// end of the class MyArrayList
