package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadSignaturesFile
{
    private static String pathFileSignatures = "day02/day02_ex00/resources/signatures.txt";
    private List<String> signatureList = null;

    public ReadSignaturesFile() {
        signatureList = new ArrayList<String>();
    }
     public void readSignaturesFile()
    {
        String str = null;
        try (FileReader fileSignatures = new FileReader(pathFileSignatures))
        {
            BufferedReader fileSignaturesLine = new BufferedReader(fileSignatures);
            while ((str = fileSignaturesLine.readLine()) != null)
            {
                String[] splitStr = str.split(", ");
                signatureList.add(splitStr[0]);
                signatureList.add(splitStr[1]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (IOException ex) {
            System.out.println("Произошла ошибка ввода вывода");
        }
    }

    public List<String> getSignatureList()
    {
        return signatureList;
    }
}
