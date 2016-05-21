/*  David Li V00818631
*   StringStackRefBased.java
*	Implements the StringStack interface using a linked list 
*
*/
public class StringStackRefBased implements StringStack{
	Node top;
	
	//constructor
	public StringStackRefBased() {
		top = null;
	}
	    /**
     * Returns true if there are no strings in the stack; false
     * otherwise.
     * @return true or false as described
     */
    public boolean isEmpty() {
		if(top == null) {
			return true;
		}
		else {
			return false;
		}
	}

    /**
     * If the stack contains at least one string, then the string
     * at the top of the stack is removed, and the value contained
     * returned to the caller. Otherwise if the stack was empty
     * at the start of the method, then a StringStackException must
     * be thrown.
     *
     * @return the value of the String at the top of the stack.
     */
    public String pop() throws StringStackException {
		//as long as the stack isn't empty items can be removed
		String result = "";
		if(top != null) {
			result = top.data;
			top = top.next;
			return result;
		}
		else {
			throw new StringStackException("Stack is Empty. Cannot pop");
		}
	}

    /**
     * Clears the stack such that is contains no elements (i.e.,
     * isEmpty() on the stack will be true after this call completes).
     */
    public void popAll(){
		top = null;
	}

    /**
     * Places the item onto the top of the stack. However, if there
     * is no room in which to place this item, then the method
     * must instead thrown a StringStackException.
     */
    public void push(String item) throws StringStackException{
		top = new Node(item, top);
	}


    /**
     * If the stack contains at least one string, then the value of
     * the String at the top of the stack is returned to the caller.
     * Otherwise if the stack was empty at the start of the method, 
     * then a StringStackException must be thrown. This method
     * does not modify the stack's contents.
     *
     * @return the value of the String at the top of the stack.
     */
    public String peek() throws StringStackException{
		//exception if the string is empty
		if(top != null) {
			return top.data;
		}
		else {
			throw new StringStackException("Stack is Empty. Cannot peek");
		}
	}
	
	public static boolean compareAndReport(StringStackRefBased given,
         String expected[])
    {
        boolean passed = true;
        String result = "";

        for (int i = 0; i < expected.length; i++) {
			try {
				result = given.pop();
			}
			catch(StringStackException e) {
				System.out.println("Testing has failed");
			}
            if (result != expected[i]) {
                passed = false;
                break;
            }
        }
        return passed;
    }
	public static void main(String[] args) {
		StringStackRefBased stack = new StringStackRefBased();
        String result = "";
        boolean passed = true;

        // Test behavior of isEmpty()
        if (stack.isEmpty()) {
            System.out.println("Test 1 (isEmpty): passed");
        } else {
            System.out.println("Test 1 (isEmpty): FAILED");
        }
		
		// Test behavior of isEmpty after one insertion.
		// isEmpty should return false
		stack = new StringStackRefBased();
		try {
			stack.push("Python");
		}
		catch(StringStackException e) {
			System.out.println(e);
		}
        if (!(stack.isEmpty())) {
            System.out.println("Test 2 (isEmpty): passed");
        } else {
            System.out.println("Test 2 (isEmpty): FAILED");
        }
		
		//Test pushing 1 item and then peek
		stack = new StringStackRefBased();
		try {
			stack.push("Games");
			result = stack.peek();
		}
		catch(StringStackException e) {
			System.out.println(e);
		}
		
		if (result == "Games") {
			System.out.println("Test 3 (push then peek): passed");
		} else {
			System.out.println("Test 3 (push then peek): FAILED");
		}
		
		//Test behavior of peek with blank list
		stack = new StringStackRefBased();
		passed = false;
		try {
			result = stack.peek();
		}
		catch(StringStackException e) {
			System.out.println(e);
			passed = true;
		}
		
		if (passed) {
			System.out.println("Test 4 (peek): passed");
		} else {
			System.out.println("Test 4 (peek): FAILED");
		}
		
		// Test behavior of isEmpty after one insert and pop
		stack = new StringStackRefBased();
		try {
			stack.push("fun");
			result = stack.pop();
		}
		catch(StringStackException e) {
			System.out.println(e);
		}
		
		if (stack.isEmpty() && result.equals("fun")) {
			System.out.println("Test 5 (push then pop): passed");
		} else {
			System.out.println("Test 5 (push then pop): FAILED");
		}
		
		// Test behavior of pop when the stack is empty
		stack = new StringStackRefBased();
		passed = false;
		try {
			result = stack.pop();
		}
		catch(StringStackException e) {
			System.out.println(e);
			passed = true;
		}
		
		if (passed) {
			System.out.println("Test 6 (pop): passed");
		} else {
			System.out.println("Test 6 (pop): FAILED");
		}
		
		//Test behaviour of popAll after 3 items added
		stack = new StringStackRefBased();
		try {
			stack.push("fun1");
			stack.push("fun2");
			stack.push("fun3");
			stack.popAll();
		}
		catch(StringStackException e) {
			System.out.println(e);
		}
		
		if (stack.isEmpty()) {
			System.out.println("Test 7 (push then popAll): passed");
		} else {
			System.out.println("Test 7 (push then popAll): FAILED");
		}
		
		//Test behaviour of popAll after 3 items added and 1 item popped
		stack = new StringStackRefBased();
		try {
			stack.push("Summer");
			stack.push("Hope");
			stack.push("Dreams");
			stack.push("Vacation");
			result = stack.pop();
			stack.popAll();
		}
		catch(StringStackException e) {
			System.out.println(e);
		}
		
		if (stack.isEmpty() && result == "Vacation") {
			System.out.println("Test 8 (push, pop, popAll): passed");
		} else {
			System.out.println("Test 8 (push, pop, popAll): FAILED");
		}
		
		//Test if the items that are inserted properly
		stack = new StringStackRefBased();
		String test7after[] = {"sub","library","ECS","DTB","uvic"};
		try {
			stack.push("uvic");
			stack.push("DTB");
			stack.push("ECS");
			stack.push("library");
			stack.push("sub");
			
			passed = compareAndReport(stack, test7after);
		}
		
		catch(StringStackException e) {
			System.out.println(e);
		}
        System.out.println("Test 9 (multiple inserts): " +
            (passed ? "passed" : "FAILED"));
		
		//Test behaviour of multiple pushes followed by a pop and peek
		stack = new StringStackRefBased();
		String test8after[] = {"turpin","bobwright","hickman"};
		try {
			stack.push("hickman");
			stack.push("bobwright");
			stack.push("turpin");
			stack.push("library");
			stack.push("sub");
			
			stack.pop();
			result = stack.peek();
			stack.pop();
			passed = compareAndReport(stack, test8after);
		}
		
		catch(StringStackException e) {
			System.out.println(e);
		}
		if(!(result == "library")) {
			passed = false;
		}

        System.out.println("Test 10 (semi-full test): " +
            (passed ? "passed" : "FAILED"));
		
		
	}
}