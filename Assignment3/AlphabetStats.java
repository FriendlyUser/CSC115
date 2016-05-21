/**
 * AlphabetStats.java
 *
 * Provided as part of the code for Assignment #3,
 * CSC 115, Summer 2015 (UVic).
 *
 * Author: Mike Zastre
 */


public class AlphabetStats {
    final static int ALPHABET_SIZE = 26;
    private int freqs[];

    /**
     * AlphabetStats is initialized with the phrase for
     * which letter-frequency stats are to be maintained.
     */
    public AlphabetStats(String phrase) {
        phrase = phrase.toLowerCase();

        freqs = new int[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            freqs[i] = 0;
        }

        for (int i = 0; i < phrase.length(); i++) {
            int index = phrase.charAt(i) - 'a';
            if (index < 0 || index > ALPHABET_SIZE) {
                continue;
            }
            freqs[index]++;
        }
    }


    /** 
     * If all of the letter frequencies in the current
     * instance are greater than or equal to the
     * letter frequencies in the parameter as, then the
     * instance is said to contain as. Otherwise false
     * returned.
     *
     * @param as An instance of alphabet stats to be compared
     * with the current instance.
     * @return true if the current instances has all of the
     * letters in the passed instance.
     */
    public boolean contains(AlphabetStats as) {
        for (int index = 0; index < ALPHABET_SIZE; index++) {
            if (this.freqs[index] < as.freqs[index]) {
                return false;
            }
        }
        return true;
    }


    /** 
     * The letter-frequencies of the current instance are
     * decremented by the values of letter frequencies
     * in the passed instance. It is possible that after
     * this operation the current instance has negative
     * letter frequencies, in which case false is returned.
     * (In normal cases we would expect subtract() to be
     * called only when the current instances actually
     * contains the passed instance.) The passed instance
     * is not modified.
     *
     * @param as An instance whose letter frequencies will
     * be used to reduce letter frequencies of the current
     * instance.
     * @return false if the method finishes with the
     * current instance having negative letter frequencies;
     * false otherwise.
     */
    public boolean subtract(AlphabetStats as) {
        boolean result = true;
        for (int index = 0; index < ALPHABET_SIZE; index++) {
            this.freqs[index] -= as.freqs[index];
            if (this.freqs[index] < 0) {
                result = false;
            }
        }
        return result; 
    }


    /**
     * The letter frequencies of the current instance are
     * incremented by the letter frequencies of the
     * passed instance. The passed instance is not
     * modified.
     *
     * @param as An instance whose letter frequencies will
     * be added to the letter frequencies of the current
     * instance.
     */
   public void add(AlphabetStats as) {
        for (int index = 0; index < ALPHABET_SIZE; index++) {
            this.freqs[index] += as.freqs[index];
        }
    }


    /**
     * @return true if all letter frequencies of the 
     * current instance are equal to zero; false otherwise.
     */
    public boolean isEmpty() {
        boolean result = true;

        for (int index = 0; index < ALPHABET_SIZE; index++) {
            result &= (this.freqs[index] == 0);
        }

        return result;
    }


    public String toString() {
        String result = "";
        for (int index = 0; index < ALPHABET_SIZE; index++) {
            result += Character.toString((char)(index + 'a'));
            result += ": ";
            result += (freqs[index] == 0 ? "--" : freqs[index]);
            result += "\n";
        }

        return result;
    }


    public static void main(String args[]) {
        AlphabetStats as1;
        AlphabetStats as2;

        String test1 = "Test 1 'aaabbb' contains 'aaabb'";
        as1 = new AlphabetStats("aaabbb");
        as2 = new AlphabetStats("aaabb");
        System.out.println(test1 + ": " +
            (as1.contains(as2) ? "passed" : "FAILED")
        );

        String test2 = "Test 2 'aaabb' does not contains 'aaabbb'";
        as1 = new AlphabetStats("aaabb");
        as2 = new AlphabetStats("aaabbb");
        System.out.println(test2 + ":  " +
            (!as1.contains(as2) ? "passed" : "FAILED")
        );


        String test3 = "Test 3 'aaabbb' subtracted from 'aaabbb' isEmpty";
        as1 = new AlphabetStats("aaabbb");
        as2 = new AlphabetStats("aaabbb");
        as1.subtract(as2);
        System.out.println(test3 + ":  " +
            (as1.isEmpty()? "passed" : "FAILED")
        );

        
    }
}
