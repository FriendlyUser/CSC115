/*
 * Provided for Assignment #3, CSC 115, Summer 2015, UVic
 * Author: Michael Zastre
 *
 * This file is not to be modified as part of the
 * assignment.
 */

import java.io.*;
import java.util.*;

public class A3driver {
    public static void main(String args[]) {
        String dictFile = null;
        String textPhrase = null;
        int    maxWords = 10;

        for (int i=0; i < args.length; i++) {
            if (args[i].startsWith("--dict=")) {
                dictFile = args[i].substring(args[i].indexOf("=")+1);
            } else if (args[i].startsWith("--phrase=")) {
                textPhrase = args[i].substring(args[i].indexOf("=")+1);
            } else if (args[i].startsWith("--maxwords=")) {
                String maxWordsString =
                    args[i].substring(args[i].indexOf("=")+1);
                maxWords = Integer.parseInt(maxWordsString);
            }
        }

        if (dictFile == null) {
            System.out.println("A dictionary file (text) must be given.");
        }
        if (textPhrase == null) {
            System.out.println("A text phrase must be given.");
        }

        if (dictFile == null || textPhrase == null) {
            System.exit(1);
        }

        String dictionary[] = readDictionary(dictFile);
        if (dictionary.length == 0) {
            System.out.println("(No words in dictionary)");
            System.exit(1);
        }

        Anagrammer a = new Anagrammer(dictionary, textPhrase, maxWords);
        a.generate();
    }


    // We do have a minimum word length -- but we'll add in
    // "i" and "a".
    //
    public static String [] readDictionary(String filename) {
        ArrayList<String> words = new ArrayList<String>();

        try {
            File file = new File(filename);
            Scanner stream = new Scanner(file);
            while (stream.hasNext()) {
                String word = stream.nextLine();
                if (word.length() == 1) {
                    continue;
                }
                words.add(word);
            }
            words.add("i");
            words.add("a");
        } catch (IOException e) {
            System.out.println("Could not open file named " + filename);
        }

        String[] result = words.toArray(new String[words.size()]);

        return result;
    }
}
