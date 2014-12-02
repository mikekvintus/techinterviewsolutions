package com.mk.techsolutions;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * Created by kvintus on 12/1/14.
 */
public class TransformOneWordToAnother {
    LinkedList<String> transform(String startWord, String stopWord, Set<String> dictionary) {
        Preconditions.checkArgument(startWord != null, "startWord was null");
        Preconditions.checkArgument(stopWord != null, "stopWord was null");
        Preconditions.checkArgument(startWord.length() == stopWord.length(), "Length of startWord (%s) was not the same as stopWord (%s)", startWord, stopWord);
        Preconditions.checkArgument(dictionary != null, "dictionary was null");
        Preconditions.checkArgument(dictionary.size() > 0, "dictionary was empty");
        startWord = startWord.toUpperCase();
        stopWord = stopWord.toUpperCase();
        Queue<String> actions = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        Map<String,String> backTrack = new TreeMap<String, String>();

        actions.add(startWord);
        visited.add(startWord);

        while (!actions.isEmpty()) {
            String word = actions.poll();
            for (String newWord : getWordsWithOneChange(word, dictionary)) {
                if (newWord.equals(stopWord)) {
                    LinkedList<String> ret = new LinkedList<String>();
                    ret.add(newWord);
                    while (word != null) {
                        ret.add(0, word);
                        word = backTrack.get(word);
                    }
                    return ret;
                }
                if (!visited.contains(newWord)) {
                    actions.add(newWord);
                    visited.add(newWord);
                    backTrack.put(newWord, word);
                }
            }
        }
        // return empty set instead of null to avoid NPEs
        return new LinkedList<String>();
    }

    Set<String> getWordsWithOneChange(String word, Set<String> dictionary) {
        Set<String> words = new TreeSet<String>();
        for (int i=0; i < word.length(); i++) {
            char[] wordArray = word.toCharArray();
            for (char ch='A'; ch <= 'Z'; ch++) {
                if (ch != word.charAt(i)) {
                    wordArray[i] = ch;
                    String newWord = new String(wordArray);
                    if (dictionary.contains(newWord)) {
                        words.add(newWord);
                    }
                }
            }
        }
        return words;
    }
}
