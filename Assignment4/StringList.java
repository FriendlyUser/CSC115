import java.util.*;

public class StringList {
    ArrayList<String> list;    

    public StringList() {
        list = new ArrayList<String>();
    }


    /** 
     * Examines the StringList; if there are no items  returns
     * true, otherwise false.
     *
     * @return whether or not the list contains Strings.
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /** 
     * Either retrieves or computes the number of Strings
     * currently stored in the list
     *
     * @return number greater than or equal to 0 corresponding
     * to number of items in the list.
     */ 
    public int getLength() {
        return list.size();
    }

    /** 
     * If the list has at least one String, then the
     * String at the end of the list (the tail) is removed.
     * If there are no Strings in the list, then the value returned
     * is null.
     *
     * @return a String object corresponding the the String
     * at the tail of the list before removal; possibly null.
     */
    public String removeTail() {
        if (list.size() > 0) {
            String result = list.remove(list.size() - 1);
            return result;
        } else {
            return null;
        }
    }

    /**                                                       
     * Accepts a String to be added to the end of the   
     * list (i.e., after the current ail.)                    
     * @param item String to be placed at the end of the list.         
     */ 
    public void insertTail(String item) {
        list.add(item);
    }

    /**                                                       
     * Takes an integer value indicate that the ith String
     * in the list is to be returned. When i is 0, the first  
     * String is returned, when i is 1, the second is   
     * returned, etc. If there is no such String i, then null         
     * is returned. The list is not modified by this operation.
     *
     * @param i indicates the number of the item from the start of          
     * of the list which will be the String returned    
     * @return contents of the ith item in the list; value is
     * null list is empty or if "i" is greater than or equal
     * to the list length.
     */                                                       
    public String retrieve(int i) {
        if (list.size() == 0) {
            return null;
        } else if (list.size() <= i) {
            return null;
        } else  if (i < 0) {
            return null;
        } else {
            return list.get(i);
        }

    }


    public static void main(String[] args) {
        StringList testList;
        boolean    passed;

        String t1 = "Testing is empty";
        testList = new StringList();
        passed = testList.isEmpty();
        System.out.println(t1 + ": " +
            (passed ? "passed" : "FAILED")
        );

        String t2 = "Testing one insertion";
        testList = new StringList();
        testList.insertTail("boo");
        passed = !testList.isEmpty();
        System.out.println(t2 + ": " +
            (passed ? "passed" : "FAILED")
        );

        String t3 = "Testing one insertion + removal";
        testList = new StringList();
        testList.insertTail("foo");
        if (testList.removeTail().equals("foo") &&
            testList.isEmpty())
        {
            passed = true;
        } else {
            passed = false;
        }
        System.out.println(t3 + ": " +
            (passed ? "passed" : "FAILED")
        );

        String t4 = "Insert four at tail";
        testList = new StringList();
        String[] a1 = {"aaa", "bbb", "ccc", "ddd"};
        for (String s : a1) {
            testList.insertTail(s);    
        }
        passed = true;
        for (int i = 0; i < a1.length; i++) {
            if (!testList.retrieve(i).equals(a1[i])) {
                passed = false;
                break;
            }
        }
        System.out.println(t4 + ": " +
            (passed ? "passed" : "FAILED")
        );

        String t5 = "Insert five at tail, delete from end";
        testList = new StringList();
        String[] a2_before = {"aaa", "bbb", "zzz", "ccc", "ddd"};
        String[] a2_after = {"aaa", "bbb", "zzz", "ccc"};
        for (String s : a2_before) {
            testList.insertTail(s);    
        }
        testList.removeTail();
        passed = true;
        for (int i = 0; i < a2_after.length; i++) {
            if (!testList.retrieve(i).equals(a2_after[i])) {
                passed = false;
                break;
            }
        }
        System.out.println(t5 + ": " +
            (passed ? "passed" : "FAILED")
        );
    }
    
}
