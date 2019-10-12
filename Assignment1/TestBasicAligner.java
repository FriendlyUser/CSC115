/**
 * David Li, V00818631, May 16
 * TestBasicAligner.java 
 * 
 * This program tests if all of the methods in BasicAligner.java with a test sequence.
 * 
 */
public class TestBasicAligner {
	public static void main(String args[]) {
		 
          
		String [] sequence = {"AAAGGGC","GGC"};
		BasicAligner Test = new BasicAligner(sequence);
		
		//test if the functions can be called
		Test.performAlignment();
		Test.getSequence(0);
		Test.getSequence(1);
		
		//print out a load of values
		System.out.println("The placeholder value is " + Test.getOffset(1));
		System.out.println("The first sequence is value is " + Test.getSequence(0));
		System.out.println("The second sequence is value is " + Test.getSequence(1));
		System.out.println("The number of errors in the best alignment is value is " + Test.getNumErrors());

		
	}
	
}