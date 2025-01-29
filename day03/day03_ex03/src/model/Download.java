package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;

public class Download {
    private static ArrayList<URL> listURL;

    public Download(ArrayList<URL> listURL, int countThread) {
        this.listURL = listURL;
        TaskDownlandFile taskOne = new TaskDownlandFile();
        startThread(taskOne, countThread);
    }

    private static void startThread(final Runnable runnable, final int countThread) {
        for (int i = 0; i < countThread; i++) {
            final Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    private static final class TaskDownlandFile implements Runnable {
        @Override
        public void run() {
            while (!listURL.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " start download file number");
                URL url;
                synchronized (listURL) {
                    url = listURL.get(0);
                    listURL.remove(0);
                }
                try {
                    InputStream inputStream = url.openStream();
                    String urlPath = url.getPath();
                    String fileName = urlPath.substring(urlPath.lastIndexOf('/') + 1);
                    Files.copy(inputStream, new File("day03/day03_ex03/src/materials/" + fileName).toPath());
                    System.out.println(Thread.currentThread().getName() + " finish download file number");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
