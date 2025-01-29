package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DetectionSignatureFile
{
    public DetectionSignatureFile() {};
    public void detectionSignatureFile(List<String> signatureList, Scanner input)
    {
        String pathFile, str = null;
        byte[] firstByteFile = new byte[8];
        FileOutputStream fout = null;
        try
        {
            fout = new FileOutputStream("result");
            while (!(pathFile = input.nextLine()).equals("42"))
            {
                try (FileInputStream file = new FileInputStream(pathFile))
                {
                    file.read(firstByteFile, 0, 8);
                    String signatureFile = bytesToHex(firstByteFile);
                    int index = -1;
                    if ((index = signatureList.indexOf(signatureFile)) > -1)
                        writeInFout(fout, signatureList.get(index - 1).toCharArray());
                } catch (FileNotFoundException ex) {
                    System.out.println("Файл не найден1");
                } catch (IOException ex) {
                    System.out.println("Произошла ошибка ввода вывода");
                }
            }
        } catch (IOException ex) {
            System.out.println("Произошла ошибка ввода вывода");
        }
        finally {
            closeStreamFile(fout);
        }
    }

    private static void writeInFout(FileOutputStream fout, char[] str) {
        try {
            for (char c : str) fout.write(c);
            fout.write('\n');
        } catch (IOException ex) {
            System.out.println("Произошла ошибка ввода вывода");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private static void closeStreamFile(FileOutputStream file_input)
    {
        try
        {
            if (file_input != null) file_input.close();
        } catch (IOException ex){
            System.out.println("Произошла ошибка закрытия файла");
        }
    }
}
