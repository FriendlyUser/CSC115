/*  David Li V00818631
*	Node.java
*	This file contains the Nodes used in the linked list
*   Each Node has a string and a link to the next node
*/
public class Node {
	Node next;
	String data;
	
	public Node(String data) {
		this.next = null;
		this.data = data;
	}
}