package model;

import userException.EmptyFileException;

import java.io.*;
import java.util.ArrayList;

public class OpenFile {
    private static ArrayList<String> ListString;
    private static String pathFile;

    public OpenFile(String pathFile) {
        OpenFile.pathFile = pathFile;
    }

    public ArrayList<String> readURL() {
        try (FileReader fileWithURL = new FileReader(pathFile)) {
            ListString = new ArrayList<>();
            String url = null;
            BufferedReader lineURL = new BufferedReader(fileWithURL);
            while ((url = lineURL.readLine()) != null)
                ListString.add(url);
            if (ListString.isEmpty())
                throw new EmptyFileException("Файл пустой");
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (IOException ex) {
            System.out.println("Произошла ошибка ввода вывода");
        }
        return ListString;
    }
}
