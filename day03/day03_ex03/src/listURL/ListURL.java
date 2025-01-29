package listURL;

import java.net.URL;
import java.util.ArrayList;

public class ListURL {
    private ArrayList<URL> listURL;

    public ListURL() {
        listURL = new ArrayList<URL>();
    }

    public void pushURL(URL url) {
        listURL.add(url);
    }

    public void popFrontURL() {
        listURL.remove(0);
    }

    public URL getFrontURL() {
        return listURL.getFirst();
    }

    public ArrayList<URL> getListURL() {
        return listURL;
    }
}
