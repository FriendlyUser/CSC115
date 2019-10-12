/**
 * BasicAligner.java 
 * Edited for CSC115 Assignment One.
 *
 * This aligner does nothing, really, but it is really meant to
 * act as code to permit SeqAligner.java to compile.
 */

import java.io.*;
public class BasicAligner implements Aligner {
	
	//attributes
	String bigString;
	String smallString;
	int numLowMismatch; //records the lowest number of mismatched characters between the first and second string
	char[] firstStr;
	char[] secondStr;
	
	//constructor
    public BasicAligner(String big, String small) { 
		
		
		bigString = big;
		smallString = small;
		
		//output what the strings are 
		System.out.println("The values of the bigString are " + bigString);
		System.out.println("The values of the smallString are " + smallString);
		
		//convert the strings into arrays
		firstStr = bigString.toCharArray();
		secondStr = smallString.toCharArray();

	}


	
	public static void main(String args[]) {
		//insert the position to start the comparison between the first and second string.
		/* BasicAligner Test = new BasicAligner("AAAGGGC","GGC");
		System.out.println("The number of mismatched characters " + Test.matchAt(3,0));
		Test.performAlignment();
		System.out.println("The placeholder position is " + Test.placeholder); */
	}
	
	//returns the number of mismatched characters
	private int matchAt(int firstI,int secondI) {
		int match = 0;
	
		for(int i=0; i < secondStr.length; i++) {
			if(!(firstStr[firstI + i] == secondStr[secondI + i])) {
				match = ++match;
			}
		}
		return match;
	}
	
    public void performAlignment() {
		numLowMismatch = matchAt(0,0);
		for( int i = 0; i <= firstStr.length - secondStr.length; i++) {
			if (numLowMismatch > matchAt(i,0)){
				numLowMismatch = matchAt(i,0);
			}
		}
	}

	//first string be index 0, and second string will be represented by index 1 
    public int getOffset(int sequenceNumber) {
		int placeholder = 0; //records the insert position of the new string
		for(int i =0; i <= firstStr.length - secondStr.length; i++) {
			
			//checks for the current index has the smallest amount of mismatched characters
			if(matchAt(i,0) == numLowMismatch) {
				placeholder = i;
			}
		}
		
        return placeholder;
		
    }


    public String getSequence(int sequenceNumber) {
		int numStr = sequenceNumber;
		if(sequenceNumber == 0) {
			System.out.println("the firstString was returned and it is " + firstStr);
			return firstStr;
			break;
		}
		if(sequenceNumber == 1) {
			System.out.println("The second string was returned and it is " + secondStr);
			return secondStr;
			break;
		}
        
    }


    public int getNumErrors() {
		int numOfErrors = match(placeholder,0);
        return numOfErrors;
    }
}
