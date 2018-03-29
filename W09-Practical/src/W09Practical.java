import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This method is to run the program and process the data.
 */
public class W09Practical {
    public static void main(String[] args) throws Exception {
            boolean runner = true;
            String search = "", query = "", cache = "";
            for (int i = 0; i < args.length; i++) {
                try {
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
                            break;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Missing value for " + args[i]);
                        runner = false;
                }
            }

            //This is to make a judgement whether the search value is valid.
            if (!(search.equals("author") || search.equals("publication") || search.equals("venue")) && runner) {
                System.out.println("Invalid value for --search: " + search);
                runner = false;
            }

            //If the input format is correct.
            if (runner) {
                //This is to judge whether there is a directory.
                Path path = Paths.get(cache);
                if (Files.notExists(path)) {
                    System.out.println("Cache directory doesn't exist: " + cache);
                }
                else {      //all the input is correct and directory exist, and the program runs.
                    inputProcess input = new inputProcess(search, query, cache);
                }
            } else {
                System.out.println("Malformed command line arguments.");
            }
    }
}
