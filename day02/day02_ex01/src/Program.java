import service.CosineSimilarity;
import service.Dictionary;
import service.WordVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        try {
            dictionary.addWords(new BufferedReader(new FileReader(args[0])));
            dictionary.addWords(new BufferedReader(new FileReader(args[1])));
            WordVector vectorA = new WordVector(new BufferedReader(new FileReader(args[0])), dictionary);
            WordVector vectorB = new WordVector(new BufferedReader(new FileReader(args[0])), dictionary);
            CosineSimilarity cosineSimilarity = new CosineSimilarity();
            System.out.println(cosineSimilarity.calculate(vectorA, vectorB));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
