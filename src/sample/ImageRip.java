package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class ImageRip {

    public String website;

    public ImageRip(String website) {
        setWebsite(website);
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public ArrayList<String> getImages() {
        String website = getWebsite();
        try {
            Document doc = Jsoup.connect(website).get();
            Elements img = doc.getElementsByTag("img");
            ArrayList<String> imgURLs = new ArrayList<String>();
            for (Element el : img) {
                String src = el.absUrl("src");
                if(src.equals("")) {

                }
                else {
                    imgURLs.add(src);
                }
            }

            return imgURLs;
        }
        catch(IOException ioe) {
            ArrayList<String> fail = new ArrayList<String>();
            return fail;
        }
    }

    public String getImageAmount() {
        String website = getWebsite();
        try {
            Document doc = Jsoup.connect(website).get();
            Elements img = doc.getElementsByTag("img");
            ArrayList<String> imgURLs = new ArrayList<String>();
            for (Element el : img) {
                String src = el.absUrl("src");
                if(src.equals("")) {

                }
                else {
                    imgURLs.add(src);
                }
            }

            return imgURLs.size() + " IMAGES FOUND";

        }
        catch(IOException ioe) {
            return "exception";
        }
    }

    public void downloadImages(ArrayList<String> toDownload) {

    }




}
