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

    private static final String IMAGE_HOME = "/Users/PLACEHOLDER"; // Used in commented out code near bottom
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


    /*
    // Code in this block comment is a potential solution for downloading images given a URL (found on StackOverflow)
    // I haven't edited any of the code yet to make it work with the program because I don't know how it works yet

    public String downloadImages(String imageUrl, String fileName, String relativePath) {
        String imagePath = null;
        try {
            byte[] bytes = Jsoup.connect(imageUrl).ignoreContentType(true).execute().bodyAsBytes();
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            String rootTargetDirectory = IMAGE_HOME + "/"+relativePath;
            imagePath = rootTargetDirectory + "/"+fileName;
            saveByteBufferImage(buffer, rootTargetDirectory, fileName);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return imagePath;
    }

    public static void saveByteBufferImage(ByteBuffer imageDataBytes, String rootTargetDirectory, String savedFileName) {
        String uploadInputFile = rootTargetDirectory + "/"+savedFileName;

        File rootTargetDir = new File(rootTargetDirectory);
        if (!rootTargetDir.exists()) {
            boolean created = rootTargetDir.mkdirs();
            if (!created) {
                System.out.println("Error while creating directory for location- "+rootTargetDirectory);
            }
        }
        String[] fileNameParts = savedFileName.split("\\.");
        String format = fileNameParts[fileNameParts.length-1];

        File file = new File(uploadInputFile);
        BufferedImage bufferedImage;

        InputStream in = new ByteArrayInputStream(imageDataBytes.array());
        try {
            bufferedImage = ImageIO.read(in);
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
}
