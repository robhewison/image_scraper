package sample;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

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
            ArrayList<String> imgURLs = new ArrayList<>();
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
            ArrayList<String> fail = new ArrayList<>();
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

    // one URL
    public void downloadImages(String imageUrl, String fileName) throws IOException {
        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));

        for ( int i; (i = in.read()) != -1; ) {
            out.write(i);
        }
        in.close();
        out.close();
    }

    // array of URLs with fileName
    public void downloadImages(String[] imageUrl, String fileName) throws IOException {
        for(int i = 0; i < imageUrl.length; i++) {
            downloadImages(imageUrl[i], fileName);// Either add in a random string generator or add +1 to every iteration of the fileName
            fileName += "1";
        }
    }

    // array of URLs with random fileName
    public void downloadImages(String[] imageUrl) throws IOException {
        for(int i = 0; i < imageUrl.length; i++) {
            downloadImages(imageUrl[i], randomStringGenerator(9));
        }
    }

    public String randomStringGenerator(int length) {
        String alphabetAndNums = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder completeString = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            completeString.append(alphabetAndNums.charAt(random.nextInt(alphabetAndNums.length())));
        }
        return completeString.toString();
    }

}
