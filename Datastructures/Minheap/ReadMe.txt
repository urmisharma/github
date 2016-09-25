Name : Urmila Sharma
email: usharma@uno.edu
Date: 4/21/2015
Project : UNboundedPriorityQueue
project Includes: 
1. MinHeap.java2. TestMinHeap.java
3. UnboundedPriorityQueue.java3. TestUnboundedPriorityQueue.java

MinHeap class is a generic class and contains all the necessary method.
Methods in MinHeap class are:
1) insert
2) remove
3) size
4) isEmpty
5) toString

TestMinHeap class test insert, remove, size, isEmpty, toString and I have tried to cover all the corner cases. 

UnboundedPriorityQueue class is also a generic class and contains all necessary method.
1)enqueue
2)dequeue
3)size
4)toString

TestUnboundedPriorityQueue class test all methods of UnboundedPriorityQueue.It passes all the test and I have tried to cover all corner cases.


 
Instruction : To Run a Program,
               	Go to the compiler
	      	Type javac name of class
		Compile all the classes.
Then run it by:  
	
		java Startup input.txt

You can test the program. TestMinHeap.java is included in the file to check the MinHeap implementation.
To run test class:
Compile the Test class then 
Type:  java org.junit.runner.JUnitCore TestMinHeap
       java org.junit.runner.JUnitCore TestUnboundedPriorityQueue
       