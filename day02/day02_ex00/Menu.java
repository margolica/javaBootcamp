package day_02.ex_00;

import java.nio.file.LinkPermission;
import java.util.List;
import java.util.Scanner;

public class Menu
{
    private ReadSignaturesFile readSignaturesFile = null;
    private DetectionSignatureFile detectionFile = null;
    Scanner in;

    public Menu()
    {
        readSignaturesFile = new ReadSignaturesFile();
        detectionFile = new DetectionSignatureFile();
        in =  new Scanner(System.in);
    }

    public List<String> getListSignatures()
    {
        readSignaturesFile.readSignaturesFile();
        return readSignaturesFile.getSignatureList();
    }

    public void Start()
    {
        detectionFile.detectionSignatureFile(getListSignatures(), in);
    }

}
