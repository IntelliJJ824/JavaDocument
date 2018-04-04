import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.json.stream.JsonParser;

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
     * This method is for processing the json file.
     */
    public void processJson() {
        String info = null;
        while (parser.hasNext()) {
            JsonParser.Event e = parser.next();
            switch (e) {
                case KEY_NAME:
                    info = parser.getString();
                    break;
                case VALUE_STRING:
                    if (info.equals("venue")) {
                        System.out.println(parser.getString());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
