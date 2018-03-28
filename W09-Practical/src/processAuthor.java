import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

public class processAuthor extends calculator {

    public processAuthor(String cache, String query, String search) {
        super(cache, query, search);
    }

    public void processXML() throws Exception {
        NodeList nodeList = doc.getElementsByTagName("info");
        //to get all the nodes where name is author.
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            String name = nNode.getFirstChild().getTextContent();
            String url = nNode.getLastChild().getTextContent();

            // encode the url end with .xml
            url = URLEncoder.encode(url, "UTF-8") + ".xml";
            String restInfo = readUrlFile(url);
            System.out.println(name + " - " + restInfo);


        }
    }

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
}
