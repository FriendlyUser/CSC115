/* This class will be used in generating the nodes for the linked list */
public class ListNode {
    Task task;
    ListNode next;
	ListNode prev;

    public ListNode(Task task) {
        this.task = task;
        this.next = null;
		this.prev = null;
    }
	public ListNode(Task task, ListNode next, ListNode prev) {
		this.task = task;
		this.next = next;
		this.prev = prev;
	}
	//this method will print all the tasks and their corresponding number and priority as a string
	public String toString() {
		return(String.format("[%d:%02d]",task.priority,task.number));
	}
}
