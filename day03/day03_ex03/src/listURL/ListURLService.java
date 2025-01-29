package listURL;

import model.OpenFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ListURLService {
    private static ListURL listURL;
    private static OpenFile file;

    public ListURLService(String pathFile) {
        file = new OpenFile(pathFile);
        listURL = new ListURL();
    }

    public void pushPullURL() {
        for (String urlStr : file.readURL()) {
            URL url = null;
            try {
                url = new URL(urlStr);
                URLConnection connection = url.openConnection();
                pushURL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Неверный URL");
            } catch (IOException e) {
                throw new RuntimeException("Не удалось установить соединение");
            }
        }
    }

    private static void pushURL(URL url) {
        listURL.pushURL(url);
    }

    public ArrayList<URL> getListURL() {
        return listURL.getListURL();
    }

}
