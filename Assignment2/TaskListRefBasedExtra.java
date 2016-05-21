/*
* David Li V00818631 May 30,2015
*TaskListRefBasedExtra.java
*This program implements the TaskList class. It can insert, retrieve and remove items in the linked list.
*Also, it can return the length of the list, print the list and determine if it is empty.
/* This program includes doubly linked lists and methods that start from the tail;
*/

public class TaskListRefBasedExtra implements TaskListExtra {
	
	//start of the list
	private ListNodeExtra head;
	//end of the list
	private ListNodeExtra tail;
	private int listItems;
	
	
	//initialize the list
    public TaskListRefBasedExtra() {
		head = null;
		tail = null;
		listItems = 0;
    }

	//returns the length of the list
	//tranverse the list
    public int getLength() {
		listItems = 0;
		if(head == null) {
			return 0;
		}
		ListNodeExtra curr = head;
		while(curr != null) {
			curr = curr.next;
			listItems++;
		}
        System.out.println("ECHO getLength()");
        return listItems;
    }

	//checks if the list is empty
    public boolean isEmpty() {
        System.out.println("ECHO isEmpty()");
		if(getLength() == 0) {
			return true;
		}
		else {
			return false;
		}
    }

	//this method will remove the head of the list and make a new head if neccessary
    public Task removeHead() {
		Task result = null;
		
		if(getLength() != 0) {
			
			if(head.next == null) {
				head = null;
				tail = null;
				System.out.println("The List is Empty");
			}
			//if there is only a list with a head remove it
			else if(getLength() == 1) {
				result = head.task;
				head = null;
			}
			//if the list is greater than 2 make the node next the to old head the new head
			else {
				result = head.task;
				head = head.next;
				head.prev = null;
			}
		}
		
        System.out.println("ECHO removeHead() [" + result.priority + ":" + result.number + "]");
        return result;
    }
	
	  //this task will remove the tail
	  public Task removeTail() {
		Task result = null;
		if(getLength() != 0) {
			
			if(tail.prev == null) {
				head = null;
				tail = null;
				System.out.println("The List is Empty");
			}
			//if there is only a list with a head remove it
			else if(getLength() == 1) {
				result = tail.task;
				tail = null;
			}
			//if the list is greater than 2 make the node next the to old head the new head
			else {
				result = tail.task;;
				tail = tail.prev;
				tail.next = null;
			}
		}
		
        System.out.println("ECHO removeTail() [" + result.priority + ":" + result.number + "]");
        return result;
    }
	
    //remove a specified task
    public Task remove(Task t) {
		
		Task nextTask = null;
		Task removed = null;
		ListNodeExtra curr = null;
		ListNodeExtra prev = null;

		if(getLength() == 0) {
			System.out.println("The List is Empty");
		}
		else {
			
			//start at the head node
			curr = head;
			//remove the head if it matches
			if(((curr.task).priority == t.priority && t.number ==(curr.task).number)) {
				removed = removeHead();
				System.out.println("ECHO remove(Task(" + removed.priority + " " +
            removed.number + "))");
				return removed;
			}
			//cycle through the list
			while(curr != null) {
				//get the next node's task
				nextTask = (curr.task);
				//if the next node's task values the same as t remove it
				if(t.priority == nextTask.priority && t.number ==(curr.task).number) {
					//remove the node from the list
					removed = (curr).task;
					//make the current node link to the node past the removed one
					(curr.prev).next = curr.next;
					
					if(curr.next != null) {
						(curr.next).prev = curr.prev;
					}
					else {
						removeTail();
						break;		
					}
				
				}
			
				//go to the next list item
				curr = curr.next;
			}
			
		}
        
		System.out.println("ECHO remove(Task(" + t.priority + " " +
            t.number + "))");
		//return the removed node
        return removed;
   
    }
	
	//remove a specified task starting from the tail
	public Task removeFromTail(Task t) {
		
		Task nextTask = null;
		Task removed = null;
		ListNodeExtra curr = null;
		ListNodeExtra prev = null;

		if(getLength() == 0) {
			System.out.println("The List is Empty");
		}
		else {
			
			//start at the head node
			curr = tail;
			//remove the head if it matches
			if(((curr.task).priority == t.priority && t.number ==(curr.task).number)) {
				removed = removeTail();
				System.out.println("ECHO remove(Task(" + removed.priority + " " +
            removed.number + "))");
				return removed;
			}
			//cycle through the list
			while(curr != null) {
				//get the next node's task
				nextTask = (curr.task);
				//if the next node's task values the same as t remove it
				if(t.priority == nextTask.priority && t.number ==(curr.task).number) {
					//remove the node from the list
					removed = (curr).task;
					//make the current node link to the node past the removed one
					
					(curr.next).prev = curr.prev;
					
					if(curr.prev != null) {
						(curr.prev).next = curr.next;
					}
					else {
						removeHead();
						break;		
					}
				
				}
			
				//go to the next list item
				curr = curr.prev;
			}
			
		}
        
		System.out.println("ECHO removeFromTail(Task(" + t.priority + " " +
            t.number + "))");
		//return the removed node
        return removed;
   
    }
	
	//insert a new Task into the linked list sorted by descending priority 
    public void insert(Task t) {
		
		ListNodeExtra newNode = new ListNodeExtra(t);
		//Create a new list or assign a new head node if the priority is higher
		if(head == null){
			head = newNode;
			tail = newNode;
			System.out.println("ECHO insert(Task(" + t.priority + " " +
            t.number + "))");
			return;
		}
		//if the newNode has higher priority than the head make the newNode the head
		else if(((newNode).task).priority >= ((head).task).priority){
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			System.out.println("ECHO insert(Task(" + t.priority + " " +
            t.number + "))");
			return;
		}
		//cycle through the list
		ListNodeExtra curr = head;
		ListNodeExtra prev = null;
		while(curr != null) {
			//if the task's priority is lower than the current priority keep on tranversing the list
			if(t.priority <= (curr.task).priority) {
				prev = curr;
				curr = curr.next;
				//if the task's priority is equal to the current priority continue to tranverse the list
			} 
			//if the task's priority is greater than the current priority end the loop
			else {	
				break;
			}
			
			
		}
		//insert newNode as the first node
		if(prev == null) {
			curr.prev = newNode;
			newNode.next = curr;
			head = newNode;
		} 
		//if the end of the list is reached assign the newNode as the tail
		else if(curr == null){
			prev.next = newNode;
			newNode.prev = prev;
			tail = newNode;
		}
		//put the node inbewteen the prev and curr nodes
		else {
			prev.next = newNode;
			newNode.next = curr;
			curr.prev = newNode;
			newNode.prev = prev;
		}
        System.out.println("ECHO insert(Task(" + t.priority + " " +
            t.number + "))");
    }
	
	//insert a new Task into the linked list sorted by descending priority 
    public void insertFromTail(Task t) {
		
		ListNodeExtra newNode = new ListNodeExtra(t);
		//Create a new list or assign a new head node if the priority is higher
		if(tail == null){
			head = newNode;
			tail = newNode;
			System.out.println("ECHO insertFromTail(Task(" + t.priority + " " +
            t.number + "))");
			return;
		}
		//if the newNode has higher priority than the head make the newNode the head
		else if(((newNode).task).priority <= ((tail).task).priority){
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
			System.out.println("ECHO insertFromTail(Task(" + t.priority + " " +
				t.number + "))");
			return;
		}
		//cycle through the list
		ListNodeExtra curr = tail;
		ListNodeExtra prev = null;
		while(curr != null) {
			//if the task's priority is lower than the current priority keep on tranversing the list
			if(t.priority >= (curr.task).priority) {
				prev = curr;
				curr = curr.prev;
			} 
			else {	
				
				break;
			}
		}
			
		//insert newNode as the first node
		if(prev == null) {
			curr.next = newNode;
			newNode.prev = curr;
			tail = newNode;
		} 
		//if the end of the list is reached assign the newNode as the tail
		else if(curr == null){
			prev.prev = newNode;
			newNode.next = prev;
			head = newNode;
		}
		//put the node inbewteen the prev and curr nodes
		else {
			prev.prev = newNode;
			newNode.next = prev;
			curr.next = newNode;
			newNode.prev = curr;
		}
        System.out.println("ECHO insertFromTail(Task(" + t.priority + " " +
            t.number + "))");
    }
		//return a Task retrieved from a certain index
    public Task retrieveFromTail(int i) {
		if(i < 0) {
			return null;
		}
		ListNodeExtra curr = tail;
		for(int k =i; k> 0; k--) {
			if(curr.prev == null) {
				return null;
			}
			curr = curr.prev;
		}
		// return the retrieved task
		System.out.println("ECHO retrieveFromTail(" + i + ")");
		return curr.task;
	}
	//return a Task retrieved from a certain index and starts from the head
    public Task retrieve(int i) {
		if(i < 0) {
			return null;
		}
		ListNodeExtra curr = head;
		for(int k =0; k< i; k++) {
			if(curr.next == null) {
				return null;
			}
			curr = curr.next;
		}
		// return the retrieved task
		System.out.println("ECHO retrieve(" + i + ")");
		return curr.task;
	}
	
	//this method will tranverse the list and print out the task's priority and number respectively 
	public void printList() {
		ListNodeExtra curr = head;
		// if the list is empty do not print anything
		if(getLength() == 0) {
			System.out.println("The list cannot be printed because it is empty.");
		}
		else {
			System.out.println();
			while(curr.next != null) {
				String message = curr.toString();
				System.out.println(message);
				curr = curr.next;
			}
			//print the last node
			String message = curr.toString();
			System.out.println(message);
		}
		System.out.println("ECHO printList()");
	}
	public void printListFromTail() {
		ListNodeExtra curr = tail;
		// if the list is empty do not print anything
		if(getLength() == 0) {
			System.out.println("The list cannot be printed because it is empty.");
		}
		else {
			System.out.println();
			while(curr.prev != null) {
				String message = curr.toString();
				System.out.println(message);
				curr = curr.prev;
			}
			//print the last node
			String message = curr.toString();
			System.out.println(message);
		}
		System.out.println("ECHO printListFromTail()");
	}

}
