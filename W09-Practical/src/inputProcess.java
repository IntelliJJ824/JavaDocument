import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Scanner;

public class inputProcess {
    String search, query, cache;

    public inputProcess(String search, String query, String cache) throws IOException {
        this.search = search;
        this.query = query;
        this.cache = cache;
        searchType();
    }

    public void searchType() throws IOException {
        switch (search) {
            case "author":
                System.out.println("you are searching author");
//                String decoderUrl = URLDecoder.decode("http%3A%2F%2Fdblp.org%2Fpid%2F14%2F891.xml","UTF-8");
//                System.out.println(decoderUrl);
                break;

            case "publication":
                System.out.println("you are searching publication");
                break;

            case "venue":
                System.out.println("searching venue");

                break;

            default:
                throw new IOException("Searching Error Input");
        }
    }



//    static final String urlStr = "http://dblp.org/search/author/api?q=tony";
//    public void printDetails() throws IOException {
//        URL url = new URL(urlStr);
//        InputStream response = url.openStream();
//        Scanner scanner = new Scanner(response);
//        while (scanner.hasNext()) {
//            System.out.print(scanner.next() + " ");
//        }
//    }

}
