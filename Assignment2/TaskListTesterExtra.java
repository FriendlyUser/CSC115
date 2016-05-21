/*
*
*David Li V00818631
*TaskListTesterExtra.java
*This program will test if the implementation of TaskListRefBasedExtra.java is correct. 
*It will see if the methods of TaskListRefBasedExtra.java work as intended.
*Test 1-10 are for the singly linked list
*The tests from 11-18 test the functionality of the extra components;
*/
import java.util.*;
import java.io.*;

public class TaskListTesterExtra {
	public static void main(String args[]) {
		

		TaskListRefBasedExtra t1 = new TaskListRefBasedExtra();
		boolean passed = true;
		Task Test = null;
		Task Test1 = null;
		
		//start doing the passed nonsense;
		//Test 1: Check if the length of the list is 0
		// it should return 0
		System.out.println("Test 1.");
		if(t1.getLength() == 0)
		{
			System.out.println("Test 1!; getLength() SUCCESS");
		}
		else {
			System.out.println("Test 1!; getLength() FAILED");
		}
		t1.printList();		
		System.out.println();
		//Test 2: Check if the list is empty
		//should return true
		System.out.println("Test 2.");
		t1 = new TaskListRefBasedExtra();
		if(t1.isEmpty()) {
			System.out.println("Test 2!; isEmpty() SUCCESS");
		}
		else {
			System.out.println("Test 2: isEmpty() FAILED");
		}
		t1.printList();
		System.out.println();
		
		
		//Test 3: check if a new list can be created
		// the length of the list should now be 1
		System.out.println("Test 3.");
		t1 = new TaskListRefBasedExtra();
		t1.insert(new Task(5,10));
		if(t1.getLength() == 1) {
			System.out.println("Test 3: insert() create list SUCCESS");
		}
		else {
			System.out.println("Test 3: insert() create list FAILED");
		}
		t1.printList();
		System.out.println();
		
		//Test 4: Add multiple items to the newly created list
		//the length of the list should be 3
		System.out.println("Test 4.");
		t1 = new TaskListRefBasedExtra();
		System.out.println();
		t1.insert(new Task(1,1));
		t1.insert(new Task(2,2));
		t1.insert(new Task(3,3));
		
		if(t1.getLength() == 3) {
			System.out.println("Test 4: insert() multiple SUCCESS");
		}
		else {
			System.out.println("Test 4: insert() multiple FAILED");
		}
		t1.printList();
		
		//Test 5: check if the retrieve function returns the proper Task
		//the task should be [7,7]
		System.out.println();
		System.out.println("Test 5.");
		t1 = new TaskListRefBasedExtra();
		t1.insert(new Task(2,1));
		t1.insert(new Task(4,3));
		t1.insert(new Task(7,7));
	
		Test = new Task(7,7);
		//list starts at 0
		Test1 = (t1.retrieve(0));
		
		passed = true;
		//check if the values are the same as what is retrieved
		if((Test.priority == (Test1.priority) && Test.number == (Test1.number))) {
			passed = true;
			
		}
		else {
			passed = false;

		}
	
		if(passed){
			System.out.println("Test 5: retrieve() stationary SUCCESS");
			System.out.println("The number of the retrieved Task is " + (Test1).number + " and the is priority: " + (Test1).priority);
		}
		else {
			System.out.println("Test 5: retrieve() stationary FAILED The number:");
			System.out.println( "The number of the retrieved Task is "+ (Test1).number + " and the is priority: " + (Test1).priority);
		}
		t1.printList();
		System.out.println();
		
		//Test 6: check if the retrieve function returns the proper Task if the list must be tranversed
		System.out.println("Test 6.");
		t1 = new TaskListRefBasedExtra();
		
		//insert a Task into the list
		t1.insert(new Task(5,5));
		t1.insert(new Task(7,7));
		t1.insert(new Task(8,69));
		t1.insert(new Task(9,100));
		Test = new Task(8,69);
		//list starts at 0
		Test1 = (t1.retrieve(1));
		
		passed = true;
		//check if the values are the same as what is retrieved
		if((Test.priority == (Test1.priority) && Test.number == (Test1.number))) {
			passed = true;
			
		}
		else {
			passed = false;

		}
	
		if(passed){
			System.out.println("Test 6: retrieve() moving. SUCCESS");
			System.out.println("The number of the retrieved Task is " + (Test1).number + " and the is priority: " + (Test1).priority);
		}
		else {
			System.out.println("Test 6: retrieve() moving. FAILED");
			System.out.println( "The number of the retrieved Task is "+ (Test1).number + " and the is priority: " + (Test1).priority);
		}
		t1.printList();
		System.out.println();
		
		//Test 7: check if the removeHead() will assign the second node as the new head
		System.out.println("Test 7.");
		t1 = new TaskListRefBasedExtra();
		t1.insert(new Task(5,5));
		t1.insert(new Task(7,7));
		t1.insert(new Task(8,69));
		t1.insert(new Task(9,100));
		
		//this should be the new head after removeHead() is called
		Test = new Task(8,69);
		t1.printList();
		t1.removeHead();
		t1.printList();
		//retrieve the head of the list
		Test1 = t1.retrieve(0);
		if(Test1.number == Test.number && Test1.priority == Test1.priority) {
			passed = true;
		}
		else {
			passed = false;
		}
		if(passed) {
			System.out.println("Test 7: removeHead() SUCCESS!");
		}
		else {
			System.out.println("Test 7: removeHead() FAILED!");
		}
		t1.printList();
		System.out.println();
		
		//Test 8: check if the remove() returns the proper Task
		System.out.println("Test 8.");
		t1 = new TaskListRefBasedExtra();
		t1.insert(new Task(1,5));
		t1.insert(new Task(2,4));
		t1.insert(new Task(3,3));
		Test = new Task(2,4);
		t1.printList();
		Test1 = (t1.remove(Test));
		if(Test1.number == Test.number && Test.priority == Test1.priority) {
			passed = true;
			System.out.println("Test 8: remove() returns the removed task. SUCCESS");
		}
		else {
			passed = false;
			System.out.println("Test 8: remove() returns the removed task. FAILED!" + Test1.priority + Test1.number);
		}
		t1.printList();
		System.out.println();
		
		//Test 9: Check if a removed item is actually removed
		System.out.println("Test 9.");
		t1 = new TaskListRefBasedExtra();
		
		t1.insert(new Task(4,19));
		t1.insert(new Task(5,20));
		t1.insert(new Task(6,79));
		t1.insert(new Task(7,43));
		t1.insert(new Task(8,53));
		t1.insert(new Task(9,93));
		t1.printList();
		Test = t1.retrieve(3);
		Test1 = (t1.remove(Test));
		
		if(Test.number == Test1.number && Test.priority == Test1.priority) {
			t1.printList();
			System.out.println("Test 9: remove() Does it remove the right Node? SUCCESS");
		}
		else {
			t1.printList();
			System.out.println("Test 9: remove() Does it remove the Node? FAILED!");
		}
		
	
		//Test 10: based off example in the assignment description test. Calls many methods in rapid succession
		//the expected values should be 212,198,104 in that order;
		System.out.println();
		System.out.println("Test 10.");
		
		int expected[] = {212,198,104};
		t1 = new TaskListRefBasedExtra();
		t1.insert(new Task(11,212));
		t1.printList();
		t1.insert(new Task(19,100));
		t1.printList();
		t1.insert(new Task(11,198));
		t1.printList();
		t1.insert(new Task(7,104));
		t1.printList();
		Task head = t1.removeHead();
		if(head.number!= 100){
			passed = false;
		}
		t1.printList();
		for(int i =0; i <expected.length; i++) {
			Task t = t1.retrieve(i);
			t1.printList();
			if(t != null && t.number != expected[i]){
				passed = false;
				break;
			} 
			else if(t == null){
				passed = false;
				break;
			}
		}
		if(passed){
			System.out.println("Test 10: (semi-full Test) SUCCESS");
		}
		else{
			System.out.println("Test 10: (semi-full Test) FAILED!");
		}
	
		//Extra Tests for the doubly listed list
		//Test 11: Add multiple items to the newly created list
		//the length of the list should be 3
		System.out.println("Test 11.");
		t1 = new TaskListRefBasedExtra();
		System.out.println();
		t1.insertFromTail(new Task(1,1));
		t1.insertFromTail(new Task(2,2));
		t1.insertFromTail(new Task(3,3));
		
		if(t1.getLength() == 3) {
			System.out.println("Test 11: insertFromTail() multiple SUCCESS" );
		}
		else {
			System.out.println("Test 11: insertFromTail() multiple FAILED" + t1.getLength());
		}
		t1.printListFromTail();
		
		//Test 12: check if the retrieveFromTail function returns the proper Task
		//the task should be [7,7]
		System.out.println();
		System.out.println("Test 12.");
		t1 = new TaskListRefBasedExtra();
		t1.insertFromTail(new Task(2,1));
		t1.insertFromTail(new Task(4,3));
		t1.insertFromTail(new Task(7,7));
	
		Test = new Task(7,7);
		//list starts at 0
		Test1 = (t1.retrieveFromTail(2));
		
		passed = true;
		//check if the values are the same as what is retrieved
		if((Test.priority == (Test1.priority) && Test.number == (Test1.number))) {
			passed = true;
			
		}
		else {
			passed = false;

		}
	
		if(passed){
			System.out.println("Test 12: retrieve() stationary SUCCESS");
			System.out.println("The number of the retrieved Task is " + (Test1).number + " and the is priority: " + (Test1).priority);
		}
		else {
			System.out.println("Test 12: retrieve() stationary FAILED The number:");
			System.out.println( "The number of the retrieved Task is "+ (Test1).number + " and the is priority: " + (Test1).priority);
		}
		t1.printListFromTail();
		System.out.println();
		
		//Test 13: check if the retrieve function returns the proper Task if the list must be tranversed backwards
		System.out.println("Test 13.");
		t1 = new TaskListRefBasedExtra();
		
		//insert a Task into the list
		t1.insert(new Task(5,5));
		t1.insert(new Task(7,7));
		t1.insert(new Task(8,69));
		t1.insert(new Task(9,100));
		Test = new Task(8,69);
		//list starts at 0
		Test1 = (t1.retrieveFromTail(2));
		
		passed = true;
		//check if the values are the same as what is retrieved
		if((Test.priority == (Test1.priority) && Test.number == (Test1.number))) {
			passed = true;
			
		}
		else {
			passed = false;

		}
	
		if(passed){
			System.out.println("Test 13: retrieve() moving. SUCCESS");
			System.out.println("The number of the retrieved Task is " + (Test1).number + " and the is priority: " + (Test1).priority);
		}
		else {
			System.out.println("Test 13: retrieve() moving. FAILED");
			System.out.println( "The number of the retrieved Task is "+ (Test1).number + " and the is priority: " + (Test1).priority);
		}
		t1.printList();
		System.out.println();
		
		//Test 14: check if the removeTail() will cause the second node to be the new tail
		System.out.println("Test 14.");
		t1 = new TaskListRefBasedExtra();
		t1.insertFromTail(new Task(5,5));
		t1.insertFromTail(new Task(7,7));
		t1.insertFromTail(new Task(8,69));
		t1.insertFromTail(new Task(9,100));
		
		//this should be the new head after removeHead() is called
		Test = new Task(7,7);
		t1.printListFromTail();
		t1.removeTail();
		t1.printListFromTail();
		//retrieve the head of the list
		Test1 = t1.retrieveFromTail(0);
		if(Test1.number == Test.number && Test1.priority == Test1.priority) {
			passed = true;
		}
		else {
			passed = false;
		}
		if(passed) {
			System.out.println("Test 14: removeTail() SUCCESS!");
		}
		else {
			System.out.println("Test 14: removeTail() FAILED!");
		}
		t1.printListFromTail();
		System.out.println();
		
		//Test 15: check if the remove() returns the proper Task
		System.out.println("Test 15.");
		t1 = new TaskListRefBasedExtra();
		t1.insertFromTail(new Task(1,5));
		t1.insertFromTail(new Task(2,4));
		t1.insertFromTail(new Task(3,3));
		Test = new Task(2,4);
		t1.printList();
		Test1 = (t1.removeFromTail(Test));
		if(Test1.number == Test.number && Test.priority == Test1.priority) {
			passed = true;
			System.out.println("Test 15: removeFromTail() returns the removed task. SUCCESS");
		}
		else {
			passed = false;
			System.out.println("Test 15: removeFromTail() returns the removed task. FAILED!" + Test1.priority + Test1.number);
		}
		t1.printListFromTail();
		System.out.println();
		
		//Test 16: Check if a removed item is actually removed
		System.out.println("Test 16.");
		t1 = new TaskListRefBasedExtra();
		
		t1.insertFromTail(new Task(4,19));
		t1.insertFromTail(new Task(5,20));
		t1.insertFromTail(new Task(6,79));
		t1.insertFromTail(new Task(7,43));
		t1.insertFromTail(new Task(8,53));
		t1.insertFromTail(new Task(9,93));
		t1.printListFromTail();
		Test = t1.retrieveFromTail(3);
		Test1 = (t1.removeFromTail(Test));
		
		if(Test.number == Test1.number && Test.priority == Test1.priority) {
			t1.printListFromTail();
			System.out.println("Test 16: remove() Does it remove the right Node? SUCCESS");
		}
		else {
			t1.printListFromTail();
			System.out.println("Test 16: remove() Does it remove the Node? FAILED!");
		}
		
	
		//Test 17: based off example in the assignment description test. Calls many methods in rapid succession
		//the expected values should be 212,198,104 in that order;
		System.out.println();
		System.out.println("Test 17.");
		
	    int expected2[] = {212,198,104};
		t1 = new TaskListRefBasedExtra();
		t1.insertFromTail(new Task(11,212));
		t1.printListFromTail();
		t1.insertFromTail(new Task(19,100));
		t1.printListFromTail();
		t1.insertFromTail(new Task(11,198));
		t1.printListFromTail();
		t1.insertFromTail(new Task(7,104));
		t1.printListFromTail();
		head = t1.removeHead();
		if(head.number!= 100){
			passed = false;
		}
		t1.printList();
		for(int i =0; i <expected2.length; i++) {
			Task t = t1.retrieve(i);
			t1.printList();
			if(t != null && t.number != expected2[i]){
				passed = false;
				break;
			} 
			else if(t == null){
				passed = false;
				break;
			}
		}
		if(passed){
			System.out.println("Test 17: (semi-full Test) SUCCESS");
		}
		else{
			System.out.println("Test 17: (semi-full Test) FAILED!");
		}
		
		//Test 18: Testing all the functionality of the program all at once
		//based off example in the assignment description test. Calls many methods in rapid succession
		//the expected values should be 212,198,104 in that order;
		System.out.println();
		System.out.println("Test 18.");
		passed = true;
	    int expected3[] = {100,212,198};
		t1 = new TaskListRefBasedExtra();
		t1.insertFromTail(new Task(11,212));
		t1.insertFromTail(new Task(100,101));
		t1.insertFromTail(new Task(19,100));
		t1.insertFromTail(new Task(11,198));
		t1.insertFromTail(new Task(10,104));
		t1.insert(new Task(23,69));
		t1.insert(new Task(10,29));
		t1.remove(new Task(10,29));
		t1.insert(new Task(4,19));
		t1.insertFromTail(new Task(5,20));
		t1.insert(new Task(6,79));
		t1.insertFromTail(new Task(7,43));
		t1.insert(new Task(8,53));
		t1.insertFromTail(new Task(9,93));
		t1.printListFromTail();
		t1.printList();
		t1.removeTail();
		t1.removeTail();
		t1.removeTail();
		t1.removeTail();
		t1.removeTail();
		t1.removeHead();
		t1.insert(new Task(25,88));
		t1.removeHead();
		t1.removeTail();
		t1.printListFromTail();
		head = t1.removeHead();
		Task tail = t1.removeTail();
		if(head.number!= 69){
			passed = false;
		}
		t1.printList();
		for(int i =0; i <expected3.length; i++) {
			Task t = t1.retrieve(i);
			t1.printList();
			if(t != null && t.number != expected3[i]){
				passed = false;
				break;
			} 
			else if(t == null){
				passed = false;
				break;
			}
		}
		if(passed){
			System.out.println("Test 18: (Big Test) SUCCESS");
		}
		else{
			System.out.println("Test 18: (Big Test) FAILED!");
		}
	
	}
}