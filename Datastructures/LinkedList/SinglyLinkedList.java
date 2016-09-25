/**
* Name : Urmila Sharma
* Assginment: Hw2(SinglyLinkedList)
* Date: Feb 18,2015
* Class: Data Structures
*
*/

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
* SinglyLinkedList class contains two iner class named
* Node<T> and Iterator<T>.
* SinglyLinkedList class contains add,remove,insertAt,getNthFromLast,getNthFromFirs
* toString(),clear,isEmpty,SinglyLinkedListIterator methods. 
*/


public class SinglyLinkedList<T> implements Iterable<T> {
	
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
/**
*constructor of SinglyLinkedList class.
*head and tail are initialized to null/
* size is set 0.
*/	
	

public SinglyLinkedList()
{
	head = null;
	tail= null;
	size = 0;
	
}
/**
* size method checks all the elements in the list and return number of element in the list.
*/

public int size()
{
	return size;
	
}

/**
*isEmpty method cheecks if the list is empty or not. If the list is empty it returns true.
* If the list is not empty, it returns false.
*/

public boolean isEmpty()
{
	if(size()==0)
	{
	return true;
	}
	else
	
	return false;
	
}

/**
* Add method add the given element to the end of the list.
*/

public void add(T item)
{
	if(isEmpty())
	{
		head = new Node<T>(item, null);
		tail = head;
	}
	else
	{
	tail.setNext(new Node<T>(item,null));
	tail= tail.getNext();
	}
		size++;
}
/**
*InsertAt method insert the given element to the given index. Counting here starts from 0.
* If Integer 2 is to be inserted in 1 index. It will be added to that position. So, 2 will
* be in 1 index not in the tail.
*/
public void insertAt(int index,T item)
{
	if(index<0||index>=size())
	{
		throw new IndexOutOfBoundsException();
	}
	if(index==size())
	{
		add(item);
		return;
	}
	if(index==0)
	{
		head= new Node(item,head);
	}
	else
	{
		Node<T> current = head;
		for(int i=0;i<index-1;i++)
		{
			current = current.getNext();
		}
		current.setNext(new Node<T>(item,current.getNext()));
	}
		size++;
	
}
/**
*Remove method removes the element. If two elements are same and that need to be removed. The first
* one in the list will be removed. 
Ihe element to be removed is not in the list. It throws Exception.
*/

public void remove(T data)
{

	if (head==null)
	{ 
	throw new NoSuchElementException("The element cannot be deleted");
	}
	if (head.data.equals(data))
	{
	head=head.next;
	size--;
	return;
	}
	
	Node<T> previous=null;
	Node<T> current=head;
	
	{
	
	while ( current != null && !current.data.equals(data))
	{
	previous=current;
	
	current =current.next;

	}
	
	}
	if (current==null)
	{ throw new NoSuchElementException("The element cannot be deleted");}
	previous.next=current.next;
	size--;
	return;
	


	
	
	
}

/**
*Clear method removes all the element from the list. The list will be empty.
*/

public void clear()
{
	head = null;
	tail = null;
	size = 0;
	
}

/**
*getNthFromFirst method returns the element in the list for given index from the first of the
* list.(head of the list).
* count of the index starts from 0.
*/

public T getNthFromFirst(int n)

{
	if(n<0||n>size())
	{
		throw new IndexOutOfBoundsException();
	}
	if(n<size())
	{
		Node<T> current = head;
		for(int i = 0;i<n;i++)
		{
			current = current.getNext();
			
		}
		return current.getData();
		
	}	
		return null;
		
	
}

/**
*getNthFromLast method returns the element in the list of given index,counting from last
* of the list(tail).
* count starts from 0.
*/
public T getNthFromLast(int n)
{
	
	if(n<0||n>=size())
	{
		throw new IndexOutOfBoundsException();
	}
	n=size()-(n+1);
	if(n<size()-1)
	{
		Node<T> current = head;
		for(int i = 0; i<n; i++)
		{
			current= current.getNext();
		}
		return current.getData();
	}
		return null;
}

/**
*SinglyLinkedListIterator method returns an iterator of the list.
*/

public Iterator<T> iterator() 
{
	
	return new SinglyLinkedListIterator();
}


/**
* toString method returns the elements of the list in a string representation.
*/


public String toString() 
{

	String FinalList = "";

	for (int i=0; i<size(); i++)

	{

	FinalList= FinalList + " " +getNthFromFirst(i);

	}

	FinalList="[" +FinalList + " ]";

	return FinalList;

}

/**
*Node class.It keeps the track of the element in the list.
*
* 
*/
private class Node<T> {
     	 T data;
	 Node<T> next;

public Node(T data, Node<T> next) 
{
		this.data = data;
		this.next = next;
}
public T getData()
{
	return data;

}
public void setData( T newData)
{
	this.data = newData;


}
public Node<T> getNext()
{
	return next;

}	
public void setNext(Node<T> newNext)
{
	this.next = newNext;

}

}//end of Node class


/**
*SinglyLinkedListIterator class
*
*/


private class SinglyLinkedListIterator implements Iterator<T>
{
	private Node<T> current;
	private Node<T> previous;
/**
*constructor of SinglyLinkedListIterator. 
*Variables are intiliazied. 
*/	
	
	public SinglyLinkedListIterator()
	{
		current = head;
		previous = null;
			
	}
/**
* it returns true if the next element is present in the list.
* if there is no next element it returns false.
*/
	@Override
	public boolean hasNext() {
		return current!= null;
		
	}
/**
* it returns the next element of the list. 
*
*/
	@Override
	public T next() {
		
		if(current == null)
		{
			throw new NoSuchElementException();
		}
		T temp = current.getData();
		previous = current;
		current= current.getNext();
		return temp;
		
	}
/**
*we donot want to remove from iterator as it will be risky. 
* Whenever remove method is called it throws Exception.
*/
	
public void remove() 
	{
		
		throw new UnsupportedOperationException (" remove operation is not supported bythis iterator ");
	}
		
	
	
}//end of SinglyLinkedListIterator class.








}

