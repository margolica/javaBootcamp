package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    private HashMap<String, Integer> dictionary;

    public Dictionary() {
        this.dictionary = new HashMap();
    }

    public void addWords(BufferedReader br) throws IOException {
        String str = null;
        while ((str = br.readLine()) != null) {
            String[] strArr = str.split(" ");
            for(int i = 0; i < strArr.length; i++ ) {
                if (!dictionary.containsKey(strArr[i]))
                    dictionary.put(strArr[i], dictionary.size());
            }
        }
    }

    public Integer contains(String word) {
        if (dictionary.containsKey(word))
            return dictionary.get(word);
        return null;
    }

    public int sizeDictionary() {
        return dictionary.size();
    }
}
