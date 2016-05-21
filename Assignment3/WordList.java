/* David Li V00818631 June 21,2015
*  WordList.java
*  This program will act as linked list 
*  WordList can determine if the list is empty and the length of the list
*  Insertion and deletion occurs only at the head of the list
*  It can retrieve data from a node and check it the list contains a certain string
*/
public class WordList {
	WordListNode head;
	public WordList() {
		 head = null;
	}
	//inserts entries at the head
	public void insertHead(String w) {
		WordListNode curr = head;
		head = new WordListNode(w);
		head.next = curr;
	}
	//removes the head and reassigns a new head if neccessary
	public String removeHead() {
		String result = null;
		if(isEmpty()) {
			return result;
		}
		else {
			if(getLength()==1) {
				result = head.data;
				head = null;
			}
			else {
				result = head.data;
				head = head.next;
			}
		}
		return result;
	}
	//checks if the String is contained in the linked list
	public boolean contains(String w) {
		boolean result = false;
		for (WordListNode curr = head; curr != null; curr = curr.next) {
            if(curr.data == w) {
				result = true;
				break;
			}
        }
		return result;
	}
	//returns the length of the list
	public int getLength() {
		int result = 0;

        for (WordListNode curr = head; curr != null; curr = curr.next) {
            result++;
        }
        return result;
	}
	//checks if the list is empty
	public boolean isEmpty() {
		return (head == null);
	}
	
	//returns the String at the index
	public String retrieve(int index) {
		if (index > getLength()) {
            return null;
        }

        int i = 0;
        WordListNode curr = head;
		if(index == i) {
			return curr.data;
		}
        while (curr != null) {
            if (index > i) {
                i++;
                curr = curr.next;
            } else {
                break;
            }
        }

        return curr.data;
	}
	//uses a given list, and checks in each entry in the linked list 
	 public static boolean compareAndReport(WordList given,
         String expected[])
    {
        boolean passed = true;
        String result;

        for (int i = 0; i < expected.length; i++) {
            result = given.retrieve(i);
            if (result != expected[i]) {
                passed = false;
                break;
            }
        }
        return passed;
    }
	//prints the all the strings in the linked list starting from the head
	public void printList() {
		for (WordListNode curr = head; curr != null; curr = curr.next) {
            System.out.print(curr.data);
			//print commas after each item as long as it is not the last item
			if(curr.next != null) {
				System.out.print(", ");
			}
        }
	}
	//main functions is used as the tester
    public static void main(String args[]) {
        WordList list = new WordList();
        String result;
        boolean passed;

        // Test behavior of isEmpty
        if (list.isEmpty()) {
            System.out.println("Test 1 (isEmpty): passed");
        } else {
            System.out.println("Test 1 (isEmpty): FAILED");
        }

        // Test behavior of length
        if (list.getLength() == 0) {
            System.out.println("Test 2 (length): passed");
        } else {
            System.out.println("Test 2 (length): FAILED");
        }
		
		// Test behavior of length after one insert
		list.insertHead("Funnies");
        if (list.getLength() == 1) {
            System.out.println("Test 3 (length): passed");
        } else {
            System.out.println("Test 3 (length): FAILED");
        }
		
		 // Test behavior of retrieve of the single item
        result = list.retrieve(0);

        if (result == "Funnies") {
            System.out.println("Test 4 (retrieve): passed");
        } else {
            System.out.println("Test 4 (retrieve): FAILED");
        }
		
		//Test behavior of removeHead with only one item	
		if(result == list.removeHead()) {
			System.out.println("Test 5(removeHead): passed");
			list.printList();
        } else {
            System.out.println("Test 5 (removeHead): FAILED");
        }
		// Test behavior when inserting "Games", "ComputerScience", and "Math" at
        // position zero. Is the resulting sequence equal
        // to Math, ComputerScience, Games, Funnies?
        String test6after[] = {"Games","ComputerScience","Math"};
        list.insertHead("Math");
        list.insertHead("ComputerScience");
        list.insertHead("Games");
        passed = compareAndReport(list, test6after);
        System.out.println("Test 6 (multiple inserts): " +
            (passed ? "passed" : "FAILED"));
		
		//Test behavior of removeHead with three items
		result = list.retrieve(0);
		list.removeHead();
		String test7after[] = {"ComputerScience","Math"};
		passed = compareAndReport(list,test7after);
		System.out.println("Test 7 (multiple inserts): " +
            (passed ? "passed" : "FAILED"));
			
		// Test behavior of contains after when adding Windows10 to the head of the list 
		
        list.insertHead("Windows10");
		passed = list.contains("Windows10");
        System.out.println("Test 8 (insert at end): " +
            (passed ? "passed" : "FAILED"));
      
		//Test behavior of contains when searching through the list
		list.insertHead("Testing");
		passed =list.contains("Math");
		System.out.println("Test 9 (insert at end): " +
            (passed ? "passed" : "FAILED"));
		//list.printList();
    }
}