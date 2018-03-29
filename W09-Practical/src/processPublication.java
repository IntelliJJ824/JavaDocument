import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * This class is to process the searching type application.
 */
public class processPublication extends calculator {
    /**
     * This method is to receive the parameters from the user's input.
     * @param cache the location of the file.
     * @param query the query string that user want to loop up.
     * @param search the type that user want to search.
     */
    public processPublication(String cache, String query, String search) {
        super(cache, query, search);
    }

    /**
     * This method is to process the file in the file for publication.
     */
    public void processXML() {
        NodeList nodeList = doc.getElementsByTagName("title");
        //to get all the nodes where name is title.
        for (int i = 0; i < nodeList.getLength(); i++) {
            int authorNo;
            Node nNode = nodeList.item(i);
            String title = nNode.getTextContent();

            //if there is no author in the publication
            try {
                authorNo = nNode.getPreviousSibling().getChildNodes().getLength();
            } catch (NullPointerException e) {
                authorNo = 0;
            }
            printOutPublication(title, authorNo);
        }
    }

    /**
     * The printing format for the title selection.
     * @param title the publication name.
     * @param authorNo the number of authors for this publication.
     */
    public void printOutPublication(String title, int authorNo) {
        System.out.println(title + " (number of authors: " + authorNo + ")");
    }

}
