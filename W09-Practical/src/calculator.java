import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class calculator {
    String cache, query, search, linkLocation;
    File directory;
    File[] fiList;
    org.w3c.dom.Document doc;


    /**
     * This method is to store the path of the file and the query.
     * @param cache the user path of the file.
     * @param query the query string that user want to search.
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
//            System.out.println("no such a directory");
            judgement = false;
        } finally {
//            System.out.println("no such a file");
            return judgement;
        }
    }

    public void readTheFile(File file) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(file);
//        System.out.println(doc.getDocumentElement().getNodeName());
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
}
