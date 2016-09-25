import java.util.Iterator;

/**
*Startup class constains main method to run the program.
* it is set as of type integer.
* numbers are added,removed and printed.
* Iterator is used to get nextelement,check nextelement.
*/
public class Startup {

	public static void main(String[] args)
	{

		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(3);
		list.add(6);
		list.add(7);
		list.add(9);
		
		Iterator<Integer> iter = list.iterator();
		
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.hasNext());
		System.out.println(iter.next());

		list.remove(3);
		list.remove(1);
		list.remove(7);
		
		System.out.println(iter.hasNext());
		System.out.println(iter.next());
		System.out.println(iter.hasNext());
		
		for(Integer i: list)
		{
			System.out.println("item = " + i);
		}
		
		
	}







}

