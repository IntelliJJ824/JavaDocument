
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This class is for judgement whether the file is exist, and read the file to make it become document.
 */
public class calculator {
    protected String cache, query, search, linkLocation;
    protected File directory;
    protected File[] fiList;
    protected org.w3c.dom.Document doc;

    /**
     * This method is to store the path of the file and the query.
     * @param cache the user path of the file.
     * @param query the query string that user want to search.
     * @param search the query type that user enter.
     */
    public calculator(String cache, String query, String search) {
        this.cache = cache;
        this.query = query;
        this.search = search;
    }

    /**
     * This method is to judge whether there is a file in the cache.
     * @return true presents exist, otherwise is false.
     */
    public boolean judgeTheFile() {
        String link = linkFinder();
        boolean judgement = false;

        try {
            directory = new File(cache);
            fiList = directory.listFiles();
            //to list all the files in a specific directory
            for (int i = 0; i < fiList.length; i++) {
                if (fiList[i].isFile() && fiList[i].getName().equals(link)) {
                    readTheFile(fiList[i]); //if there is a file, read the file.
                    judgement = true;
                    return judgement;
                }
            }
        } catch (NullPointerException e) {
            judgement = false;
        } finally {
            return judgement;
        }
    }

    /**
     * This method is to process the file from the given directory and generate to a doc.
     * @param file
     * @throws Exception all the exceptions are handled
     */
    public void readTheFile(File file) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(file);
    }

    /**
     * This method is to generate the link.
     * @return the link that user might store in the local computer.
     */
    public String linkFinder() {
        String link = "http%3A%2F%2Fdblp.org%2Fsearch%2F" + search + "%2Fapi%3Fformat%3Dxml%26c%3D0%26h%3D40%26q%3D";
        String[] wordsList = query.split("\\s");

        for (int i = 0; i < wordsList.length; i++) {
               if (i == wordsList.length - 1) {     // if this is the last element of the array, the link is finished.
                   link = link + wordsList[i];
               } else {
                   wordsList[i] = wordsList[i] + "%2B";
                   link = link + wordsList[i];
               }
        }
        linkLocation = cache + "/" + link;
        return link;
    }

    /**
     * This method is to generate the web link.
     * @return the web link.
     */
    public String webLink() {
        String link = "http://dblp.org/search/" + search + "/api?format=xml&c=0&h=40&q=";
        String[] wordsList = query.split("\\s");

        for (int i = 0; i < wordsList.length; i++) {
            if (i == wordsList.length - 1) {
                link = link + wordsList[i];
            } else {
                wordsList[i] = wordsList[i] + "+";
                link = link + wordsList[i];
            }
        }
        return link;
    }

    /**
     * This method is to input the file which is not existed in the given directory.
     * @throws Exception not exists.
     */
    public void webReader() throws Exception{
        String link = webLink();
        String fileName = URLEncoder.encode(link, "UTF-8");

        URL url = new URL(link);
        InputStream response = url.openStream();
        String path = cache + "/" + fileName;
        OutputStream result = new FileOutputStream(new File(path));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = response.read(bytes)) != -1) {
            result.write(bytes, 0, read);
        }

        response.close();
        result.close();

        // This is to read the file, after the file is created.
        File file = new File(path);
        readTheFile(file);
    }
}
