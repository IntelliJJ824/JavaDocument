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
                    summaryAuthor.jsonReader();
                    summaryAuthor.processJson();

                } else {
                    summaryAuthor.processJson();
                }

                break;

            case "publication":
                search = "publ";
                processPublication summary = new processPublication(cache, query, search);
                if (!summary.judgeTheFile()) {  //send the request to the web
                    System.out.println("Web searching for publication.");
                    summary.jsonReader();
                    summary.processJson();
                } else {        //process the file
                    summary.processJson();
                }
                break;

            case "venue":
                processVenue summaryVenue = new processVenue(cache, query, search);
                if (!summaryVenue.judgeTheFile()) {
                    System.out.println("Web searching for venue.");
                    summaryVenue.jsonReader();
                    summaryVenue.processJson();

                } else {
                    summaryVenue.processJson();
                }
                break;
            default:
                break;
        }
    }

}
