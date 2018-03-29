import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

public class processAuthor extends calculator {
    private boolean fileSearching = true;

    public processAuthor(String cache, String query, String search) {
        super(cache, query, search);
    }

    /**
     * This method is to find the summary of all the authors.
     * @throws Exception all the Exceptions are not existed
     */
    public void processXML() throws Exception {
        NodeList nodeList = doc.getElementsByTagName("info");
        //to get all the nodes where name is author.
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            String name = nNode.getFirstChild().getTextContent();
            String url = nNode.getLastChild().getTextContent();

            if(!fileSearching) { //if there is not a file in the directory before searching.
                 UrlWeb(url);   //This is for creating the file.
            }

            // encode the url end with .xml
            url = URLEncoder.encode(url, "UTF-8") + ".xml";
            String restInfo = readUrlFile(url);
            System.out.println(name + " - " + restInfo);

        }
    }

    public void UrlWeb (String link) throws Exception{
        String fileName = URLEncoder.encode(link, "UTF-8") + ".xml";
        URL urlAuthor = new URL(link+".xml");

        InputStream info = urlAuthor.openStream();
        String path = cache + "/" + fileName;
        OutputStream result = new FileOutputStream(new File(path));

        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = info.read(bytes)) != -1) {
            result.write(bytes, 0, read);
        }

        info.close();
        result.close();
    }

    /**
     * This method is based on the first summary, requesting further information for each author.
     * @param url The url link for each author.
     * @return the further information for each user.
     * @throws Exception no exceptions.
     */
    public String readUrlFile(String url) throws Exception {
        String fileLocation = cache + "/" + url;
        File urlFile = new File(fileLocation);
        String coAuthorseNo;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        org.w3c.dom.Document urlDoc = db.parse(urlFile);

        String publications = urlDoc.getDocumentElement().getAttribute("n");

        try {
            NodeList coNode = urlDoc.getElementsByTagName("coauthors");
            Node nNode = coNode.item(0);
            Element eElement = (Element) nNode;
            coAuthorseNo = eElement.getAttribute("n");
        } catch (NullPointerException e) {
            coAuthorseNo = "0";
        }

        String info = publications + " publications with " + coAuthorseNo + " co-authors.";
        return info;
    }

    public void setFileSearching() {
        fileSearching = false;
    }
}
