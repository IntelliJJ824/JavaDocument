public class inputProcess {
    protected String search, query, cache;

    public inputProcess(String search, String query, String cache) throws Exception {
        this.search = search;
        this.query = query;
        this.cache = cache;
        searchType();
    }

    public void searchType() throws Exception {

        switch (search) {
            case "author":
                processAuthor summaryAuthor = new processAuthor(cache, query, search);
                if (!summaryAuthor.judgeTheFile()) {
                    System.out.println("Web searching author");
                    summaryAuthor.setFileSearching();
                    summaryAuthor.webReader();
                    summaryAuthor.processXML();
                } else {
                    summaryAuthor.processXML();
                }

                break;

            case "publication":
                search = "publ";
                processPublication summary = new processPublication(cache, query, search);
                if (!summary.judgeTheFile()) {  //send the request to the web
                    System.out.println("Web searching for publication.");
                    summary.webReader();
                    summary.processXML();
                } else {        //process the file
                    summary.processXML();
                }
                break;

            case "venue":
                processVenue summaryVenue = new processVenue(cache, query, search);
                if (!summaryVenue.judgeTheFile()) {
                    System.out.println("Web searching for venue.");
                    summaryVenue.webReader();
                    summaryVenue.processXML();
                } else {
                    summaryVenue.processXML();
                }
                break;
            default:
                break;
        }
    }


//                String decoderUrl = URLDecoder.decode("http%3A%2F%2Fdblp.org%2Fpid%2F14%2F891.xml","UTF-8");
//                System.out.println(decoderUrl);
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
