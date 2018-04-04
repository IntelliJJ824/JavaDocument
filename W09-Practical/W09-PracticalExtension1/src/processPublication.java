import javax.json.stream.JsonParser;

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
     * This is to process publication in json format.
     */
    public void processJson() {
        String info = null;
        int numberAuthors = 0;
        while (parser.hasNext()) {
            JsonParser.Event e = parser.next();
            switch (e) {
                case KEY_NAME:
                    info = parser.getString();
                    break;
                case VALUE_STRING:
                    if (info.equals("author")) {
                        numberAuthors++;
                    }
                    if (info.equals("title")) {
                        printOutPublication(parser.getString(), numberAuthors);
                        numberAuthors = 0;
                    }
                    break;
                default:
                    break;
            }
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
