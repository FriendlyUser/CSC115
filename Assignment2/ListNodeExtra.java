/* This class will be used in generating the nodes for the linked list */
//This class has a tail and stores the previous node in the prev
public class ListNodeExtra {
    Task task;
    ListNodeExtra next;
	ListNodeExtra prev;

    public ListNodeExtra(Task task) {
        this.task = task;
        this.next = null;
		this.prev = null;
    }
	public ListNodeExtra(Task task, ListNodeExtra next, ListNodeExtra prev) {
		this.task = task;
		this.next = next;
		this.prev = prev;
	}
	//this method will print all the tasks and their corresponding number and priority as a string
	public String toString() {
		return(String.format("[%d:%02d]",task.priority,task.number));
	}
}
