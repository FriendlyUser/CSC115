/**
 * David Li, V00818631, May 16
 * BasicAligner.java 
 * 
 * This aligner determines where the best position to align the strings are, the number of mismatched characters and the number of errors in the alignment.
 * 
 */

import java.io.*;
public class BasicAligner implements Aligner {
	
	//attributes
	String firstString;
	String secondString;
	int placeholder; ////records the insert position of the new string
	int numLowMismatch; //records the lowest number of mismatched characters between the first and second string
	char[] firstCharStr;
	char[] secondCharStr;
	
	//constructor
    public BasicAligner(String[] sequence) { 
		
		//String firstString = big;
		//String secondString = small;
		
		firstString = sequence[0]; 
		secondString = sequence[1];
		
		//output what the strings are 
		//System.out.println("The values of the firstString are " + firstString);
		//System.out.println("The values of the secondString are " + secondString);
		
		//convert the strings into arrays
		firstCharStr = firstString.toCharArray();
		secondCharStr = secondString.toCharArray();

	}


	
	public static void main(String args[]) {
	
	}
	
	//returns the number of mismatched characters
	private int matchAt(int firstI,int secondI) {
		int match = 0;
	
		for(int i=0; i < secondString.length(); i++) {
			if((firstCharStr[firstI + i] != secondCharStr[secondI + i])) {
				match = ++match;
			}
		}
		return match;
	}
	
    public void performAlignment() {
		numLowMismatch = secondString.length();
		for( int i = 0; i < firstString.length() - secondString.length(); i++) {
			if (numLowMismatch > matchAt(i,0)){
				numLowMismatch = matchAt(i,0);
			}
		}
	}

	//first string be index 0, and second string will be represented by index 1 
    public int getOffset(int sequenceNumber) {
		
		placeholder = 0; //records the insert position of the new string
		
		//checks the first string
		if(sequenceNumber == 0) {
			for(int i =0; i <= firstCharStr.length - secondCharStr.length; i++) {
			
			//checks for the current index has the smallest amount of mismatched characters
				if(matchAt(0,i) == numLowMismatch) {
					placeholder = i;
				}
			}
		}
		//checks the second strings
		if(sequenceNumber == 1) {
			for(int i =0; i <= firstCharStr.length - secondCharStr.length; i++) {
				
				//checks for the current index has the smallest amount of mismatched characters
				if(matchAt(i,0) == numLowMismatch) {
					placeholder = i;
				}
			}
		}
		
        return placeholder;
		
    }


    public String getSequence(int sequenceNumber) {
		int numStr = sequenceNumber;
		
		if(numStr == 0) {
			
			
			//returns the bigger String
			return firstString;
		}
		if(numStr == 1) {
			
			//returns the smaller string
			return secondString;
		}
        return "Neither of the strings were returned!";
    }

	//returns the number of errors in the best alignment
    public int getNumErrors() {
		
		for(int i =0; i <= firstCharStr.length - secondCharStr.length; i++) {
			
		//checks for the current index has the smallest amount of mismatched characters
			if(matchAt(i,0) == numLowMismatch) {
				placeholder = i;
			}
		}
		int numOfErrors = matchAt(placeholder,0);
        return numOfErrors;
    }
}
