public class TaskListEcho implements TaskList {
    public TaskListEcho(int size) {

    }


    public int getLength() {
        System.out.println("ECHO getLength()");
        return -1;
    }


    public boolean isEmpty() {
        System.out.println("ECHO isEmpty()");
        return false;
    }



    public Task removeHead() {
        Task result;

        System.out.println("ECHO removeHead()");
        return null;
    }
    

    
    public Task remove(Task t) {
        System.out.println("ECHO remove(Task(" + t.priority + " " +
            t.number + "))");
        return null;
    }


    public void insert(Task t) {
        System.out.println("ECHO insert(Task(" + t.priority + " " +
            t.number + "))");
        return;
    }


    public Task retrieve(int i) {
        System.out.println("ECHO retrieve(" + i + ")");
        return null;
    }
}
