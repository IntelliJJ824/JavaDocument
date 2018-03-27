import java.io.IOException;

/**
 * This method is to run the program and process the data.
 */
public class W09Practical {
    public static void main(String[] args) throws IOException {
        if (args.length == 6) {
            String search = "", query = "", cache = "";
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]) {
                    case ("--search"):
                        search = args[i + 1];
                        break;

                    case ("--query"):
                        query = args[i + 1];
                        break;

                    case ("--cache"):
                        cache = args[i + 1];
                        break;
                    default:
                        throw new IOException("Input error");
                }
            }
            inputProcess input = new inputProcess(search, query, cache);
//            input.printDetails();
        } else {
            System.out.println("Usage: java W09Practical --cache <The Path> --search <Type> --query <Words>");
        }
    }
}
