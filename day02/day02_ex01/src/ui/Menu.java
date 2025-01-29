package ui;

import service.Dictionary;
import service.WordVector;

import java.io.*;

public class Menu {

    public Dictionary createDictionary(String fileA, String fileB) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.addWords(new BufferedReader(new FileReader(fileA)));
        dictionary.addWords(new BufferedReader(new FileReader(fileB)));
        return dictionary;
    }

    public WordVector createVector(Dictionary dictionary, String file) throws IOException {
        return new WordVector(new BufferedReader(new FileReader(file)), dictionary);
    }
}
