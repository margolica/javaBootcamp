package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordVector {
    int[] vector;

    public WordVector(BufferedReader br, Dictionary dictionary) throws IOException {
        vector = new int[dictionary.sizeDictionary()];
        createVector(this.vector, dictionary, br);
    }

    static void createVector(int[] vector, Dictionary dictionary, BufferedReader br) throws IOException {
        String str = null;
        while ((str = br.readLine()) != null) {
            String[] strArr = str.split(" ");
            for(int i = 0; i < strArr.length; i++) {
                if (dictionary.contains(strArr[i]) != null)
                    vector[dictionary.contains(strArr[i]).intValue()] += 1;
            }
        }
    }

    public int[] getVector() {
        return vector;
    }
}
