/**
 * Task.java
 * Created for CSC 115 Assignment Two.
 */

/**
 * Task objects contain the information necessary to represent
 * an task in a scheduling simulator, i.e., task priority
 * and task number.
 *
 * This class is not meant to be used as a linked-list node!
 */
public class Task {
    int priority;
    int number;

    Task(int priority, int number) {
        this.priority = priority;
        this.number   = number;
    }
}
