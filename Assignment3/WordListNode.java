/* David Li V00818631 
*  WordListNode.java
*  This program acts as a simple Node for the WordListNode
*  It contains a data in the form of a string and a link to the next node
*/

public class WordListNode {
	
	String data;
    WordListNode next;

    WordListNode(String data) {
        this.data = data;
        this.next = null;
    }
}