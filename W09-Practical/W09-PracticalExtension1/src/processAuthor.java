import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.json.stream.JsonParser;
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
     * This method is to process the author option in json format.
     * @throws Exception no exists
     */
    public void processJson() throws Exception {
        String info = null;
        String name = null;
        while (parser.hasNext()) {
            JsonParser.Event e = parser.next();
            switch (e) {
                case KEY_NAME:
                    info = parser.getString();
                    break;
                case VALUE_STRING:
                    if (info.equals("author")) {
                        name = parser.getString();
                    }
                    if (info.equals("url") && name != null) {       //in order to avoid the next link after the info
                        String url = parser.getString();

                        if (!fileSearching) { //if there is no json file in this situation.
                            UrlWeb(url);
                        }

                        url = URLEncoder.encode(url, "UTF-8") + ".xml";
                        String restInfo = readUrlFile(url);
                        System.out.println(name + "-" + restInfo);
                        name = null;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This method is to create the xml file from the website, and the website does not provide such a json file.
     * @param link the specific location to search for the information.
     * @throws Exception not exists.
     */
    public void UrlWeb(String link) throws Exception{
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

    /**
     * This is to decide whether to search the file.
     */
    public void setFileSearching() {
        fileSearching = false;
    }
}
