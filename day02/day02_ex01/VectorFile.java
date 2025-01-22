package day_02.ex_01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
public class VectorFile
{
    Vector<Integer> vectorFile = new Vector<Integer>();

    public VectorFile()
    {}
    public void filling(Dictionary dictionary, String pathFile)
    {
        int sizeDictionary =  dictionary.getDictionary().size();
        for (int i = 0; i < sizeDictionary; ++i)
            vectorFile.add((int)0);
        try (FileReader fio = new FileReader(pathFile))
        {

            BufferedReader fioBuffer = new BufferedReader(fio);
            String str = null;
            while ((str = fioBuffer.readLine()) != null)
            {
                String[] arrayStr = null;
                arrayStr = fioBuffer.readLine().split(" ");
                for (int i = arrayStr.length; i > 0; --i)
                    if (dictionary.getDictionary().contains(arrayStr[i]))
                        vectorFile.set(i, vectorFile.get(i) + 1);
            }

        } catch (FileNotFoundException ex)
        {
            System.out.println("Файл не найден");
        } catch (IOException ex)
        {
            System.out.println("Произошла ошибка ввода вывода");
        }

    }
}
