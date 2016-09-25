public class Startup {

	public static void main(String [] args)
	{
		MyArrayList<Integer> list = new MyArrayList();

		list.add(12);
		list.add(3);
		list.add(4);
		list.add(8);
		list.add(1);
		list.add(9);
		list.add(2);
		list.add(5);
		list.add(6);
		list.add(7);
		list.remove(1);
		list.remove(3);

/*
print all the contains of the ArrayList after add and remove of the element is done.

*/
	
	System.out.println("The arraylist is:"+ list);


	}


}// end of Startup class

