import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * This class is to process the searching type Venue.
 */
public class processVenue extends calculator {
    /**
     * This is to pass the value from the users' input/
     * @param cache the path of the file.
     * @param query the query string user enters.
     * @param search the type that user want to search.
     */
    public processVenue(String cache, String query, String search) {
        super(cache, query, search);
    }

    /**
     * This method is to print the venue for the searching.
     */
    public void processXML() {
        NodeList nodeList = doc.getElementsByTagName("venue");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println(nNode.getTextContent());
        }
    }
}
