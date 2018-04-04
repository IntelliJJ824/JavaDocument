import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class describeJson {
    private String query;

    public describeJson(String query) {
        this.query = query;
        readWebpage();
    }

    public String webLink() {
        String link = "https://api.duckduckgo.com/?q=";
        String[] wordsList = query.split("\\s");

        for (int i = 0; i < wordsList.length; i++) {
            if (i == wordsList.length - 1) {
                link = link + wordsList[i];
            } else {
                wordsList[i] = wordsList[i] + "+";
                link = link + wordsList[i];
            }
        }

        link = link + "&format=json&pretty=1";

        return link; //This is the full link of the query
    }

    public void readWebpage() {
        String link = webLink();
        System.out.println(link);

        URL url = null;
        InputStreamReader response = null;
        try {
            url = new URL(link);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("User-Agent", "curl/7.37.0");

            response = new InputStreamReader(connection.getInputStream());
            JsonParser parser = Json.createParser(response);
            String info = "begin";
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();

                if (e == JsonParser.Event.KEY_NAME) {
                    info = parser.getString();
                }

                if (e == JsonParser.Event.VALUE_STRING) {
                    switch(info) {
                        case ("Text"):
                            System.out.println("<Text description>: " + parser.getString());
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.getStackTrace();
        } catch (IOException f) {
            f.getStackTrace();
        } finally {
            if (url != null) {
                try {
                    response.close();
                } catch (IOException error) {
                    error.getStackTrace();
                }
            }
        }
    }
}
