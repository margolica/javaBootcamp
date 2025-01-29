package ui;

import listURL.ListURLService;
import model.Download;

public class Menu {

    private static ListURLService listURLService;
    private static String pathFile;
    private static int countThread;

    public Menu(String pathFile, int countThread) {
        this.pathFile = pathFile;
        this.countThread = countThread;
    }

    public void start(String pathFile) {
        readURL();
        downloadURL(listURLService, countThread);
    }

    private void readURL() {
        listURLService = new ListURLService(this.pathFile);
        listURLService.pushPullURL();
    }

    private static void downloadURL(ListURLService listURLService, int countThread) {
        Download download = new Download(listURLService.getListURL(), countThread);
    }
}
