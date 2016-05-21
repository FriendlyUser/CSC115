/*  David Li V00818631 June 21,2015
*	Anagrammer.java
*	This program requires a dictionary, phrase, and specified maximum amount of words in order to find anagrams.
*	The generate function calls the recursive findAnagram function.
*	The findAnagram function will find the anagrams and print them out
*
*/
public class Anagrammer {
	
	private String dict[];
	public String phrase;
	private int maxWords; 
	WordList words;
	AlphabetStats phraseStats;
	
	//constructor
	public Anagrammer(String dictionary[], String phrase, int maxWords) {
		this.dict = dictionary;
		this.phrase = phrase;
		this.maxWords = maxWords;
	}
	//this function will use findAnagram to recursively generate words
	public void generate() {
		
		//create a new linked list to store all of the words;
		words = new WordList();
		phraseStats = new AlphabetStats(phrase);
		//find all the possible words in the phrase
		findAnagram(words,phraseStats);
		System.out.println();

	}
	
	private int fact(int n) {
		
		// Base Case: 
		//    If n <= 1 then n! = 1.
		if (n <= 1) {
			return 1;
		}
		// Recursive Case:  
		//    If n > 1 then n! = n * (n-1)!
		else {
			return n * fact(n-1);
		}
    }
	
	//this function will add words to the wordlist and remove their letters from phraseStats
	private void findAnagram(WordList words, AlphabetStats phraseStats) {
		//base cases
		if(maxWords ==0) {
			return;
		}
		//another base case
		if(phraseStats.isEmpty()){
			words.printList();
			System.out.println();
			return;
		}
		//if the number of words reachs maxWords print out the list
		if(words.getLength() == maxWords && phraseStats.isEmpty()) {
			words.printList();
			System.out.println();
			return;
		}
		AlphabetStats word;
		if(words.getLength() < maxWords) {
			//search through the dictionary
			for(int i =0; i <dict.length;i++){
				word = new AlphabetStats(dict[i]);	
				//if the word isn't already in the wordlist and the phrase still has enough letters
				//then put the word into the list
				if(phraseStats.contains(word) && !(words.contains(dict[i]))) {
					words.insertHead(dict[i]);
					phraseStats.subtract(word);
					findAnagram(words,phraseStats);
					//if a new word isn't added go backtrack
					word = new AlphabetStats(words.removeHead());
					//add the letters back in
					phraseStats.add(word);
				}
			}
		}
	}
	
	//uses a given list, and checks in each entry in the linked list 
	 private static boolean compareAndReport(WordList given)
    {
        boolean passed = true;
        String result;

        for (int i = 0; i < given.getLength(); i++) {
            result = given.retrieve(i);
			//if the list contains the given string 
            if (given.contains(result)) {
                passed = true;
            }
			//the test has failed
			else {
				passed = false;
			}
        }
        return passed;
    }
	//this method will reverse a given string
	private static String reverseString(String str) {
		if ((null == str) || (str.length() <= 1)) {
			return str;
		}
		return reverseString(str.substring(1)) + str.charAt(0);
	}
	
	//tests 
	public static void main(String[] args) {

		// Test behavior of findAnagram if maxWords is 0
		String testDict[] = {"ab,a,abb,aa,abbb"};
		boolean passed = true;
		
		Anagrammer a = new Anagrammer(testDict, "yoyo", 0);
		a.generate();
		
		//if the list is empty no words were added
		if(a.words.getLength() == 0) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 1 (getLength = 0): " +
            (passed ? "passed" : "FAILED"));
			
		// Test behavior of findAnagram if maxWords is 0
		a = new Anagrammer(testDict,"yoyo",0);
		
		if(a.maxWords == 0) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 2 (maxWords = 0): " +
            (passed ? "passed" : "FAILED"));
			
		//Test behavior of findAnagram if the phraseStats is empty
		a = new Anagrammer(testDict,"",1);
		a.generate();
		if(a.phraseStats.isEmpty()) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 3 (phrase is empty string): " +
            (passed ? "passed" : "FAILED"));
			
		//Test behavior of findAnagram with dictionary of one matching word and phrase
		String test4Dict[] = {"yo"};
		a = new Anagrammer(test4Dict,"yo",1);
		a.generate();
		if(compareAndReport(a.words)) {
			passed = true;
		}
		else {
			passed = false;
		} 
		 System.out.println("Test 4 (findAnagram): " +
            (passed ? "passed" : "FAILED"));
		
		//Test behavior of findAnagram with dictionary of one three matching words and phrase
		String test5Dict[] = {"a", "ab", "bbb"};
		a = new Anagrammer(test5Dict,"bbbbaa",3);
		a.generate();
		if(compareAndReport(a.words)) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 5 (findAnagram): " +
            (passed ? "passed" : "FAILED"));
		
		//Test behavior of findAnagram with dictionary of one three matching words and phrase
		String test6Dict[] = {"hello","goodbye","fun","personal","hobbies"};
		a = new Anagrammer(test6Dict,reverseString("personalhobbies"),2);
		a.generate();
		if(compareAndReport(a.words)) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 6 (findAnagram): " +
            (passed ? "passed" : "FAILED"));
		
		//Test behavior of findAnagram with 5 dictionary words and phrase
		String test7Dict[] = {"abba","good","miss","evil","pokemon"};
		a = new Anagrammer(test7Dict,reverseString("goodmisspokemon"),4);
		a.generate();
		if(compareAndReport(a.words)) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 7 (findAnagram): " +
            (passed ? "passed" : "FAILED"));
			
		//Test behavior of findAnagram with 9 dictionary words and phrase
		String test8Dict[] = {"air","bike","dues","duet","run","unjust","funn","Computer Science"};
		a = new Anagrammer(test8Dict,reverseString("JustunAirRun"),3);
		a.generate();
		if(compareAndReport(a.words)) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 8 (findAnagram): " +
            (passed ? "passed" : "FAILED"));
			
		//Test behavior of findAnagram with 9 dictionary words and the maximum words is 4
		String test9Dict[] = {"air","bike","dues","duet","run","unjust","funn","Computer Science"};
		a = new Anagrammer(test9Dict,reverseString("duesduetrununjust"),4);
		a.generate();
		if(compareAndReport(a.words)) {
			passed = true;
		}
		else {
			passed = false;
		}
		 System.out.println("Test 9 (findAnagram): " +
            (passed ? "passed" : "FAILED"));
	}
}
