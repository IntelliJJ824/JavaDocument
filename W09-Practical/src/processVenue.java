import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class processVenue extends calculator {

    public processVenue(String cache, String query, String search) {
        super(cache, query, search);
    }

    public void processXML() {
        NodeList nodeList = doc.getElementsByTagName("venue");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println(nNode.getTextContent());
        }
    }
}
